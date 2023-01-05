package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestPart {
	String value(); // part name
	//기본값이 필수 속성인
	boolean required() default true;
	//파람과 두 차이는 파람을 받느냐 파트를 받느냐 그 차이뿐.
}
