package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link HttpServletRequest}, {@link HttpServletResponse}  타입의 핸들러 메소드 인자 해결.
 * @author PC-06
 *
 */


public class ServletRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?>parameterType = parameter.getType();
		boolean support = HttpServletRequest.class.equals(parameterType)
							||
						  HttpSession.class.equals(parameterType);
		return support; //내가 처리할 수 있는 파라미터를 정하는,
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//내가 처리할 수 있는 파라미터를 여기서 정한다
		Class<?>parameterType = parameter.getType();
		//리퀘스트인지 파라미터인지 파악을 해야한다.
		Object argumentObject = null; //아규먼트를 리턴으로 반환시킨다
		if(HttpServletRequest.class.equals(parameterType)) {
			argumentObject = req;
		} else {
			argumentObject = req.getSession(); //세션 객체가 없기때문에 리퀘스트의 get으로 세션을 꺼내온다.
		}
		return argumentObject;
	}

}
