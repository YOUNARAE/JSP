package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements AuthuenticateService {
	
	private MemberDAO memberDAO;
	//기본생성자 없어짐
	@Inject
	public AuthenticateServiceImpl(MemberDAO memberDAO) {
		super();
		this.memberDAO = memberDAO;
	}

	@Resource(name="passwordEncoder") //password로 등록된 것이 여러개이더라도 하나로 지칭해줄 수 있음
	private PasswordEncoder encoder; //얘를 강제로 풀어서 리플렉션 해서 주입한다, 전제조건 : encoder가 빈으로 등록되어있어야함
	
	@Override
	public ServiceResult authenticate(MemberVO member) {
		MemberVO savedMember = memberDAO.selectMember(member.getMemId()); //memId는 파라미터로 받는 member에 있다.
		//저장되어 있는 데이터, savedMember에는 멤버테이블에 있는 모든 자료를 가지고 있음
		//필요한 건 아이디,패스워드
		if(savedMember==null || savedMember.isMemDelete()) //Boolean->boolean으로 바뀌면 getMemDelete가 아닌 is
			throw new UserNotFoundException(String.format("%s 사용자 없음.", member.getMemId()));
		
		String inputPass = member.getMemPass();
		String savedPass = savedMember.getMemPass();
		//인증 실패 성공의 차이는 위 두가지 변수가 같냐 안 같냐의 차이
		
		//클라이언트가 입력한 비밀번호를 암호화 시켜야한다.
		
		ServiceResult result = null;
		if(encoder.matches(inputPass, savedPass)){
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
