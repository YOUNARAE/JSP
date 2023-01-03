package kr.or.ddit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //실제 구현해야하는 타겟이 메소드가 되어야한다.
public @interface RequestMapping {
	String value(); //메소드 같아 보이지만 이렇게 쓰면 속성명이 된다
	//이렇게 써서 싱글벨류 어노테이션이 될 수 있다.
	String method() default "get";
	//속성이 2개가 됨.
	//싱글밸류로 사용할 수 없다. 값이 두개라서, 그럴땐 default 를 붙여준다. 
}
