package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 
 * 회원관리(CRUD)를 위한 Persistence Layer
 */
public interface MemberDAO {
	public List<MemberVO> selectMemberList(); //제네릭으로 멤버VO넣는다
	//조건으로 아무것도 받지 않는다 . 조건에 맞는 전체 조회, MemberVO에서는 엔터티 하나를 받는다

}
