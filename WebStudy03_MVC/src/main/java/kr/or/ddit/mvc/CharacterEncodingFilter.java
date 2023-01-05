package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;

public class CharacterEncodingFilter implements Filter {

	private String encoding; 
	private boolean forced; //reponse를 인코딩할지
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		try {
			forced = Boolean.parseBoolean(filterConfig.getInitParameter("forced")); //뺴먹으면 파으익셉션
		}catch(Exception e) {
			forced = true;
		}
		if(StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		//만약에 포스드가 트루면
		if(forced)
			response.setCharacterEncoding(encoding);
		//이게 없으면 필터링이 안되서 안 나옴
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}
