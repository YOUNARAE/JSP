package kr.or.ddit.prod.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.mvc.AbstractController;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemoVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
//@WebServlet("/prod/prodList.do")
//public class ProdListControllerServlet extends HttpServlet {
public class ProdListController implements AbstractController{
	// 비즈니스 레이어 로직과 의존관계가 필요하다, 싱글톤으로 구현해도 되지만 우리가 관리하지 않고 컨테이너를 통해 추구 관리할 것이다.
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OtherDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selecttBuyerList(null));
	}
	
	private String listUI(HttpServletRequest req, HttpServletResponse resp){
		addAttribute(req);		
		return "prod/prodList";
	}
	
	private String listData(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		String pageParam = req.getParameter("page");
//		String searchType = req.getParameter("searchType"); //검색 안해서 없어도 됨.
//		String searchWord = req.getParameter("searchWord");
//		SearchVO simpleCondition = new SearchVO(searchType, searchWord); //생성자로 2가지 조건을 갖고 있는 파라미터를 받는다->이걸 페이징 VO에 넣어줘야한다.
		
		//상품하나가 가지고 있는 조건들을 가지고 있다. 
		ProdVO detailCondition = new ProdVO();
		req.setAttribute("detailCondition", detailCondition); //jsp에서 복잡하게 꺼내기 싫어서 만듬
//		detailCondition.setProdLgu(req.getParameter("prodLgu"));
//		detailCondition.setProdBuyer(req.getParameter("prodBuyer"));
//		detailCondition.setProdName(req.getParameter("prodName"));
		
		
		try {
			BeanUtils.populate(detailCondition, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		} //검색조건이 아무리 늘어도 prod 안에서 있으니까 맵으로 한꺼번에 넘긴다.
		
		int currentPage = 1; //page에 페이지가 없으면 1페이지이다.
		if(StringUtils.isNumeric(pageParam)) { // 뉴머릭 : 값을 확인하는 용도로 쓴다.
			currentPage = Integer.parseInt(pageParam);
		}
		
		//
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5,2);
		pagingVO.setCurrentPage(currentPage); // 첫번째로는 커런트 페이지 한번을 꼭 넣어줘야한다. 
//		pagingVO.setSimpleCondition(simpleCondition); //페이징VO에 페이징과 관련된 검색 조건까지 받을 수 있게 되었다
		pagingVO.setDetailCondition(detailCondition); // 상세검색용의 디테일 컨디션을 받을 수 있게 되었음
		
		service.retrieveProdList(pagingVO); //여기에 커런트 페이지를 넣어놓은 pagingVO를 넣어서 이미 startRow와 endRow가 계산이 끝났다.
		req.setAttribute("pagingVO", pagingVO); //setAttribute로 페이징VO를 넣는다
		//제이슨뷰는 또다른 서블릿에서 또 다른 서블릿으로 태우기 위한 코드
		return "forward:/jsonView.do";
	}
	
	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");  // 프론트에서 갖고 있어서 가질 필요가 없다
		
		String accept = req.getHeader("Accept"); //여기에 json이라는 키워드가 있을 수도 있고 없을 수도 있다.
		String viewName = null;
		if(accept.contains("json")) {
			//2 데이터가 필요할 때 구조
			viewName = listData(req,resp);
		} else {
			//1 ui가 필요할 때 구조
			viewName = listUI(req, resp);	
		}
//		String viewName="prod/prodList";	// 4. 로지컬 뷰 네임 결정
//		new InternalResourceViewResolver("/WEB-INF/views/",".jsp").resolveView(viewName, req, resp); //포워드로 사용할때만이라서 // 프론트에서 갖고 있어서 가질 필요가 없다
		return viewName;
		
	}
	
}
