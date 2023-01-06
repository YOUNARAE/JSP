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
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberUpdate.do")
//서비스와의 의존관계 형성
@Controller
public class MemberUpdateController{
	
	private MemberService service = new MemberServiceImpl();
	
	//수정폼 제공
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	@RequestMapping(value="/member/memberUpdate.do")
	public String updateForm(HttpServletRequest req
//			, @SessionAttribute("authMember") MemberVO authMember //나만의 리졸브를 추가해보자
			, HttpSession session) {
		//누구라는 정보 - 세션에 담겨있음
		//String memId = req.getParameter("memId");
//		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		//누구라는 정보를 확보한 구조
		MemberVO member = service.retrieveMember(authMember.getMemId());
		//모델 공유
		req.setAttribute("member", member);
		//뷰 선택
		String viewName="member/memberForm";		
		//뷰 이동
		return viewName;
//		new InternalResourceViewResolver("/WEB-INF/viewss/",".jsp").resolveView(viewName, req, resp);
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String updateProcess(
			@ModelAttribute("member") MemberVO member
			, HttpServletRequest req
			, @RequestPart(value="memImage", required=false) MultipartFile memImage
			, HttpSession session
	) throws IOException { 
		
		String viewName=null;	
		//한글 요청으로 수정할 수 있어야한다.
//		req.setCharacterEncoding("UTF-8");
		//수정한 글을 받을 그릇을 만든다		
//		MemberVO member = new MemberVO();
//		req.setAttribute("member", member); //어떤 이름으로든 멤버 이름으로 보내야하고, 비번이 오류가 되었을 때 이전 데이터가 그대로 들어가있어야하기때문에
		
//		그 안에 내용을 채워준다
//		try {
//			// 요청을 분석한다.
//			BeanUtils.populate(member, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
		
//230105수정
//		if(req instanceof MultipartHttpServletRequest) {
////			MultipartHttpServletRequest wrapperReq = (MultipartHttpServletRequest) req;
////			
//			MultipartFile memImage = ((MultipartHttpServletRequest) req).getFile("memImage"); //클라이언트가 보내주는 이미지의 이름을 받아야한다.
//			//1. 저장
//			if(memImage!=null && !memImage.isEmpty()) {
//				member.setMemImg(memImage.getBytes()); //이미지로 올라오는 녀석을 받아서 걔를 img로 바꿔주는데
//			}
//		}
		
		member.setMemImage(memImage);
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = ValidationUtils.validate(member, errors, UpdateGroup.class);
		
		if(valid) {
			ServiceResult result = service.modifyMember(member);
//		    비밀번호 잘못 입력했을 때, 성공, 실패,
			switch (result) {
			case INVALIDPASSWORD: //비번오류
				viewName="member/memberForm"; 
				req.setAttribute("message", "비밀번호 오류");
				break;
			case FAIL: //
				req.setAttribute("message", "서버 오류, 쫌이따 다시");
				viewName="member/memberForm"; 
				break;
				
			default:
				MemberVO modifiedMember = service.retrieveMember(member.getMemId()); //수정한 후의 값을 어스멤버에 덮어씌워줘야한다.
				//그래야 수정한 이미지가 바로 뜸.
				session.setAttribute("authMember", modifiedMember);
//				수정이 이미 끝났다. 리다이렉트
				viewName = "redirect:/mypage.do"; //성공했을 때 상황
				break;
			}
			
		}else {
			viewName="member/memberForm"; 
		}
//		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp);
		return viewName;
	}
}
