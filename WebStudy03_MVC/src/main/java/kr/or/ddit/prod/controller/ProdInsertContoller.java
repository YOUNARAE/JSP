package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertContoller{
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OtherDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selecttBuyerList(null));
	}

	@RequestMapping("/prod/prodInsert.do")
//	@Override
	public String prodForm(HttpServletRequest req) {
		//String이 로지컬 뷰 네임.
		addAttribute(req);	
		return "prod/prodForm"; //suffix,prefix 앞 뒤로 생략되있음
	}
	
	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	public String insertProcess(
		//command Object
		@ModelAttribute("prod") ProdVO prod
		//필요한 건 핸들러 어댑퍼에서 메소드 파라미터로 받는다
		, HttpServletRequest req
	) {	
		addAttribute(req);	
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName=null;
		
		if(valid) { // 통과했을 때
			//이제 통과했다면 등록을 해야한다
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK == result) {
				viewName="redirect:/prod/prodView.do?what="+prod.getProdId(); //신규등록한 걸 확인하고 싶어서
			}else {
				req.setAttribute("message", "서버 오류, 쫌따 다시");
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
