package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import kr.or.ddit.reflect.ReflectionTest;
import kr.or.ddit.vo.MemberVO;

/**
 * Reflection(java.lang.reflect)
 *  : 객체가 가지고 있는 여러가지 특성들 ( 객체의 타입, 객체의 속성(상태, 행동)정보, 들을 역으로 추적하는 작업. 
 *
 */
public class ReflectionDesc {
	public static void main(String[] args) {
		Object dataObj =  ReflectionTest.getObject();
		System.out.println(dataObj);
		//데이터를 갖고 있어도 memId를 꺼내올 수 었다. 이럴 때 사용하기 위해 만든게 리플렉션 객체
		//객체 정보가 아무것도 없이 상태정보를 꺼내와야할 때
//원래라면 ->		MemberVO member = new MemberVO();
		// 해당하는 객체를 먼저 선언은 하고, 이 타입을 모방하겠다는 의미
		// 이 한 줄을 실행하기 위해서는 MemberVO의 구체적인 타입을 알고 있어야한다.
		// but Object dataObj 은 정확한 타입을 알 수 없다
//		member.getMem_name();		
		
		Class<?> objType = dataObj.getClass();
		System.out.println(objType.getName());
		
//		objType.getFields(); //타입을 찾아내면 모든 건 타입으로 한다. 필드에 뭐가 들어있는지도 모르니까 그냥 필드들을 가져와라.
		Field[] fields = objType.getDeclaredFields(); //붕어빵 클래스가 가지고 있는 모든 전역변수에 대한 필드들
//		Arrays.stream(fields)
//				.forEach(System.out::println); 
		
		Method[] methods = objType.getDeclaredMethods();
//		Arrays.stream(methods)
//				.forEach(System.out::println);
		
		try {
			Object newObj = objType.newInstance(); //붕어빵의 새로운 인스턴스를 만들려고 하는데, 객체가 뭔지 알 수 없어서 기본생성자를 외부에서 불러오지 못해서 오류나서 멀티캐치로 묶는다
			Arrays.stream(fields)
					.forEach(fld->{
//						fld.setAccessible(true);
						String fldName = fld.getName(); // mem_id, getMem_id, setMem_id (자바빈규약)
						try { //특정타입에 종속되지 않음
							PropertyDescriptor pd = new PropertyDescriptor(fldName, objType);
							Method getter = pd.getReadMethod(); //getter
							Method setter = pd.getWriteMethod(); //setter
							//getter를 찾아야함
//							Object fldValue = fld.get(dataObj);
							Object fldValue = getter.invoke(dataObj);//게터는 파라미터가 필요없다
							//setter를 찾아야함
//							fld.set(newObj, fldValue); //복사하는 작업이 다 끝났음
							setter.invoke(newObj, fldValue);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						} catch (IntrospectionException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					});
			System.out.println(newObj); //내 붕어빵
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} 
		
		
	}
}
