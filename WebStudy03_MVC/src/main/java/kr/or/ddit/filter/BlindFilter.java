package kr.or.ddit.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BlindFilter implements Filter{

	private Map<String, String> blindMap; //키는 맵의 대상자, 밸류는 해상자들의 타입
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", this.getClass().getName());
		blindMap = new LinkedHashMap<>();
		blindMap.put("127.0.0.1", "나니까 블라인드"); 
		blindMap.put("0:0:0:0:0:0:0:1", "나니까 블라인드"); 
		blindMap.put("192.168.35.43", "나니까 블라인드");
		blindMap.put("192.168.35.51", "경수니까 블라인드");		
		blindMap.put("192.168.35.89", "알리미니까 블라인드");		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("blind filter 동작");
		//1. 클라이언트의 아이피를 습득
		String ipAddress = request.getRemoteAddr(); 
		//2. 대상자 판단하기
		
		if(blindMap.containsKey(ipAddress)){
			String reason = blindMap.get(ipAddress);
			String message = String.format("당신은 %s한 사유로 블라인드 처리되었음", reason);
			request.setAttribute("message", message);
			//view안에 있어서 리다이렉트로는 보낼 수 없다
			//뷰스 안에 넣은 건 클라이언트의 요청을 못 받도록 하기 위해 그 안에 넣었다
			String viewName = "/WEB-INF/views/commons/messageView.jsp";
			request.getRequestDispatcher(viewName).forward(request, response);
		} else {
			chain.doFilter(request, response);//두 필터를 리퀘스트와 리스폰즈를 옮겨준다
		}
		
		//3.블라인드 대상자가 아니면 정상적으로 서비스를 얻게 해준다
				
			
		//4.블라인드 대상자라면 통과시키지 않을꺼고 왜 때문에 블라인드가 되는지 알려줄 것임
		log.info("blind filter 동작종료");
	}

	@Override
	public void destroy() {
		log.info("{} 소멸", this.getClass().getName());
		// TODO Auto-generated method stub
		
	}

}
