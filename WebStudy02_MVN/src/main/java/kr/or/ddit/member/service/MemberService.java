package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

/**
 * 
 * 회원관리(CRUD)를 위한 Business Logic Layer
 */
public interface MemberService {
	
	/**
	 * 회원 가입
	 * @param member
	 * @return 아이디 중복으로 인한 실패(PKDUPLICATED), 가입 성공(OK), 가입 실패(FAIL), 
	 */
//	public int createMember(MemberVO member);
	public ServiceResult createMember(MemberVO member);
	//회원 한명 가입 시키려고 해서 멤버VO를 파라미터로 받고, 가입을 성공시켰는지 아닌지 체크하기 위해 타입은int로 정했다
	
	public List<MemberVO> retrieveMemberList();
	// 리스트라서 파라미터는 안 넣어도 된다
	
	/**
	 * 회원 정보 상제 조회
	 * @param memId
	 * @return 존재하지 않는 경우, {@link UserNotFoundExceptiono} 발생.
	 */
	public MemberVO retrieveMember(String memId);
//	throws UserNotFoundException;
//	public MemberVO retrieveMember(String memId); 
	//존재하는 경우에는 반환을 해줘야하기때문에 이넘 타입으로 반환타입을 해서는 안된다.

	/**
	 * 회원 수정
	 * @param member
	 * @return 존재부(NOTEXIST), 비번 인증 실패(INVALIDPASSWORD), 성공(OK), 실패(FAIL)
	 */
	public ServiceResult modifyMember(MemberVO member);
//	public int modifyMember(MemberVO member); //비번 오류가 난 케이스, 수정에 실패하는 케이스, ...4가지
	
	/**
	 * 회원 탈퇴
	 * @param memId
	 * @return 존재 부(NOTEXIST), 비번 인증 실패(INVALIDPASSWORD), 성공(OK), 실패(FAIL)
	 */
	public ServiceResult removeMember(MemberVO member);
//	public int removeMember(String memId); //비번인증까지 필요해서 멤버VO로 파라미터 변경
}
