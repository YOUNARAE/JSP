package kr.or.ddit.mvc.annotation;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class RequestMappingHandlerMapping implements HandlerMapping {
	//이 클래스의 목적
	//1.hadlerMap을 만들기 위해 핸들러 정보들을 수집하는 것이 이 클래스의 역할
	//2.수집된 정보를 기반으로 현재 요청을 처리할 수 있는 커멘드 핸들러 메서드를 검색해주는 것이 두번째 역할
	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;//attribute
	
	public RequestMappingHandlerMapping(String...basePackges) { 
		handlerMap = new LinkedHashMap<>();
		scanBasePackages(basePackges); //메서드를 하나 만들어준다
	}
		
	private void scanBasePackages(String[] basePackges) {
		// 이 안에서 리플렉션을 하려고 한다.
		// 어노테이션 트레이싱을 하는 곳이다
		ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackges)
			//첫번쨰로 받을 수 있는 건 핸들러 클래스, 두번쨰는 컨트롤러 어노테이션
			.forEach((handlerClass, controller)->{
				try {
					Object commandHandler = handlerClass.newInstance(); //메소드 호출 부분
					//문제가 발생된 클래스 한번더 스캔
					ReflectionUtils.getMethodsWithAnnotationAtClass(
							handlerClass, RequestMapping.class, String.class // 리턴타입은 무조건 String이고 파라미터는 무조건 밑에 2개를 받고 그 순서는 바꿀 수 없다
//							, HttpServletRequest.class //조건을 추가할 것이다. 모든 핸들러들을 파라미터로 리퀘스트를 받는다
//							, HttpServletResponse.class//모든 핸들러들은 두번째 파라미터로 리스폰즈를 받는다, 제약사항 정해놓은 곳
					).forEach((handlerMethod, requestMapping)->{ //맨 위에 handlerMap를 채워주기 위해서 여기 과정이 있었다, 
						RequestMappingCondition mappingCondition =  //key
								new RequestMappingCondition(requestMapping.value(), requestMapping.method()); //메소드와 url에 대한 정보는 requestMapping이 가지고 있다
						RequestMappingInfo mappingInfo = //value
								new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod); 
						handlerMap.put(mappingCondition, mappingInfo); //키와 밸류로 두 가지 값을 받는다.
						log.info("수집된 핸들러 정보 : {}", mappingInfo); //mappingInfo가 모든 핸들러 내용들을 가지고 있기때문에 얘를 출력해본다
					});
				} catch(Exception e) {
					log.error("핸들러 클래스 스캔 중 문제발생", e);
				}
			});
		//컨트롤러를 가지고 있는 클래스를 찾아야한다
	}

	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest request) { //response가 없어서 404에 응답이 없다는 표현을 할 수가 없는 것이다
		String url = request.getServletPath(); //디스패쳐 서블릿에서 책임을 덜어내는 코드다
		RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase()); //현재 모든 요청은 리퀘스트에 들어있기때문에 메소드도 리퀘스트에 있다.
		RequestMappingCondition mappingCondition = //여길걸 기준으로 위에 handlerMap을 검색했다 , 경우에 따라선 이 값에 널이 들어올 수 있는데 처리하는 것이 없다
				new RequestMappingCondition(url, method);
		return handlerMap.get(mappingCondition); //리퀘스트 구조로 맵을 반환시키기 위한 과정
	}

}
