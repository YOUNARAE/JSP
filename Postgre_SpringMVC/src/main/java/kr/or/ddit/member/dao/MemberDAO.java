package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface MemberDAO {
	public List<MemberVO> selectMemberList();
}
