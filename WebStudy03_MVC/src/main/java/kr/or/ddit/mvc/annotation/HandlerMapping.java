package kr.or.ddit.mvc.annotation;
import javax.servlet.http.HttpServlet;


import javax.servlet.http.HttpServletRequest;
	/**
	 * 현재 요청의 조건(RequestMappingCondition)에 맞는 핸들러 정보(핸들러 객체 + 핸들러 메소드 : RequestMappingInfo)를 검색. -> 이 두개를 다 가지고 있는 녀석이 RequestMappingInfo
	 * @param request
	 * @return 
	 */
public interface HandlerMapping {
	public RequestMappingInfo findCommandHandler(HttpServletRequest request);
}
