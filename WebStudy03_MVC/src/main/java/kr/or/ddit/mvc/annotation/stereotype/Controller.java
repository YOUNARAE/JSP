package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Marker annotation 형태로
 * Front controller 다음에서 동작한 handler 객체 표현. (뭔가를 표현만 하고 실제로 갖고있지 않다)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
//여기까지는 아무 의미없는 어노테이션들이라서 마커 어노테이션인 상태이다.
public @interface Controller {

}
