package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 핸들러 메소드의 인자 하나를 Command Object로 받을 때 사용.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ModelAttribute {
	String value() default ""; //화이트 스페이스를 가질 수 있음 
	//이녀석을 통해서 속성명을 결정하려고 한다
	//이 어노테이션은 마크언어와 싱글밸류 2가지 형태로 사용하자고 함
}
