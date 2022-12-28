package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.Validate;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import kr.or.ddit.vo.MemberVO;

public class ValidationUtils {
	// 검증을 우리 프로젝트에 붙이기 위한 작업들을 여기에 할 것이다
	private static Validator validator;
	
	static{
		 String massageBaseName = "kr.or.ddit.msgs.errorMessage";
		 ValidatorFactory factory =  Validation.byDefaultProvider()
								        .configure()
								        .messageInterpolator(
								                new ResourceBundleMessageInterpolator(
								                        new PlatformResourceBundleLocator( massageBaseName ) //base네임들어가는 곳
								                )
								        )
								        .buildValidatorFactory();
		 
		 validator = factory.getValidator();	
	}
	
	public static <T> boolean validate(T target, Map<String, List<String>> erros, Class<?>...groups) { //특정타입에 종속되지 않게 하려면 제네릭 타입으로 선언해줘야한다. MemberVO에만 쓸 수 있지 않도록, 검증 메소드니까 불린을 반환받는다
		// 뭐가 될지 몰라서 Class<?>의 갯수도 안 정해져있어서 가변인수
		// Map<String, List<String>> erros 로 넣어가면 콜바이 레퍼런스 타입으로 넘어갈 수 있다.
		boolean valid = true;
		//검증을 잘 하고 valid만 잘 바꿔주면 된다.
		Set<ConstraintViolation<T>> constraintViolations  = validator.validate(target, groups); //<T>타입이라는 건 타겟 타입에 제한이 없다는 의미다.
		//constraintViolations가 검증 결과들을 가지고 있다.
		valid = constraintViolations.isEmpty(); //비어있으니까 검증에 통과
		
		if(!valid) {
			constraintViolations.stream()
								.forEach(single->{
									String propertyName = single.getPropertyPath().toString(); //우린 single로 키로 잡아야하기 때문에 String으로 해줘야해서 toString
									String errorMessage = single.getMessage();
									// 여기서 작업한 것을 Map<String, List<String>> erros 담아줘야한다
									// 근데 무조건 호출할 게 아니고 먼저 꺼내와서 리스트에 내용이 있는지 없는ㄴ지에 따라 put할지 add할지를 정해야한다.
									List<String> errorMessages = Optional.ofNullable(erros.get(propertyName)) //있으면 가져오고 없으면 기본 값을 던질 수 있다
																		.orElse(new ArrayList<String>()); //있으면 리스트를 새로 만들어야한다.
									errorMessages.add(errorMessage);
									
									erros.put(propertyName, errorMessages);
								});
		} 
		
		return valid;
		
	}
	
}
