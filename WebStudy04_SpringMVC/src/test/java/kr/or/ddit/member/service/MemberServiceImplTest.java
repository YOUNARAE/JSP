import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.AbstractTestCase;

//Mock
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("test-servlet-context.xml")
public class MemberServiceImplTest extends AbstractTestCase{
	
	@Inject
	private MemberService service;

	@Test
	public void testInit() {
	}
	
	@Test
	public void testCreateMember() {
		
	}
	
	@Test
	public void testRetrieveMemberList() {
		
	}
	@Test
	public void testRetrieveMember() {
		
	}

}
