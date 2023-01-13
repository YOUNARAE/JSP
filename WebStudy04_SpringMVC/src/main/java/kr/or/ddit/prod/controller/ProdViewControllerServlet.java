package kr.or.ddit.prod.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

/**
 *  상품 상세 조회시, 해당 거래처의 모든 정보 함께 조회함.
 *  상품 하나에 대한 조회시, 구매자 리스트(구매자회원아이디, 회원명, 휴대폰, 이메일, 마일리지) 함께 조회.
 *  분류 명도 함께 조회함.
 *  5개테이블 조인, 회원정보 조회할 때, 주데이터가 멤버에서 상품으로 바뀌었다.
 *  
 */
@Controller
@RequiredArgsConstructor
public class ProdViewControllerServlet {
	
	private final ProdService service;
	
	@RequestMapping("/prod/{prodId}")
	public String prodView(
//						@RequestParam(value="what") String prodId
						@PathVariable String prodId // 클라이언트에게 prodId가 노출되지 않는다. 그래서 그냥 prodId로 써도 된다
						, Model model) {
		
//		1. 파라미터가 있나 없나 확인하는 전형적인 코드
//		String prodId = req.getParameter("what");
//		if(StringUtils.isBlank(prodId)) {
//			resp.sendError(400);
//			return;
//		}
		// 2
		ProdVO prod = service.retrieveProd(prodId);
		// 3
		model.addAttribute("prod", prod);
		
		// 3
		String viewName = "prod/prodView"; //logical view name
		
		return viewName;
		
	}
}
