package kr.or.ddit.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import kr.or.ddit.ReflectionUtils;

/**
 * annotation ? 개발자와 시스템에서 일정 정보를 제공하는 주석의 한 형태이다.
 * 
 * comment 정보를 취득하는 대상은 사람이다.
 * mark annotation : 메소드 안에 전역변수같은 아무것도 없던 것과 비슷한 개념, 뭔가를 표현하고 싶고 속성을 표기하고 싶을 떄 쓸 수 있는 어노테이션 @Override
 * single value annotation : @WebServlet("/member/memberInsert.do")- 어노테이션이 값을 갖게 되기때문에 싱글밸류어노테이션이다. 속성의 이름이 밸류일 때 가능하다.
 * multi value annotation : 어노테이션이 갖을 수 있는 값이 여러개이다. @WebServlet(value="/member/memberInsert.do", loadOnStartup=1)
 * 
 * custom annotation 
 *  1. @interface 키워드로 선언된 클래스 정의
 *  2. RetentionPolicy를 통해 어노테이션의 사용 범위 설정 (결정해야하는 정책이 있다. 이 어노테이션을 언제까지 살려놓을지 )
 *  3. Target으로 어노테이션 적용 대상 설정.
 *  
 */
public class AnnotationDesc {
	public static void main(String[] args) {
		String basePackages = "kr.or.ddit";
		List<Class<?>> classList = ReflectionUtils.getAllClassesAtBasePackages(basePackages);
//		classList.stream().forEach(System.out::println);
		
		Map<Class<?>, Controller> classMap = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		classMap.forEach((handlerClass, anno)->{
			try {
				Object handlerObject = handlerClass.newInstance();
				System.out.printf("%s : %s\n", handlerClass.getName(), anno);
	//			ReflectionUtils.getMethodsAtClass(clz, String.class).stream().forEach(System.out::println);
				Map<Method, RequestMapping> methodMap = ReflectionUtils.getMethodsWithAnnotationAtClass(handlerClass, RequestMapping.class, String.class); // 모든 프론트에서 logical view name이 반환되어야 해서 string
				methodMap.forEach((handlerMethod, requestMapping)->{
					String url = requestMapping.value();
					String method = requestMapping.method();
					try {
						String logicalViewName = (String) handlerMethod.invoke(handlerObject);
						System.out.printf("url : %s, method : %s, 요청 매핑 핸들러 : %s\n", url, method, handlerMethod);
						System.out.printf("핸들러 메서드에서 결정된 논리적인 뷰 이름 : %s\n", logicalViewName);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					}
				});
			} catch (Exception e) {
				
			}
		});
	}
}