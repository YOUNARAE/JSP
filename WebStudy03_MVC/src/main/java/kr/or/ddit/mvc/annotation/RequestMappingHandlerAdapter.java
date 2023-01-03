package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeMethodProcessor;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamMethodArgumentResolver.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.ServletRequestMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletResponseMethodArgumentResolver;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class RequestMappingHandlerAdapter implements HandlerAdapter { //스프링에서 가장 중요한 역할을 하는 어댑터이다

	private List<HandlerMethodArgumentResolver> argumentResolvers; //얘가 여기 왜 있을까? 잘 못 들었다.
	{ //코드블럭, 생성자역할
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletRequestMethodArgumentResolver());
		argumentResolvers.add(new ServletResponseMethodArgumentResolver());
		argumentResolvers.add(new RequestParamMethodArgumentResolver());
		//한개짜리를 가지고 있는 헤즈 관계를 형성했다. 
		//모든 리절버는 여기에 추가하고 있다
		argumentResolvers.add(new ModelAttributeMethodProcessor());
	}

	private HandlerMethodArgumentResolver findArgumentResolver(Parameter param) {
		// 갖고 있는 리졸브가 여러개인데 어떤거인지 확인하려고 하는 작업을 여기서 하려고 함
		HandlerMethodArgumentResolver finded = null;
		for( HandlerMethodArgumentResolver resolver: argumentResolvers) {
			//핸들러 HandlerMethodArgumentResolver 타입의 resolver의 하나하나에 접근할 수 있다
			if(resolver.supportsParameter(param)) { //한번이라도 이프문에 들어갔거나 한번도 못 들어갔거나 둘 중 한개의 결과
				finded = resolver;
				break;
			} //여기서 돌아오는 리턴타입은 본인 타입이다.ex true로 돌아오면 내가 처리할 수 있다
		}
		return finded;
	}
	
	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object handlerObject = mappingInfo.getCommandHandler();//리턴타입이 오브젝트라서 꺼내서 사용할 핸들러 객체가 된다
		Method handlerMethod = mappingInfo.getHandlerMethod(); //여기서 로지컬 뷰 네임을 받아와야한다
		int parameterCount = handlerMethod.getParameterCount();
		
		try {
//			String viewName  = (String) handlerMethod.invoke(handlerObject, req, resp); //순서는 req,resp // 여기서 리플렉션을 하고 있다
			// 아규먼트를 무조건 3개씩 넘겼는데, 이것을 하드코딩 하지 않기 위해 밑에꺼로 수정한다.
			String viewName  = null;//널값으로 우선 초기화 후.
			if(parameterCount > 0) {
				//도대체 몇개의 파라미터를 어떤 타입으로 넣어줘야하는가?
				Parameter[] parameters = handlerMethod.getParameters(); // 파라미터가 여러개라서 배열로 돌아왔다.
				Object[] arguments = new Object[parameterCount];
				// 한 개 한개의 파라미터를 만들어 내야한다.
				//여기서 우리가 만든 아규먼트의 구현체를 사용한다
				for(int i=0; i<parameterCount; i++) {
					Parameter param = parameters[i]; //파라미터들을 먼저 받아오고
					HandlerMethodArgumentResolver findedResolver =  findArgumentResolver(param); //찾는 메소드를 하나 만드려고 한다
					if(findedResolver==null) {
						//여기를 탔다는 것은 response를 사용한다는 의미
						throw new RuntimeException(String.format("%s 타입의 메소드 인자는 현재 처리 가능한 resolver가 없음", param.getType()));
					}else {
						//resolver가 있어야 아규먼트를 만들어내기 때문에
						arguments[i] = findedResolver.resolveArgument(param, req, resp);//아규먼트를 열심히 만들기만하고 써먹지 않았다
						//아규먼트에는 필요한 인자가 다 들어있다
					}
				}
				
				viewName = (String) handlerMethod.invoke(handlerObject, arguments);

			} else {
				viewName = (String) handlerMethod.invoke(handlerObject); //파라미터가 상관이 없어져서 req,resp가 다 사라짐				
			}
			// 아무 생각없이 String으로 캐스팅할 수 있었던 건 시작할 때 리턴타입을 string으로 고정해놨기 때문이다.
			return viewName; 
		}catch(BadRequestException e) {
			log.error("handler adapter가 handler method argument resolver 사용 중 문제 발생", e);
			resp.sendError(400, e.getMessage());
			return null; // 이 상태코드 에러를 보여줄 땐 뷰 네임을 널로 보여줘도 상관없다.
		}catch(Exception e) {
			throw new ServletException(e); //기존의 예외를 반드시 전달해야한다는 것
		}
	}
}
