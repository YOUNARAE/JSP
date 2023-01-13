package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memDAO = new MemberDAOImpl();
	
	@Override
	public List<MemberVO> retrieveMemberList() {
		List<MemberVO> memberList = memDAO.selectMemberList();
		return memberList;
	}

}
