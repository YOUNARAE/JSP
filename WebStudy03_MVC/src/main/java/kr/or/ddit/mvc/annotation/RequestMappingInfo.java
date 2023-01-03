package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestMappingInfo {
    private RequestMappingCondition	mappingCondition; // url과 method를 가지고 있음.
    private Object commandHandler; // 타입이 object는 컨트롤러가 가지고 있는 클래스의 객체들을 가지고 있다.
    private Method handlerMethod; // 리플렉션으로 메소드가 어떤 내용인지 알기 전까지는 임의로 정한 타입으로, 여기에 객체의 어떤 구조로 어떤 메소드를 호출해야하는지의 정보가 들어있다.
    
    // 이 생성자가 생성되면서 기본생성자가 사라졌다
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
    
	//Getter와 생성된 생성자로 이뮤테이블 객체가 되었다(수정될 수 없는)
    //지금 이 클래스는 키 타입이 아니라 밸류스 타입이라서 나중에 비교대상이 될 필요가 없어서 이퀄스는 만들지 않았다.
}
