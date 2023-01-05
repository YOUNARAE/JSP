package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;

public class RequestPartMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(Parameter parameter) {
		//먼저 내가 처리할 수 있는 건 어떤 어떤 아규먼트인지 알려주는 것
		Class<?> parameterType = parameter.getType();
		RequestPart requestPart = parameter.getAnnotation(RequestPart.class);
		boolean support = requestPart!=null
							&&
						  MultipartFile.class.equals(parameterType);
		return support;
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		RequestPart requestPart = parameter.getAnnotation(RequestPart.class);
		
		//리퀘스트가 원본이나 아니냐를 따져봐야한다
		if(req instanceof MultipartHttpServletRequest) {
			MultipartFile file = ((MultipartHttpServletRequest) req).getFile(requestPart.value()); //호환해야하는 파트의 이름
			//그 파일 구조 그대로 반환시킬 것이다
			if(requestPart.required() && (file==null||file.isEmpty())) {
				throw new RequestParamMethodArgumentResolver.BadRequestException("필수 파트가 누락되었다!");
			}
			return file;
		} else {
			//여기로 오면 멀티파트로 받을 수 있는 타입이 아니다
			throw new RequestParamMethodArgumentResolver.BadRequestException("멸티파트 오청이 아님"); //배드리퀘스트를 발생시키자는 것은 이미 핸들로 어댑터가 갖고있음
		}
	}
}
