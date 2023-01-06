package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.mvc.multipart.MultipartHttpServletRequest;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidationUtils;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
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
		, HttpServletRequest req //이게 원본인지 wrapper인지 확인해야한다.
		, @RequestPart("prodImage") MultipartFile prodImage //사진이 꼭 있어야해서 싱글밸류로 쓴다
	) throws IOException, ServletException {	
		addAttribute(req);	

//		if(req instanceof MultipartHttpServletRequest) {
//			//클라이언트는 prodImage로 입력하는데 그 이름을 저장하려고 해서 prodImg로 만들어줘야하는 과정을 여기에 적어줘야한다.
//			//이 안에 들어온다는 건 변경된 wrapper라는 의미.
//			MultipartHttpServletRequest wrapperReq = (MultipartHttpServletRequest) req;
////			wrapperReq.getF
//			MultipartFile prodImage = wrapperReq.getFile("prodImage"); //클라이언트가 보내주는 이미지의 이름을 받아야한다.
			//prodImage->prodImg
		
			prod.setProdImage(prodImage); //이렇게 넣어준다면 있나없나 모두 확인하고 마임타입까지 모두 확인하고 온 것이다. 정상적이면 업로드가 된다.
			
			String saveFileName = prod.getProdImg();
			
//			if(saveFileName!=null) {//널도 아니고 비어있지도 않은지 확인되야 이미지를 업로드한다 , VO에서 작업해줘서 이프문도 다 사라졌다.
//			if(prodImage!=null && !prodImage.isEmpty()) {//널도 아니고 비어있지도 않은지 확인되야 이미지를 업로드한다 
//			1.저장
				String saveFolderURL = "/recources/prodImages";
				ServletContext application = req.getServletContext(); 
				String saveFolderPath = application.getRealPath(saveFolderURL);
				File saveFolder = new File(saveFolderPath);
				if(!saveFolder.exists()) 
					saveFolder.mkdirs(); 
				//상품을 수정을 할 때 바꾸고 싶을 수도 안 바꾸고 싶을 수도 있다.
//				2.metadata 추출
//				String saveFileName = UUID.randomUUID().toString(); // 위에 셋터에서 이미 만들어지고 있어서 저장명을 여기서 할 필요가 없다
//				prodImage.transferTo(new File(saveFolder, saveFileName)); //VO에서 저장하고 메타데이터 추출하는 거 다 했음
				
//				3.DB저장
//				prod.setProdImg(saveFileName);
				prod.saveTo(saveFolder);
//			}
//		}
		
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
