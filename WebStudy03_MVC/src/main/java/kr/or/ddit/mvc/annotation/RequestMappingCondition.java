package kr.or.ddit.mvc.annotation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
/**
 * 
 * immutable 객체 형태로 값을 변경하지 않음
 *
 */
@Getter
@EqualsAndHashCode
//나중에 키로 사용할 클래스이기때문에 식별성이 필요해서 이퀄스 코드를 넣었다.
public class RequestMappingCondition {
	//2번재 만드는 클래스
	private String url;
	private RequestMethod method; //이 두가지가 같아야지만 같은 객체로 판단할 수 있게 만들음
	
	//기본생성자 사라짐
	public RequestMappingCondition(String url, RequestMethod method) {
		super();
		this.url = url;
		this.method = method;
	}
	//한번 값을 생성한 이후에는 변경 없이 그대로 둘 거라는 의미다.(기본생성자도 없고 setter도 없어서)
	@Override
	public String toString() {
		return String.format("%s[%s]", url, method);
	}
}
