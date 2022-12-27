package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceTest {
	//테스트를 진행하기 위해 추가
	private MemberService service;
	//create하려면 필요해서 추가
	private MemberVO member;
	
	@Before
	public void setUp() throws Exception {
		member = new MemberVO();
//		member.setMemId("a001"); //테스트를 위해 멤버 아이디를 셋팅해본다
	}

	@Test
	public void testCreateMember() {
		ServiceResult result = service.createMember(member);
//		result==ServiceResult.OK
//				result==ServiceResult.FAIL
//				result==ServiceResult.PKDUPLICATED
				
//		int rowcnt = service.createMember(member);
		// 0, 실패의 원인이 다르게 나타날 수 있는데 0은 두 상황에 대한 식별성이 없다.
	}

	@Test
	public void testRetrieveMemberList() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMember() {
		fail("Not yet implemented");
	}

}
