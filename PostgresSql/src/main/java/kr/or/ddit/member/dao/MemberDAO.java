package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 
 * 회원관리(CRUD)를 위한 Persistence Layer
 */
public interface MemberDAO {
	/**
	 * 회원 신규 등록
	 * @param member
	 * @return 등록된 레코드 수(rowcnt) > 0 : 성공, <= : 실패
	 */
	public int insertMember(MemberVO member);
	//새로 가입할 사람의 아이디,이름이 있어야해서 MemberVO를 매개변수로 받는다 
	//업데이트 쿼리로 받아야해서 int형, 몇개의 인트가 인서트됐는지
	
	/**
	 * 회원 목록 조회
	 * @return size == 0인 경우, 조건에 맞는 레코드 없음
	 */
	public List<MemberVO> selectMemberList();
	//조건으로 아무것도 받지 않는다 . 조건에 맞는 전체 조회, MemberVO에서는 엔터티 하나를 받는다
	
	/**
	 * 회원 상세 조회
	 * @param memId
	 * @return : 조건에 맞는 레코드 없는 경우, null 반환 
	 */
	public MemberVO selectMember(String memId);
	//아이디 한개로 조회하는 것, 레코드 한개가 필요하니까 MemberVO가 된다

	/**
	 * 회원 정보 수정
	 * @param member
	 * @return
	 */
	public int updatdMember(MemberVO member);
	//누군가는 전화번호를 수정하고, 누군가는 내용을 수정하기때문에 MemberVO를 파라미터로 받는다

	/**
	 * 회원 정보 삭제
	 * @param memId
	 * @return 삭제된 레코드 수 (rowcnt) > : 성공, <= 0 : 실패 
	 */
	public int deleteMember(String memId);
	//파라미터로 삭제하고 싶은 아이디 한개를 받는다
}
