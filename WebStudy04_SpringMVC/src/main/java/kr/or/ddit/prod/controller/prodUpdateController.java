package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/prod/prodUpdate.do")
public class prodUpdateController {

	
	private final ProdService service;
	private final OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>>lprodList() {
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selecttBuyerList(null);
	}
	
	@GetMapping
	public String updateForm(
			@RequestParam("what") String prodId //어떤 상품인지에 대한 정보가 파람으로 받아야한다. 없을 때 400 오류는 핸들러 메서드가 발생시켜준다
			, Model model) {
		
//		addAttribute(req);	
		//ProdVO prod
		ProdVO prod = service.retrieveProd(prodId);
		//여기서 req:핸들러 어댑터가 객체를 넣어주었다.
		model.addAttribute("prod", prod); //여기서 초기값을 셋팅해준다 
		
		return "prod/prodForm";
	}
	
	@PostMapping
	public String updateProcess(
			@Validated(UpdateGroup.class) @ModelAttribute ProdVO prod //Command Object에서 사용할 수 있는 어노테이션이 ModelAttribute
//			, @RequestPart(value="prodImage", required=false) MultipartFile prodImage //클라이언트에게서 멀티파트인 prodImage를 받아와야한다.
			//required는 있을 수도 있고 없을 수도 있다.
			, BindingResult errors
			, Model model 
		) throws IOException {
		
//		addAttribute(req);	
		
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
		
//		prod.setProdImage(prodImage); //이렇게 넣어준다면 있나없나 모두 확인하고 마임타입까지 모두 확인하고 온 것이다. 정상적이면 업로드가 된다.
		
		
//		boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class); //업데이트에서 들어가는 내용인지 검증
		boolean valid = !errors.hasErrors();
		
		String viewName=null;
		
		if(!errors.hasErrors()) { 
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK == result) {
//				viewName="redirect:/prod/prodView.do?what="+prod.getProdId(); //신규등록한 걸 확인하고 싶어서
				viewName="redirect:/prod/"+prod.getProdId(); //신규등록한 걸 확인하고 싶어서
			}else {
				model.addAttribute("message", "서버 오류, 쫌따 다시");
				viewName="prod/prodForm"; 
			}
		} else {
			viewName="prod/prodForm";
		}
		return viewName;
		
	}
}
