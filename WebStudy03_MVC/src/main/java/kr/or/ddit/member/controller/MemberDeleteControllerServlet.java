package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		// 1.요청분석
		HttpSession session = req.getSession(); //누구인지를 꺼낸다.
		MemberVO authMember = (MemberVO) session.getAttribute("authMember"); //로그인을 한 것과 같은 비슷한 효과를 냈다.
//		req.getUserPrincipal(); // 세션이 실제로 인증이 되어 있다면 여기서 null이 들어오면 안된다.
		//반드시 있어야만 하는 데이터들
		String memId = authMember.getMemId(); //현재 로그인된 아이디를 가져옴
		String memPass = req.getParameter("memPass");//클라이언트가 입력한 비밀번호를 memPass로 꺼내준다
		
		//모델확보
//		MemberVO member = new MemberVO();
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);
		
//		//모델 공유
//		member.setMemId(memId);
//		member.setMemPass(memPass);
//				
//		//검증을 여기서 하면 맵에 내용이 없음
//		Map<String, String[]> parameterMap = req.getParameterMap(); //파라미터는 맵으로 받아진다
		
		//맵이 하나 필요하다
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = ValidationUtils.validate(inputData, errors, DeleteGroup.class);
		
		String viewName=null;
		
		if(valid) {
			// 모델확보
			ServiceResult result = service.removeMember(inputData);
			// 3가지 경우의 수
			// 존재하지 않을때는 고려하지 않음
			switch (result) {
			case INVALIDPASSWORD:
				session.setAttribute("message", "비번 오류");
				viewName = "redirect:/mypage.do";
				break;
			case FAIL:
				session.setAttribute("message", "서버 오류");
				viewName = "redirect:/mypage.do";
				break;
				
			default :
				session.invalidate();
				viewName = "redirect:/";
				break;
			}
			//
		}else {
			session.setAttribute("message", "아이디나 비밀번호 누락");
			viewName = "redirect:/mypage.do";
		}
		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp);




		//맵을 미리 만들어놓고 
//		req.setAttribute("errors", errors);
//		//미리 공유해놓기
//		
//		try {
//			// 요청을 분석한다.
//			BeanUtils.populate(member, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
//		

//		String viewName=null;
//		//뷰이동
//		if(valid) { // 통과했을 때
//			ServiceResult result = service.removeMember(member);
//			switch (result) {
//			case PKDUPLICATED: //아이디가 중복되서 돌아가는 케이슨
//				req.setAttribute("message", "아이디 중복");
//				viewName="member/memberView"; //앞에 리다이렉트가 없어서 디스패치로 가기때문에 입력하던 데이터가 그대로 유지될 수 있다.
//				break;
//			case FAIL:
//				req.setAttribute("message", "서버에 문제가 있음. 좀이따 다시 하셈.");
//				viewName="member/memberView"; 
//				break;
//				
//			default:
//				viewName = "redirect:/member/memberList.do"; //성공했을 때 상황
//				break;
//			}
//		} else {			
//			viewName="member/memberView";
//		}
		
	}
	
}
