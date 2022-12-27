package kr.or.ddit.member.controller;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemoVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String viewName="/WEB-INF/views/member/memberForm.jsp";		
		//5. 뷰 이동
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//a 모델확보
//		MemberVO member = getMemberFromRequest(req);
//		int ret = service.createMember(member);
		// 파라미터에 한글이 포함되어있기때문에 인코딩을 해야한다.
		req.setCharacterEncoding("UTF-8");
		
		MemberVO member = new MemberVO();// 파라미터들을 받을 그릇이 필요하다.
//		member.setMemId(req.getParameter("memId")); // 이 파라미터 하나가 멤아이디라는 프로퍼티에 들어가야한다.
		req.setAttribute("member", member);
		
		Map<String, String[]> parameterMap = req.getParameterMap(); //파라미터는 맵으로 받아진다
//		parameterMap.entrySet().stream()
//					.forEach(entry->{
//						//엔트리에서 키를 꺼내면 그게 파라미터의 이름이 된다
//						String paramName = entry.getKey();
////						MemberVO.class.getDeclaredField(paramName);
//						PropertyDescriptor pd = new PropertyDescriptor(paramName, MemberVO.class); //파라미터의 이름을 넘기면 그 이름하고 똑같은 이름을 가져올 수 있다.
//						Method setter = pd.getWriteMethod(); 
//						// 지금 한 과정을 리플렉션이라고 한다.
//						//중프때 쓴 빈 유틸스
//					});
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		String viewName=null;		
		
		
		ServiceResult result = service.createMember(member);
		switch (result) {
		case PKDUPLICATED: //아이디가 중복되서 돌아가는 케이슨
			req.setAttribute("message", "아이디 중복");
			viewName="/WEB-INF/views/member/memberForm.jsp"; //앞에 리다이렉트가 없어서 디스패치로 가기때문에 입력하던 데이터가 그대로 유지될 수 있다.
			break;
		case FAIL:
			req.setAttribute("message", "서버에 문제가 있음. 좀이따 다시 하셈.");
			viewName="/WEB-INF/views/member/memberForm.jsp"; 
			break;
			
		default:
			viewName = "redirect:/"; //성공했을 때 상황
			break;
		}
		
		//5. 뷰 이동
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}	
	}
}
