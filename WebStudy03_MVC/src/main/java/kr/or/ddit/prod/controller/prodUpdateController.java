package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

///prod/prodUpdate.do

@Controller
public class prodUpdateController {

	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OtherDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selecttBuyerList(null));
	}
	
	@RequestMapping(value="/prod/prodUpdate.do")
	public String updateForm(
			@RequestParam("what") String prodId //어떤 상품인지에 대한 정보가 파람으로 받아야한다. 없을 때 400 오류는 핸들러 메서드가 발생시켜준다
			, @ModelAttribute("prod") ProdVO prod 
			, HttpServletRequest req
			, HttpSession session) {
		
		addAttribute(req);	
		//ProdVO prod
		prod = service.retrieveProd(prodId);
		//여기서 req:핸들러 어댑터가 객체를 넣어주었다.
		req.setAttribute("prod", prod); //여기서 초기값을 셋팅해준다 
		
		return "prod/prodForm";
	}
	
	@RequestMapping(value="/prod/prodUpdate.do", method=RequestMethod.POST) //doGet과 중복되니까 멀티밸류 어노테이션을 넣어준다.
	public String updateProcess(
			@ModelAttribute("prod") ProdVO prod //Command Object에서 사용할 수 있는 어노테이션이 ModelAttribute
			, HttpServletRequest req
			, @RequestPart(value="prodImage", required=false) MultipartFile prodImage //클라이언트에게서 멀티파트인 prodImage를 받아와야한다.
			//required는 있을 수도 있고 없을 수도 있다.
			, HttpSession session
		) throws IOException {
		
		addAttribute(req);	
		
//		if(prodImage!=null && !prodImage.isEmpty()) {
//			String saveFolderURL = "/recources/prodImages";
//			ServletContext application = req.getServletContext(); 
//			String saveFolderPath = application.getRealPath(saveFolderURL);
//			File saveFolder = new File(saveFolderPath);
//			if(!saveFolder.exists()) 
//				saveFolder.mkdirs(); 
//			String saveFileName = UUID.randomUUID().toString();
//			prodImage.transferTo(new File(saveFolder, saveFileName));
//			
//			prod.setProdImg(saveFileName);
//		}
		
		prod.setProdImage(prodImage); //이렇게 넣어준다면 있나없나 모두 확인하고 마임타입까지 모두 확인하고 온 것이다. 정상적이면 업로드가 된다.
		
		String saveFolderURL = "/recources/prodImages";
		ServletContext application = req.getServletContext(); 
		String saveFolderPath = application.getRealPath(saveFolderURL);
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) 
				saveFolder.mkdirs(); 

		prod.saveTo(saveFolder);
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors); //위에 HashMap내용을 가지고 가려면 공유를 해야한다.
		// 여기서 ModelAttribute로 받은 ProdVO를 검증한다.
		boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class); //업데이트에서 들어가는 내용인지 검증
		
		String viewName=null;
		
		if(valid) { 
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK == result) {
				viewName="redirect:/prod/prodView.do?what="+prod.getProdId(); //신규등록한 걸 확인하고 싶어서
			}else {
				req.setAttribute("message", "서버 오류, 쫌따 다시");
				viewName="prod/prodForm"; 
			}
		} else {
			viewName="prod/prodForm";
		}
		return viewName;
		
	}
}
