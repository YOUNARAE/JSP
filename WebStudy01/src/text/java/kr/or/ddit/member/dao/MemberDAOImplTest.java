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

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsertMember() {
		fail("Not yet implemented");
	}

	@Test
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
		member = dao.selectMember("1234a");
		assertNull(member);
	}

	@Test
	public void testUpdatdMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
