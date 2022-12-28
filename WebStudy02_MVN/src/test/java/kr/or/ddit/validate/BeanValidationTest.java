package kr.or.ddit.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.ibatis.annotations.Delete;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

public class BeanValidationTest {
	private static final Logger log = LoggerFactory.getLogger(BeanValidationTest.class);

	 private static Validator validator;
	 
	 @BeforeClass //클래스로 하면 이 안에 테스트 케이스가 5개가 있어도 무조건 1번만 실행한다.
	 public static void setUpBeforeClass() {
//		 String massageBaseName = "kr/or/ddit/msgs/errorMessage";
		 String massageBaseName = "kr.or.ddit.msgs.errorMessage"; //확장자 안 포함됨, 로케일도 _ko.pro - .도 빼야함
//		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
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
	
	 @Test
	 public void memberVOTest() {
		 MemberVO member = new MemberVO(); //객체가 필요하다. 
		 //처음엔 비어있는 상태
//		 member.setMemId("b001");
		 member.setMemBir("2000/01-01");
//		 member.setMemBir("2000/01-01"); -- : 체킹 여부에 걸린다
//		 member.setMemMail("aa@gmail.coms"); // @를 빼면 체킹에 다시 걸린다.
//		 member.setMemPass("abcd");
//		 member.setMemMileage(-1000);
//		 이 과정을 컨트롤러에서 하다가 프레임워크에게 다 넘기게 된 것이다.

//		 위에 member를 대상으로 검증을 할 것이다
		 Set<ConstraintViolation<MemberVO>> constraintViolations = 
//				 				validator.validate(member); //반환타입이 set이라는 건 검증을 실패했을 시 돌아오는 데이터가 계속 들어가있을 수 있다는 것을 의미
								 // 파라미터를 2개를 넣을 수 있는데 1개일 때는 기본 그룹(그룹이 없을 때)
//				 				validator.validate(member, UpdateGroup.class); //반환타입이 set이라는 건 검증을 실패했을 시 돌아오는 데이터가 계속 들어가있을 수 있다는 것을 의미
				 				validator.validate(member, InsertGroup.class); //반환타입이 set이라는 건 검증을 실패했을 시 돌아오는 데이터가 계속 들어가있을 수 있다는 것을 의미
		 constraintViolations.stream()
		 						.forEach(singleViolation->{
		 							Path propertyPath = singleViolation.getPropertyPath();
		 							String errorMessage = singleViolation.getMessage();
		 							log.error("{} : {}", propertyPath, errorMessage);
		 						});
	 }
}
