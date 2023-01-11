package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

//@WebServlet("/member/memberView.do")
@RequiredArgsConstructor
@Controller
public class MemberViewController {
//public class MemberViewController implements AbstractController{
//	서비스와 의존관계(점선)을 형성해준다
	private final MemberService service;
	
//	void 리턴 타입으로 logical view name이 생략된 경우,
//	HandlerAdapte는 RequestToViewNameTranslateor를 이용해 view를 검색함
	
	@RequestMapping("/member/memberView.do")
//	@Override
//	public String memberView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	public String memberView(@RequestParam(value="who", required=true) String memId
	public void memberView(@RequestParam(value="who", required=true) String memId
			, Model model) {
		//처음부터 String memId로 받으면 여기 안에서 memId가 있는지 없는지 확인할 필요가 없다
		//요청 파라미터에서 파람을 뽑고 싶어서 어노테이션을 추가한다
//		1. 요청분석
//		String memId = req.getParameter("who"); //필요한 파라미터가 있다. 이것이 누구의 정체가 된다
//		if(StringUtils.isBlank(memId)) {
//			//아이디가 누락되어있을 때, 누구를 조회할 수 있을지
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return null;
//		} 
//		-- 여기가 사라지면 resp가 필요없게 된다
		//2.
		MemberVO member = service.retrieveMember(memId);
		
		//3.
//		req.setAttribute("member",member);
		model.addAttribute("member", member);
		
		//4. 뷰 선택
		String viewName="member/memberView";		
		
//		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp); 
//		//5. 뷰 이동
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}	

//		return viewName;
	}
}
