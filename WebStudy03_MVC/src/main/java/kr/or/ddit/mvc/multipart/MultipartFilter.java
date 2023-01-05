package kr.or.ddit.mvc.multipart;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * multipart request를 request wrapper(MultipartHttpServletRequest)로 변경하는 필터
 * 
 */
public class MultipartFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//일반요청에 대해선 판단할 필요가 없고 멀티 파트에 대해서만 판단을 하면 된다.
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		// 시작이 /multipart로 시작되는
		if(contentType!=null && contentType.startsWith("multipart/form-data")) {
			HttpServletRequestWrapper modifiedReq = new MultipartHttpServletRequest(req); //어댑터 하나를 만들어줌
			chain.doFilter(modifiedReq, response);
			//이 맵퍼에서는 뭘할지를 정해야한다.
			
		} else {
			//그냥 아무것도 안해도 된다
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
