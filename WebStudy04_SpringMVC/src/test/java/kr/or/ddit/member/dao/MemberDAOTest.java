package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RunWith(SpringRunner.class) // 1. Junit Context 구성
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml") // 2, 가상컨테이너 하나 생성
@WebAppConfiguration // 3. WebApplicationContext로 가상 컨테이너 생성.
public class MemberDAOTest extends AbstractTestCase{
	
	@Inject
	private MemberDAO memberDAO;

	@Test
	public void test() {
		log.info("주입된 객체 : {}", memberDAO);
	}
	
	

}
