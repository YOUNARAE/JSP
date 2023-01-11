package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 
 * 회원관리(CRUD)를 위한 Business Logic Layer
 */
public interface MemberService {
	
	public List<MemberVO> retrieveMemberList();
	
}
