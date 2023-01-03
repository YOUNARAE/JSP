package kr.or.ddit.annotation.dummy;

import kr.or.ddit.annotation.Controller;
import kr.or.ddit.annotation.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller //백단에서 동작하는 클래스라는 표현의 어노테이션
public class DummyCommandHandler {
	
	private String dummy() {
		return null;
	}
	
	//멤버에서 동작을 시킬지 prod에서 동작시킬지, 
	
	//메소드의 겟이라는 어노테이션이 생략됨
	@RequestMapping("/testInsert")
	public String form() {
		return "test/form";
	}
	
	@RequestMapping(value="/testInsert", method="post")
	 //또다른 식별성을 알 수 있는 속성을 넣어줌
	public String process() {
		return "redirect:/testInsert";
	}
}
