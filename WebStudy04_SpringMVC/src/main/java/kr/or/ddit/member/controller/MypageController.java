package kr.or.ddit.member.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

//@WebServlet("/mypage.do")
//public class MypageControllerServlet extends HttpServlet{
@Controller
public class MypageController{
	//서비스와의 의존관계
	@Inject
	private MemberService service;
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	@RequestMapping("/mypage.do")
	public String mypage(
						Model model
						, MemberVOWrapper principal) {
		//누구라는 정보는 세션에 들어있는 세션스코프가 필요하다
//		HttpSession session = req.getSession();
//		MemberVOWrapper principal = (MemberVOWrapper) req.getUserPrincipal(); //아규먼트 넣어줘서 없어져도 되는 코드 
//		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		MemberVO authMember = principal.getRealMember();
//		authMember.getMemId();
		
		MemberVO member = service.retrieveMember(authMember.getMemId()); // 방금전 그 아이디를 꺼내서 넘겨준다
		// 여기에 모든 로그인한 사람의 정보를 가지고 있는 모델 확보
		//모델 공유
//		req.setAttribute("member", member);
		//어댑터가 대신 위의 일을 해준다
		model.addAttribute("member", member);
		
		//뷰 네임 결정
		String viewName = "member/memberView"; //완전한 뷰네임이 아닌 것을 logical view name이라고 한다.
		
//		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp);
		return viewName;
		
	}
}
