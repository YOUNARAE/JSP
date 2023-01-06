package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;
/**
 * Backend controller
 *
 */
@Controller
//@WebServlet("/member/memberInsert.do")
public class MemberInsertController {
	private MemberService service = new MemberServiceImpl();
	
	
//	@Override
//	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String method = req.getMethod();
//		RequestMethod requestMethod = RequestMethod.valueOf(method.toUpperCase()); //위에 메소드란 녀석을 받아왔다
//		String viewName = null;
////		if("getA".equals(method)) {
//		if(requestMethod==RequestMethod.GET) {
//			viewName = memberForm(req,resp);
//		} else if(requestMethod==RequestMethod.POST) {
//			viewName = memberInsert(req, resp);
//		} else {
//			resp.sendError(405, method+"는 지원하지 않음.");
//		}
//		return viewName;
//	}
	
	
	@RequestMapping("/member/memberInsert.do")
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	public String memberForm() {
		
		//resp과 rep를 뺄 수 없다. 스캔을 해야되기 때문에
		return "member/memberForm";
//		String viewName="member/memberForm";		
//		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp);
//		//5. 뷰 이동
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}	
	}
	

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String memberInsert(
			HttpServletRequest req
			, @ModelAttribute("member") MemberVO member
			, @RequestPart(value="memImage", required=false) MultipartFile memImage
		) throws ServletException, IOException {
		//a 모델확보
//		MemberVO member = getMemberFromRequest(req);
//		int ret = service.createMember(member);
		// 파라미터에 한글이 포함되어있기때문에 인코딩을 해야한다.
//		req.setCharacterEncoding("UTF-8"); 뺴도된다
		// 1. 요청 뿐만 아니라 검증 까지 여기서 할 것이다.
		
		// command object : 검증대상   클라이언트가 명령을 발생시킬 때 그 명령에 관련된 모든 오브젝트를 관리한다는 것.
//		MemberVO member = new MemberVO();// 파라미터들을 받을 그릇이 필요하다.
//		member.setMemId(req.getParameter("memId")); // 이 파라미터 하나가 멤아이디라는 프로퍼티에 들어가야한다.
//		req.setAttribute("member", member);
		
//		검증을 여기서 하면 맵에 내용이 없음
//		Map<String, String[]> parameterMap = req.getParameterMap(); //파라미터는 맵으로 받아진다
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
//		try {
//			BeanUtils.populate(member, parameterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
		//멀티파트 작업을 해야하는데 바이너리 타입으로 저장을 해줘야한다
		
//		if(req instanceof MultipartHttpServletRequest) {
//			MultipartFile memImage = ((MultipartHttpServletRequest) req).getFile("memImage"); //클라이언트가 보내주는 이미지의 이름을 받아야한다.
			member.setMemImage(memImage); //이미지로 올라오는 녀석을 받아서 걔를 img로 바꿔주는데
//		}

		//검증을 이 이후에 해야 맵에 내용들이 들어가있기때문에 검증이 가능하다
			
		//맵이 하나 필요하다
		Map<String, List<String>> errors = new LinkedHashMap<>();
		//맵을 미리 만들어놓고 
		req.setAttribute("errors", errors);
		//미리 공유해놓기
		
		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);
		
		String viewName=null;
		
		if(valid) { // 통과했을 때
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED: //아이디가 중복되서 돌아가는 케이슨
				req.setAttribute("message", "아이디 중복");
				viewName="member/memberForm"; //앞에 리다이렉트가 없어서 디스패치로 가기때문에 입력하던 데이터가 그대로 유지될 수 있다.
				break;
			case FAIL:
				req.setAttribute("message", "서버에 문제가 있음. 좀이따 다시 하셈.");
				viewName="member/memberForm"; 
				break;
				
			default:
				viewName = "redirect:/"; //성공했을 때 상황
				break;
			}
		} else {
			//통과 안됐을 때 에러스와 기존의 데이터를 가지고 가야한다.
			
			viewName="member/memberForm";
		}
				
		
//		//5. 뷰 이동
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}	
//		뺴도된다 new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp); //포워드로 사용할때만이라서 
		return viewName;
	}
}
