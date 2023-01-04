package kr.or.ddit.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 보호 자원에 대한 요청인 경우, 신원 확인(session 스코프 안에 들어있는 authMember)을 한 사용자인지 판단 
 * 경우의 수가 3가지
 */
@Slf4j
public class AuthenticationFilter implements Filter{
	
	private Map<String, String[]> securedResources;
	public static final String SECUREDNAME = "securedResources";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedResources = new LinkedHashMap<>();
		filterConfig.getServletContext().setAttribute(SECUREDNAME, securedResources);
		//classpath주소
		String filePath = filterConfig.getInitParameter("filePath");
		try(
			InputStream is = this.getClass().getResourceAsStream(filePath);
		){
			Properties props = new Properties();
			props.load(is);
			props.keySet().stream()
						.map(Object::toString)
						.collect(Collectors.toList())
						.forEach(key->{
							String value = props.getProperty(key);
							securedResources.put(key, value.split(","));
							log.info("보호 자원에 대한 정보[{} : {}]", key, securedResources.get(key));
						});
						//멥은 스트림 안에 들어있는 데이터들을 하나하나 변화할떄 사용한다
			//받아야하는 건 오브젝트 인데 갖고 놀아야하는 형태는 스트링이다.
			
		}catch(IOException e) {
			throw new ServletException(e); //io익셉션으로 던져지지만 톰캣에는 서블릿 익셉션으로 던져지게 된다
		}
		//자원으 ㅣ종류와 클래스패스 종류에 따라 지원을 읽어들이는 방법
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getServletPath();
		
		boolean pass = true; //통과를 시킬것인지 아닐 것인지를 판단해야하는 것
		
		if(securedResources.containsKey(uri)) { //보호자원이면 통과를 시키면 안되
			//케이스1이 이미 true이다
			//보호자원일 경우에
			//신원을 확인해야한다.
			Object authMember = req.getSession().getAttribute("authMember"); //상황은 얘가 있거나 없거나 둘 중 상황이다
			if(authMember==null) {
				//이 케이스는 통과시키면 안된다.
				pass = false;
			}
		} 
		
		if(pass) {
			//패스가 true일 경우에
			chain.doFilter(request, response);
		}else {
			// 보호자원을 요청했는데 세션 스코프에 (로그인된)어스 멤버가 없는 경우
			// loginform으로 이동
			// 인증시스템에선 무조건 redirect
			String viewName = req.getContextPath() + "/login/loginForm.jsp";
			resp.sendRedirect(viewName);
		}
	}

	@Override
	public void destroy() {
		
	}

}
