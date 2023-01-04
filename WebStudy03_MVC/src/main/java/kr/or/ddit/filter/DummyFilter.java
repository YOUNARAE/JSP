package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DummyFilter implements Filter{//필터는 익스텐즈 안하고 임플리먼츠한다
	//필터도 생명주기가 존재한다.
	//필터도 컨테이너에 의해서 관리가 된다.
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//요청 필터링
		log.info("====================요청 필터링===================");
		chain.doFilter(request, response); //---------------------이 코드에 의해서 다음 필더에게 제어권이 이동된다
		//응답 필터링 1 ,2, 3으로 호출됬더라도 복귀는 3,2,1이다
		log.info("====================응답 필터링===================");
		
		
	}

	@Override
	public void destroy() {
		log.info("{} 초기화", this.getClass().getName());		
	} 
	
}
