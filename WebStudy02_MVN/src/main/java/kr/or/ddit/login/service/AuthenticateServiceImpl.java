package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthuenticateService {
	private MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticate(MemberVO member) {
		MemberVO savedMember = memberDAO.selectMember(member.getMemId()); //memId는 파라미터로 받는 member에 있다.
		//저장되어 있는 데이터, savedMember에는 멤버테이블에 있는 모든 자료를 가지고 있음
		//필요한 건 아이디,패스워드
		if(savedMember==null) 
			throw new UserNotFoundException(String.format("%s 사용자 없음.", member.getMemId()));
		
		String inputPass = member.getMemPass();
		String savedPass = savedMember.getMemPass();
		//인증 실패 성공의 차이는 위 두가지 변수가 같냐 안 같냐의 차이
		ServiceResult result = null;
		if(savedPass.equals(inputPass)){
//			member.setMemName(savedMember.getMemPass());
			try {
				BeanUtils.copyProperties(member, savedMember);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}			
			result = ServiceResult.OK;
			
		} else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
