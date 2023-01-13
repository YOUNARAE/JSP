package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("prod/")
public class ProdListController {
	// 비즈니스 레이어 로직과 의존관계가 필요하다, 싱글톤으로 구현해도 되지만 우리가 관리하지 않고 컨테이너를 통해 추구 관리할 것이다.
	@Inject
	private ProdService service;
	@Inject
	private OthersDAO othersDAO;
	
//	private void addAttribute(Model model) {
//		model.addAttribute("lprodList", othersDAO.selectLprodList());
//		model.addAttribute("buyerList", othersDAO.selecttBuyerList(null));
//	}
//	
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>>lprodList() {
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selecttBuyerList(null);
	}
	
	@GetMapping
	public String listUI(Model model){ //대부분의 컨트롤러에서는 resp가 처리가 필요없다 
//		addAttribute(model);		
		return "prod/prodList";
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //클라이언트가 나한테 json을 요구했을 때
//	@RequestMapping(value="/prod/prodList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //클라이언트가 나한테 json을 요구했을 때
	public String listData(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage //필수조건이 아니라는 뜻으로 required
			, @ModelAttribute("detailCondition") ProdVO detailCondition
			, Model model
		) throws ServletException{
		
//		상품하나가 가지고 있는 조건들을 가지고 있다. 
//		ProdVO detailCondition = new ProdVO();
//		model.addAttribute("detailCondition", detailCondition); //jsp에서 복잡하게 꺼내기 싫어서 만듬
		
		//
		PagingVO<ProdVO> pagingVO = new PagingVO<>(5,2);
		pagingVO.setCurrentPage(currentPage); // 첫번째로는 커런트 페이지 한번을 꼭 넣어줘야한다. 
		pagingVO.setDetailCondition(detailCondition); // 상세검색용의 디테일 컨디션을 받을 수 있게 되었음
		
		service.retrieveProdList(pagingVO); //여기에 커런트 페이지를 넣어놓은 pagingVO를 넣어서 이미 startRow와 endRow가 계산이 끝났다.
		model.addAttribute("pagingVO", pagingVO); //setAttribute로 페이징VO를 넣는다
		
		return "jsonView";
	}
}
