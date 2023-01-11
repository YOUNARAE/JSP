package kr.or.ddit.login.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@WebServlet("/login/logout.do")
public class LogoutController{
//	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	
//	@Override
//	public String logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	@PostMapping("/login/logout.do")
	public String logout(HttpSession session){ //이 메소드의 호출자는 핸들러어댑터 , 개발자가 필요한 값은 파라미터로 받는다. 핸들러 어댑터로 받게 되는데 그걸 받게 해주는게 핸들러 메소드이다.
//		HttpSession session = req.getSession();
//		session.removeAttribute("authMember");
		session.invalidate(); //명시적인 속성 지우기, 우리가 따로 지우지 않아도 자동으로 지워진다
		
		String viewName = "redirect:/";
		
		return "redirect:/";
		
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}	
	}
}
