package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import kr.or.ddit.mvc.annotation.RequestMethod;

/**
 * single value(GET handler), multi value
 * 핸들러 메소드에 commandler handler 내의 핸들러 메소드에
 * 어떤 요청이라는 걸 식별하기 위해 (주소, 메소드) 구조를 사용하고 있다. 동작을 이 모양으로 표현하고 있다
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //실제 구현해야하는 타겟이 메소드가 되어야한다.
public @interface RequestMapping {
	String value(); //메소드 같아 보이지만 이렇게 쓰면 속성명이 된다
	//이렇게 써서 싱글벨류 어노테이션이 될 수 있다.
	RequestMethod method() default RequestMethod.GET;
	//속성이 2개가 됨.
	//single value로 사용할 수 없다. 값이 두개라서, 그럴땐 default 를 붙여준다.
}
