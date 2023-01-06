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
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller //프론트뷰에서 실행되는 핸들러가 된다
//@WebServlet("/member/memberList.do")
public class MemberListController{ //완전한 POJO
//	의존관계(점선)을 형성해준다
	private MemberService service = new MemberServiceImpl();
	
//	@Override
//	public String memberList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	@RequestMapping("/member/memberList.do") //싱글어노테이션이고 겟메소드가 기본으로 숨어있다.
	public String memberList(
//			@RequestParam("page") int currentPage 이렇게 쓰면 페이지가 필수 파라미터가 된다
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute SearchVO simpleCondition
//			, @RequestParam(value="searchType", required=false) String searchType
//			, @RequestParam(value="searchWord", required=false) String searchWord
			, HttpServletRequest req //요청을 보내기 위한 리퀘스트를 마지막으로 파라미터로 넣어준다
		) {
//	resp는 사용하지 않지만 가지고 있다. resp를 안 쓰면 예외를 던질필요는 없는데, 던지고 있다
//	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//검색으로 한글이 들어오기때문에 한글 인코딩 먼저 해본다
//		req.setCharacterEncoding("UTF-8");
		
//		pagingVO.setCurrentPage(1); //
//		여기에 요청을 분석하는 소스로 바껴야함
//		String pageParam = req.getParameter("page");
//		String searchType = req.getParameter("searchType");
//		String searchWord = req.getParameter("searchWord");
		//검색은 할 수도 있고 안할 수도 있기때문에 검증의 대상이 되지 않는다. r검색조건에 맞는 걸 찾기 위해서 ㄴpagingVO까지 가야하는데,
		
//		SearchVO simpleCondition = new SearchVO(searchType, searchWord);
		
		
//		int currentPage = 1;
//		if(StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		} 
		
		//요청을 받아야함
		PagingVO<MemberVO> pagingVO = new PagingVO<>(4,2); //모든 정보를 갖고 있는 것은 pagingVO
		pagingVO.setCurrentPage(currentPage); //페이징처리 값을 받아서 처리할 수 있도록 하는 솟,
		pagingVO.setSimpleCondition(simpleCondition); //심플컨디션에 데이터를 조회할 수 있는 조건까지 들어갔음.
		
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO); //리트리브멤버리스트를 통해 리스트를 받아온다
//		req.setAttribute("memberList", memberList); //내용을 넣어준다.(멤버리스트) //여기서 리스트만 넣고 페이징 처리를 안해서 보여줄 수가 없다
		req.setAttribute("pagingVO", pagingVO);
		
		log.info("paging data : {}", pagingVO);
//		4. 뷰네임결정
		String viewName="member/memberList";	
		
//		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp); //포워드로 사용할때만이라서 
////		5.
//		if(viewName.startsWith("redirect:")) {
//			viewName = viewName.substring("redirect:".length());
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}	
		return viewName;
	}
}
