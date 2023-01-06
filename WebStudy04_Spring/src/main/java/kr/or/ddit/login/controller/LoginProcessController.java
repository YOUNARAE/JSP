package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.login.service.AuthuenticateService;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
/**
 * 1. 검증에 통과하지 못했을 경우, 다시 로그인 폼으로 이동함.
 * 2. 인증에 통과하지 못했을 경우, 다시 로그인 폼으로 이동함.
 *    - 비밀번호 오류 상태를 가정하고, 메시지 전달. -> alert 함수로 메시지 출력.
 *    - 이전에 입력받은 아이디의 상태를 유지함. 
 * 3. 인증 완료시에 웰컴 페이지로 이동함.
 * 
 */
@Controller
//@WebServlet("/login/loginProcess.do")
public class LoginProcessController {
	private AuthuenticateService service = new AuthenticateServiceImpl();

	@RequestMapping(value="/login/loginProcess.do", method=RequestMethod.POST)
//	@Override
	public String login(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 회원 로그인 건 확보
//		HttpSession session = req.getSession(); 위에 아규먼트에서 이미 받는다
		if(session.isNew()) {
			//잘못된 요청일 가능성이 높다. 새로운 세션인지를 검증하는 단계
			resp.sendError(400, "로그인 폼이 없는데 어떻게 로그인을 하지??");
			return null;
		}
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		//추가--
		String saveId = req.getParameter("saveId");
		//
		MemberVO member = new MemberVO();
		member.setMemId(memId); // ="memId" 이 두개가 같은 특성을 활용하면 이 코드 하나로 모든 19개의 변수값이 들어오게 할 수 있게 한다
		member.setMemPass(memPass);
		
//		boolean valid = validate(memId, memPass);
		boolean valid = validate(member);
		String viewName = null;
		
		if(valid) {
			try {
				ServiceResult result = service.authenticate(member);
//			2. 인증단계
				if(ServiceResult.OK.equals(result)) { //인증 성공
					//1. 갖고 있는 리퀘스트에 프린시펄을 넣어주는 단계인데 세션이라 리퀘스트에 프린시펄을 넣을 수가 없다.
					
					//추가
					Cookie saveIdCookie = new Cookie("saveId", member.getMemId());
//				ex)www.naver.com
					saveIdCookie.setDomain("localhost");
					saveIdCookie.setPath(req.getContextPath());
					int maxAge = 0; //처음에 0으로 초기화하면 인증을 했을 때 조건을 만족하면 
					
					if(StringUtils.isNotBlank(saveId)) {
						//쿠키는 무조건 만들어져야하고, 맥스에이지가 중요함
						maxAge = 60*60*24*5;
					}
					saveIdCookie.setMaxAge(maxAge);
					resp.addCookie(saveIdCookie);
					
					session.setAttribute("authMember", member);
					viewName = "redirect:/";
				}else {
					session.setAttribute("validId", memId);
					session.setAttribute("message", "비밀번호 오류");
					viewName = "redirect:/login/loginForm.jsp";
				}
			}catch(UserNotFoundException e) {
				session.setAttribute("message", "존재하지 않는 회원입니다");
				viewName = "redirect:/login/loginForm.jsp";
			}
			
		}else {		
			session.setAttribute("message", "아이디나 비밀번호 누락");
			viewName = "redirect:/login/loginForm.jsp";
		}
		
//		5.
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}		
		return viewName;
	}

	private boolean validate(MemberVO member) {
		boolean valid = true;
		//여기서 데이터들을 검증할 수 있는 조건들을 넣어준다
		
//		if(member.getMemId()==null || member.getMemId().isEmpty()) { // 자르파일들을 이용해서 코드를 다시 줄임
		if(StringUtils.isBlank(member.getMemId())) {
			valid  = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) {
			valid  = false;
		}
		// 이게 19개가 반복되어야해서 간단하게 하는 라이브러리가 필요해진다.
		//commons-lang3-3.12.0.jar
		//commons-lang3-3.12.0-javadoc.jar
		//commons-lang3-3.12.0-sources.jar 이거 설치함자바리소스에
		return valid;
	}
}
