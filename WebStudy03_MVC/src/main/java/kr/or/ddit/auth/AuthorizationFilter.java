package kr.or.ddit.auth;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
/**
 * 보호자원에 대한 요청인 경우,
 * 자원에 설정된 역할 정보와 사용자에게 부여된 역할 정보가 일치할 때
 *
 */
public class AuthorizationFilter implements Filter{

	public ServletContext application;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		application = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, String[]> securedRecources = (Map) application.getAttribute(AuthenticationFilter.SECUREDNAME);
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
//		HttpSession session = req.getSession(); // 세션 없어도 리퀘스트 만드로도 가능해진 건 이프문 안에 authMember를 받아서
		
		boolean pass = true;
		String uri = req.getServletPath();
		
		if(securedRecources.containsKey(uri)) {
			String[] resRoles = securedRecources.get(uri);//여러개를 가지고 있어서
			//한쪽의 비교대상을 잡아왔다
//			MemberVO authMember = (MemberVO) session.getAttribute("authMember"); //principal구현 전 코드
			MemberVOWrapper principal = (MemberVOWrapper) req.getUserPrincipal();
			MemberVO authMember = principal.getRealMember(); // 세션 없어도 리퀘스트 만드로도 가능
			String memRole = authMember.getMemRole(); //이 배열안에 memRole이 포함되어 있는지
			pass = Arrays.stream(resRoles) //true가 돌아오면 통과시키고 false가 돌아오면 통과시키면 안된다.ㄴ
				.anyMatch(ele->ele.equals(memRole));
		}//찾았는지 못 찾았는지에 따라 에러를 내보낸다.
		
		if(pass) {
			chain.doFilter(request, response);
		} else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "권한 없는 자원에 대한 요청");
		}
	}
	@Override
	public void destroy() {
		
	}

}
