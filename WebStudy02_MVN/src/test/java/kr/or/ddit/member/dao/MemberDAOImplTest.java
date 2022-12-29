package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
//	private MemberService service = new MemberServiceImpl();
	private MemberDAO dao = new MemberDAOImpl();
	private MemberVO member;

	@Before
	public void setUp() throws Exception {
		member = new MemberVO();
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("유나래");
		member.setMemBir("1990624");
		member.setMemZip("12345");
	}

//	@Test
	public void testInsertMember() {
		dao.insertMember(member); //테스트 대상이 되는 것을 테스트 더미라고 한다
	}

//	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		memberList.stream()
				.forEach(System.out::println);
//		List<MemberVO> memberList = service.retrieveMemberList();
//		assertNotEquals(0, memberList.size());
		
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		System.out.println(member);
		member.getProdList().stream()
				.forEach(System.out::println);
//		member = dao.selectMember("1234a");
//		assertNull(member);
	}

//	@Test
	public void testUpdatdMember() {
		fail("Not yet implemented");
	}

//	@Test
	public void testDeleteMember() {
		int rowcnt = dao.deleteMember("b001");
		assertEquals(1, rowcnt); //성공할 때 값, 실제값 
	}

}
