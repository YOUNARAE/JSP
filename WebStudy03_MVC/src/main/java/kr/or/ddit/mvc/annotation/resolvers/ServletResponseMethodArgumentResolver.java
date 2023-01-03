package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
/**
 * {@link HttpServletResonse} 타입의 인자를 해결
 */
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletResponseMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		Class<?> parameterType = parameter.getType(); //리스폰즈가 내가 지원하는 파라미터가 되겠다는
		boolean support = HttpServletResponse.class.equals(parameterType);
		return support;
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return resp;
	}

}
