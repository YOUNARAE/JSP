package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertContoller{
	
	@Inject
	private ProdService service;
	@Inject
	private OthersDAO othersDAO;

//	private void addAttribute(HttpServletRequest req) {
//		req.setAttribute("lprodList", othersDAO.selectLprodList());
//		req.setAttribute("buyerList", othersDAO.selecttBuyerList(null));
//	}
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>>lprodList() {
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selecttBuyerList(null);
	}
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	} //객체를 미리 만들어놓는다. 객체의 재활용 개념으로도 좋음
	
	@GetMapping
	public String prodForm(
	) {
//		addAttribute(req);	
		return "prod/prodForm"; //suffix,prefix 앞 뒤로 생략되있음
	}
	
//	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	@PostMapping
	public String insertProcess(
//		command Object
//		필요한 건 핸들러 어댑퍼에서 메소드 파라미터로 받는다
		@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
		, Errors errors
		, Model model
//		, @RequestPart("prodImage") MultipartFile prodImage //받을 필요 없어짐
	) throws IOException, ServletException {	
		
//		prod.setProdImage(prodImage);
// 스프링은 그 자체가 리소스 폴더가 된다. 자기 자신에 객체가 있고 		
//		String saveFileName = prod.getProdImg();

			
		boolean valid = !errors.hasErrors();
				
		String viewName=null;
		
		if(!errors.hasErrors()) { // 통과했을 때
			//이제 통과했다면 등록을 해야한다
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK == result) {
				viewName="redirect:/prod/"+prod.getProdId(); //신규등록한 걸 확인하고 싶어서
			}else {
				model.addAttribute("message", "서버 오류, 쫌따 다시");
				viewName="prod/prodForm"; 
			}
			//케이스가 멤버인서트에서 3개에서 2개로 줄어들었다
		} else {
			//검증을 통과를 못했다는 의미
			viewName="prod/prodForm";
		}
		return viewName;
	}
		
}
