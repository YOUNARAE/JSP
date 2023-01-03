package kr.or.ddit.validate;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

public class ValidationUtilsTest {
	private static final Logger log = LoggerFactory.getLogger(ValidationUtilsTest.class);

	@Test
	public void testValidate() {
		//테스트 전 먼저 타겟이 필요하다
		MemberVO target = new MemberVO();
		
		//맵이 하나 필요하다
		Map<String, List<String>> errors = new LinkedHashMap<>();
		
		boolean valid = ValidationUtils.validate(target, errors, InsertGroup.class);
		
		if(!valid) {
			errors.forEach((k,v)->{
				log.error("{} : {}", k, v);
			});
		}
	}

}
