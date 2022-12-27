package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	//주방장이 요리를 하려면 재료를 꺼내와야한다. 
	//이 코드에 의해서 결합력이 최상으로 발생한다.
	private MemberDAO memberDAO = new MemberDAOImpl();
	//싱글톤을 쓰지 않을 것이다. 차후에 스프링 프레임워크를 사용해서 대신 구현할 예정
	
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		try {
			retrieveMember(member.getMemId());
			result = ServiceResult.PKDUPLICATED;
		}catch(UserNotFoundException w) {
			int rowcnt = memberDAO.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
		return result;
//		// 다오임플에 구현한 인서트 메서드를 이용해서 인서트 쿼리를 이용해 넣고
//		int cnt = memberDAO.insertMember(member);
//		// 이넘으로 상태를 비교해서 
//		ServiceResult result=null;
//		if(cnt>0) {
//			// 값을 리턴시켜
//			result = ServiceResult.OK;
//		} else {
//			result = ServiceResult.FAIL;
//		}
//		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		List<MemberVO> memberList = memberDAO.selectMemberList();
	    return memberList;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = memberDAO.selectMember(memId);
		if(member==null) {
			throw new UserNotFoundException(String.format(memId+"에 해당하는 사용자가 없음"));
		}
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

}
