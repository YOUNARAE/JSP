package kr.or.ddit.member.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.login.service.AuthuenticateService;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	private MemberDAO memberDAO;
	@Inject
	private AuthuenticateService authService;
	@Inject
	private PasswordEncoder encoder;
	
	@PostConstruct
	private void init() {
		log.info("주입된 객체 : {}, {}, {}", memberDAO, authService, encoder);
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		try {
			retrieveMember(member.getMemId());
			result = ServiceResult.PKDUPLICATED;
		}catch(UserNotFoundException w) {
			//추가
			String encoded = encoder.encode(member.getMemPass());
			member.setMemPass(encoded);
			
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
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) { //pagingVO에 심플컨디션있음
		pagingVO.setTotalRecord(memberDAO.selectTotalRecord(pagingVO)); // 두번쨰 세터를 여기서 호출해줬다
		List<MemberVO> memberList = memberDAO.selectMemberList(pagingVO); //페이징의 결과물 memberList
		pagingVO.setDetaList(memberList); //여기서 필요한 모든 걸 갖게 되었다
		
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
		MemberVO inputData = new MemberVO();
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		
		ServiceResult result = authService.authenticate(inputData);
		if(ServiceResult.OK.equals(result)) {
			int rowcnt = memberDAO.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
//		// 존재 부(NOTEXIST), 비번 인증 실패(INVALIDPASSWORD), 성공(OK), 실패(FAIL)
//		MemberVO inputData = new MemberVO();
//		inputData.setMemId(member.getMemId()); // 내가 로그인한 아이디
//		inputData.setMemPass(member.getMemPass()); // 내가 view에서 입력한 비밀번호
//
////	      MemberVO memberData = retrieveMember(member.getMemId()); // 아이디로 조회할 수 있다. 아이디에 해당하는 회원에 대한 모든 정보, 비밀번호를 꺼내써서 대조하려고 생성했다
//
		ServiceResult result = authService.authenticate(member);
////	 if(그런 회원이 존재하지 않을 경우) {
////	         //이건 어차피 로그인할 때 처리해주었다
////	      }
////	      if( !memberData.getMemPass().equals(member.getMemPass()) ) {
////	         result = ServiceResult.INVALIDPASSWORD;
////	      }
		if (ServiceResult.OK.equals(result)) {
			int rowcnt = memberDAO.deleteMember(member.getMemId()); //DAO를 통해 딜리트 처리를 할 수 있어야한다.
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
		return result;
	}

}
