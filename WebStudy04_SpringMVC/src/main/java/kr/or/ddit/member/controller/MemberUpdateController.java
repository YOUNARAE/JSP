package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

//@WebServlet("/member/memberUpdate.do")
//서비스와의 의존관계 형성
@Slf4j
@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	
	@Inject
	private MemberService service;

	//수정폼 제공
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	@RequestMapping(value="/member/memberUpdate.do")
	@GetMapping
	public String updateForm(
			Model model
//			, MemberVOWrapper principal 다른 방식을 쓰자.
//			HttpServletRequest req
			, @SessionAttribute("authMember") MemberVO authMember //나만의 리졸브를 추가해보자
			//세션에서 멤버 브이오를 찾아낸다 이 이름으로.
//			, HttpSession session
		) {
		//누구라는 정보 - 세션에 담겨있음
		//String memId = req.getParameter("memId");
//		HttpSession session = req.getSession();
//		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
//		MemberVO authMember = principal.getRealMember();
		//누구라는 정보를 확보한 구조
		MemberVO member = service.retrieveMember(authMember.getMemId());
		//모델 공유
//		req.setAttribute("member", member);
		model.addAttribute("member", member);
		//뷰 선택
		String viewName="member/memberForm";		
		//뷰 이동
		return viewName;
//		new InternalResourceViewResolver("/WEB-INF/viewss/",".jsp").resolveView(viewName, req, resp);
	}

	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	@PostMapping
	public String updateProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
			, BindingResult errors
			, Model model
			//순서가 중요하다
			//처음부터 멤버 브이오를 만들어서 비어있는 곳에 요청값들을 넣어줘서 member로 사용할 수 있게끔 만든다.
//			, @RequestPart(value="memImage", required=false) MultipartFile memImage
			, HttpSession session // 꺼내기만 하는데 세션이 필요없는데, 세션에 넣어줘야한다고 하면 세션이 필요해진다. ex)setAttribute
	) throws IOException { 
		
		boolean valid = !errors.hasErrors(); 
		
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
		
//		member.setMemImage(memImage);
		
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
		
//		boolean valid = ValidationUtils.validate(member, errors, UpdateGroup.class);
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyMember(member);
//		    비밀번호 잘못 입력했을 때, 성공, 실패,
			switch (result) {
			case INVALIDPASSWORD: //비번오류
				model.addAttribute("message", "비밀번호 오류");
				viewName="member/memberForm"; 
//				req.setAttribute("message", "비밀번호 오류");
				break;
			case FAIL: //
//				req.setAttribute("message", "서버 오류, 쫌이따 다시");
				model.addAttribute("message", "서버 오류, 쫌이따 다시");
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