package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class BuyerInsertController{
	private BuyerService service = new BuyerServiceImpl();
	private OthersDAO othersDAO = new OtherDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
	}
	
	@RequestMapping("/buyer/buyerInsert.do")
	public String prodForm(HttpServletRequest req){
		addAttribute(req);
		return "buyer/buyerForm";
	}

	@RequestMapping(value="/buyer/buyerInsert.do", method=RequestMethod.POST)
	public String insertProcess(
		@ModelAttribute("buyer") BuyerVO buyer	
		, HttpServletRequest req
	) throws IOException {
		addAttribute(req);
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(buyer, errors, InsertGroup.class);
		String viewName = null;
		if(valid) {
			int rowcnt = service.createBuyer(buyer);
			if(rowcnt > 0) {
				viewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
			}else {
				req.setAttribute("message", "서버 오류, 쫌따 다시");
				viewName = "buyer/buyerForm";
			}
		}else {
			viewName = "buyer/buyerForm";
		}
		return viewName;
	}
}

















