package kr.or.ddit.vo;

import java.security.Principal;

public class MemberVOWrapper implements Principal{
//principle은 필요한 데 VO는 바꾸기 싫어서 만든 어댑터
//어댑터는 기본생성자를 갖을 필요도 갖아서도 안된다
	private MemberVO realMember;
    //어댑터    //어댑티
	
	//이생성자가 들어가는 순간 기본생성자는 사라짐
	public MemberVOWrapper(MemberVO realMember) {
		super();
		this.realMember = realMember;
	}
	
	public MemberVO getRealMember() {
		return realMember;
	}

	@Override
	public String getName() {
		return realMember.getMemId(); //여기에 멤버 VO에 있는 memId를 갖다쓸 수 있다.
	}

}
