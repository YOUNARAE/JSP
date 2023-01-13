package kr.or.ddit.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller //프론트뷰에서 실행되는 핸들러가 된다
//@WebServlet("/member/memberList.do")
public class MemberListController{ //완전한 POJO
//	의존관계(점선)을 형성해준다
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberList.do") 
	public String memberList(HttpServletRequest req, HttpServletResponse resp)  {
				
		MemberVO member = new MemberVO();
		List<MemberVO> memberList = service.retrieveMemberList();
		req.setAttribute("memberList", memberList);
		
		
		String viewName="member/memberList";
		return viewName;
	}
}
