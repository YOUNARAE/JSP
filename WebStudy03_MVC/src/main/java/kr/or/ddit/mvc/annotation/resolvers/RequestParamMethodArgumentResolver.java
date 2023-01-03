package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @RequestParam을 가지고 있으며, 기본형 타입인 인자를 해결.
 *
 */
public class RequestParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
		boolean support = requestParam != null
				&&
			(
				parameterType.isPrimitive() 
				||
				String.class.equals(parameterType) //기본형이 아니가서 스트링 파라미터 타입이면 이 조건이라는 의미
				||
				(	
					parameterType.isArray() 
					&&  
					(
						parameterType.getComponentType().isPrimitive() 
						|| 
						parameterType.getComponentType().equals(String.class)
					  )
				)//배열타입인지 확인, 인트의 배열, 불린의 배열, 스트링의 배열
			);
		return support; //어노테이션을 갖고 있는 경우에만 내가 처리하겠다는 메소드를 오버라이딩했다.
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
		
		String requestParameterName = requestParam.value(); //리퀘스트에 대한 정보가 여기 파람에 있고 파람에 대한 정보는 value가 갖고 있다
		boolean required = requestParam.required(); //
		String defaultValue = requestParam.defaultValue();
		
		//requespParameterName 이게 한개일지 몇개일지 모름
		String[] requestParameterValues = req.getParameterValues(requestParameterName); //넘겨줄 수 있는 타입의 이름을 가지고 있고 여러개일 수 있어서 배열로 받는다
		if(required && ( requestParameterValues==null 
							|| requestParameterValues.length==0 
							|| StringUtils.isBlank(requestParameterValues[0] )
						)) { //필수 파라미터 정보인데 하나도 요청 파라미터가 넘어오지 않을 때
			//필수 파라미터가 누락되었기때문에 오류가 400이 되어야한다
//			resp.sendError(400); //여기서는 상태코드 400을 직접적으로 사용하지 않는다
//			return null;
			throw new BadRequestException(requestParameterName + "이름의 필수 파라미터 누락");
		}
		if( requestParameterValues==null || requestParameterValues.length==0 || StringUtils.isBlank(requestParameterValues[0]) ) {
			requestParameterValues = new String[] {defaultValue};
		}
		//이 이프문들을 건너뛰었다면 requestParameterValues은 절대  null일 수가 없다는 의미
		
		Object argumentObject = null;
		if(parameterType.isArray()) {
			//값을 몇개 만들진 모르겠지만 여러개 만들어야함 , 여긴 배열이라는 걸 확인한 경우
			Object[] argumentObjects = new Object[requestParameterValues.length]; //파라미터의 갯수가 몇개인지 requestParameterValues가 가지고 있다.
			for(int i=0; i<argumentObjects.length; i++) {
				argumentObjects[i] = 
						singleValueGenerate(parameterType.getComponentType(), requestParameterValues[i]);
				//argumentObjects[i]얘가 모든 정보들을 다 가지고 있다.
			}
			argumentObject = argumentObjects;
		} else {
			argumentObject = singleValueGenerate(parameterType, requestParameterValues[0]);
			//한개 값의 타입, 요청 파라미터 한개를 넘긴다
		}
		return argumentObject;
		
	}
	
	private Object singleValueGenerate(Class<?> singleValueType, String requestParameter) {
		Object singleValue = null;
		//기본형 8개에 해당하는 이프문을 원래 다 써야하는데 3개로 압축시킴
//		if(byte.class.equals(singleValueType)) {
		if(int.class.equals(singleValueType)) {
			singleValue = Integer.parseInt(requestParameter);
//		} else if(short.class.equals(singleValueType)) {
		} else if(boolean.class.equals(singleValueType)) {
			singleValue = Boolean.parseBoolean(requestParameter); //파싱해서 불린으로 만들어준다
		} else {
			//String type , 어차피 요청 파라미터는 엘즈, 스트링이니까
			singleValue = requestParameter;
		}
		return singleValue;
	}

	public static class BadRequestException extends RuntimeException{
		
		public BadRequestException(String message) {
			super(message);
		}
		
	}
}
