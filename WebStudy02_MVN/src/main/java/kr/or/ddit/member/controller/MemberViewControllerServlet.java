package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet{
//	서비스와 의존관계(점선)을 형성해준다
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청분석
		String memId = req.getParameter("who"); //필요한 파라미터가 있다. 이것이 누구의 정체가 된다
		if(StringUtils.isBlank(memId)) {
			//아이디가 누락되어있을 때, 누구를 조회할 수 있을지
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		//2.
		MemberVO member = service.retrieveMember(memId);
		
		//3.
		req.setAttribute("member",member);
		
		//4. 뷰 선택
		String viewName="member/memberView";		
		
		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp); 
//		//5. 뷰 이동
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}	
	}
}
