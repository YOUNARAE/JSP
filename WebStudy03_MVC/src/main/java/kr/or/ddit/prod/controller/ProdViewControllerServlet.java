package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

/**
 *  상품 상세 조회시, 해당 거래처의 모든 정보 함께 조회함.
 *  상품 하나에 대한 조회시, 구매자 리스트(구매자회원아이디, 회원명, 휴대폰, 이메일, 마일리지) 함께 조회.
 *  분류 명도 함께 조회함.
 *  5개테이블 조인, 회원정보 조회할 때, 주데이터가 멤버에서 상품으로 바뀌었다.
 *  
 */
@WebServlet("/prod/prodView.do")
public class ProdViewControllerServlet extends HttpServlet{
	
	private ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 파라미터가 있나 없나 확인하는 전형적인 코드
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400);
			return;
		}
		// 2
		ProdVO prod = service.retrieveProd(prodId);
		// 3
		req.setAttribute("prod", prod);
		
		// 3
		String viewName = "prod/prodView"; //logical view name
		
		new InternalResourceViewResolver("/WEB-INF/views/", ".jsp").resolveView(viewName, req, resp);
		
	}
}
