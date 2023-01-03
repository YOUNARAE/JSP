package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * HandlerAdapter가 핸들러 메소드를 호출할 때
 * 메소드 인자 하나하나를 별개로 처리하기 위한 전략 객체.
 * (인자를 하나하나 따로 처리하겠다는 뜻)
 */
public interface HandlerMethodArgumentResolver {
	/**
	 * 현재 파라미터가 resolver에 의해 처리 가능한 경우인지 판단.
	 * @param parameter
	 * @return
	 */
	public boolean supportsParameter(Parameter parameter); //메소드 파라미터를 리플렉션 해야함
	//여기서 true가 들어간다면 처리를 할 수 있다는 의미
	/**
	 * 핸들러 메소드의 인자 하나를 처리(생성)하기 위한 메소드
	 * @param parameter
	 * @param req
	 * @param resp
	 * @return
	 */
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException;
	//내가 처리하겠다는 것이 parameter일지 req일지 resp일지 모르는 상황인데
	// 한개는 무조건 받아야하니까 Object타입이다
}
