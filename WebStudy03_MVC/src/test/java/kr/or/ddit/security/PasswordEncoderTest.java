package kr.or.ddit.security;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordEncoderTest {
	String password = "java";
	String mem_pass = "{bcrypt}$2a$10$TJ2DfETqDlAlVYj4hVwJ9.Vn57VRIUnSj6JNquqG3Ld/nrj.ryFLm";
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	public void encodeTest() {
		String encoded = encoder.encode(password); //인크립트부터해서 인코딩까지 다 된상태의 객체
		log.info("mem_pass :  {}", encoded);
	}

	
	@Test
	public void mathTest() {
//		String encoded = encoder.encode(password);
//		encoder.matches(password, mem_pass); //클라이언트가 입력한 비밀번호, DB에 저장된 비밀번호
		log.info("match result : {}", encoder.matches(password, mem_pass));
	}
}
