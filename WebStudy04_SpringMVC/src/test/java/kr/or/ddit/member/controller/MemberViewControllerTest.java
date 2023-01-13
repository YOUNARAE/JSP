package kr.or.ddit.member.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
	, @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
}) //콘텍스트들 계층구조를 만들어 줄 수 있다.
@WebAppConfiguration
public class MemberViewControllerTest {

	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMemberView() {
		mockMvc.perform(get(""))
				.andExpect(status().isOK())
				.andDo(log());
	}

}
