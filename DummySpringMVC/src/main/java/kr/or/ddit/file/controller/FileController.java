package kr.or.ddit.file.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.FileTestVO;

@Controller
@RequestMapping("/fileUpload")
public class FileController {
	@Inject
//	private ApplicationContext context;
	private WebApplicationContext context;
	private Resource saveFolderRes; //저장위치
	private File saveFolder;
	
	
	@PostConstruct //객체 생성 이후에 동작을 한다는 뜻
	private void init() throws IOException {
		saveFolderRes = context.getResource("classpath:kr/or/ddit/file");
		saveFolder = saveFolderRes.getFile();		
	}	

	
	@GetMapping
	public String fileForm() {
		return "file/uploadForm";
	}
	
	
	@PostMapping
	public String fileProcessCase2(
			RedirectAttributes redirectAttributes
			, @ModelAttribute("fileVO") FileTestVO commandObject) throws IllegalStateException, IOException {
		//FileTestVO에 파트 데이터와 메타 데이터들이 들어있는 상태다
		commandObject.file1SaveTo(saveFolder);
		commandObject.file2SaveTo(saveFolder);
		redirectAttributes.addFlashAttribute("result", commandObject);
		return "redirect:/fileUpload";
	}
	

	
//	낱개의 데이터를 각자 받음 
//	@PostMapping
	public String fileProcessCase1(
			@RequestParam String textParam
			, @RequestParam String dateParam
			, @RequestPart MultipartFile file1
			, @RequestPart MultipartFile[] file2
//			, HttpSession session
			, RedirectAttributes redirectAttributes //리다이렉트를 사용할 때 속성을 공유할 때 쓰는 API
	) throws IllegalStateException, IOException {
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("textParam", textParam);
		result.put("dateParam", dateParam);
		
		if(!file1.isEmpty()) {
			File dest = new File(saveFolder, UUID.randomUUID().toString());
			file1.transferTo(dest);
			result.put("file1", Collections.singletonMap("savename", dest.getName()));
		}

		result.put("file2", Arrays.stream(file2)
				.filter((f)->!f.isEmpty()) //엘리먼트 1개는 파일 1개이다.
				.map((f)->{
					try {
					File dest = new File(saveFolder, UUID.randomUUID().toString());
					f.transferTo(dest);
					return Collections.singletonMap("savename", dest.getName());
					}catch(IOException e) {
						throw new RuntimeException(e);
					}
				}).collect(Collectors.toList())
			);
//		session.setAttribute("result", result); //세션 사용
		redirectAttributes.addFlashAttribute("result", result); //얘는 꺼내자마자 바로 삭제되서 remove가 될 필요가 없다
		return "redirect:/fileUpload";
	}
}
