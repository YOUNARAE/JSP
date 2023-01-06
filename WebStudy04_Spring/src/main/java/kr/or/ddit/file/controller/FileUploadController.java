package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class FileUploadController {
	
	//실제 핸들러 메소드
	@RequestMapping(value="/file/upload.do", method=RequestMethod.POST)
//	@PostMapping("/file/upload.do")
	public String upload(HttpServletRequest req, HttpSession session) throws IOException, ServletException {
		//5개의 파트를 가지고 있는 리퀘스트의 바디를 받는다. 필요한 건 어댑터에서
		//version3
		String textPart = req.getParameter("textPart");
		String numPart = req.getParameter("numPart");
		String datePart = req.getParameter("datePart");
		log.info("textPart : {}", textPart);
		log.info("numPart : {}", numPart);
		log.info("datePart : {}", datePart);
		session.setAttribute("textPart", textPart);
		session.setAttribute("numPart", numPart);
		session.setAttribute("datePart", datePart);
		
//		진짜 경로가 아닌 논리적인 경로 : /resources/prodImages
		String saveFolderURL = "/resources/prodImages";
		ServletContext application = req.getServletContext(); //어플리케이션의 기본객체가 들어옴
		String saveFolderPath = application.getRealPath(saveFolderURL); //이게 폴더의 진짜 경로 , 여기에 저장하자라는 걸 결정함.
		//저장할 떄 쓸 수 있는 이름이 필요하다
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) {//이 폴더가 존재하지 않으면 만들어라는 - 
			saveFolder.mkdirs(); //s를 사용해야 경로가 쭉 만들어진
		}
		
		List<String> metadata = req.getParts().stream()
					.filter((p)->p.getContentType()!=null && p.getContentType().startsWith("image/")) // filter를 사용하면 조건에 맞는 엘리먼트를 걸러낼 수가 있다, 5개 다 뜨던 문자파트는 걸러냈음
//		.forEach(System.out::println);
					.map((p)->{
						String originalFilename = p.getSubmittedFileName();		 
						//원본파일명으로는 절대 저장하지 않는다는 원칙. 백도어의 공격을 당할 수 있고, 사용자끼리 이름을 겹치게 쓸 수도 있기 때문에 , originalFilename로는 저장하면 안된다는 의미.
						String saveFilename = UUID.randomUUID().toString(); // 저장파일명을 만들었다(웬만하면 겹치지 않는 16진수의 이름)
						File saveFile = new File(saveFolder, saveFilename); //여기서 카피를 떠야한다.
						//생략되는 부분 찾아보기
						try {
							p.write(saveFile.getCanonicalPath()); //이 안에서 스트링 카피를 뜨고 있는 것이라 오류가 난다. 저장경로가 포함된 파일 네임을 달라는 의미
							String saveFileURL = saveFolderURL + "/" + saveFilename;//resources/prodImages/어쩌구저쩌구
							//map을 사용해서 타입을 바꿀 수 있게됨
							return saveFileURL; //문자열이 반환되는 걸로 바뀜.
						} catch (IOException e) {
							throw new RuntimeException(e);
						} //이미지 파일 2개에 대한 메타데이터를 찾는 처리
					}).collect(Collectors.toList()); //여기서 찾게된 2개의 메타데이터 + 위에 더해진 3개 총 5개
		//세션 스코프가 필요해져서 위에 어규먼트에 세션을 추가했다.
		session.setAttribute("fileMetadata", metadata);
		
		
		return "redirect:/fileupload/uploadForm.jsp";
	}
}
