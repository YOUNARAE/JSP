package kr.or.ddit.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CalculatorController {
	
	@RequestMapping(method=RequestMethod.GET, value="/caluate")
	public String calForm() {
		return "cal/form";
	}

	@RequestMapping(method=RequestMethod.POST, value="/caluate")
	public String calProcessCase4( 
			@RequestParam int left 
			, @RequestParam int right
			, HttpSession session
			, Model model //이것만 req에서 model로 바뀜
	) throws StreamWriteException, DatabindException, IOException {
		int result = left + right; 
//		Map<String, Object> target = Collections.singletonMap("result", result);
		model.addAttribute("result", result);//req.setAttribute와 마찬가지다.
		return "jsonView"; //마샬링컨트롤러가 없다
	}
	
	
//	@RequestMapping(method=RequestMethod.POST, value="/caluate")
	@ResponseBody 
	public Map<String, Object> calProcessCase3( //return 타입이 String에서 void로 바껴도 상관없다
			@RequestParam int left, 
			@RequestParam int right, 
			HttpSession session
	) throws StreamWriteException, DatabindException, IOException {
		int result = left + right; 
		Map<String, Object> target = Collections.singletonMap("result", result);
		return target;
		//뷰에 대한 정보가 없다. 뷰에 대한 정보는 새로운 어노테이션을 통해 정보를 준다
	}
	
	
//	@RequestMapping(method=RequestMethod.POST, value="/caluate")
	public void calProcessCase2( //return 타입이 String에서 void로 바껴도 상관없다
			@RequestParam int left
			, @RequestParam int right
			, HttpSession session
			, OutputStream os
	) throws StreamWriteException, DatabindException, IOException {
		int result = left + right; //result -> 우리가 사용할 모델
		Map<String, Object> target = Collections.singletonMap("result", result);
//		이동하는 방식, 이미 연산 명령이 끝난 정보를 넘길 필요가 없다
//		폼으로 가더라도 
		session.setAttribute("result", result);
		ObjectMapper mapper = new ObjectMapper();
		// resp가 필요해졌다
		mapper.writeValue(os, target);
	}
		
		
//	@RequestMapping(method=RequestMethod.POST, value="/caluate")
//	public calProcess(@RequestParam("left") int left)
	public void calProcessCase1( //return 타입이 String에서 void로 바껴도 상관없다
			@RequestParam int left, 
			@RequestParam int right, 
			HttpSession session,
			HttpServletResponse resp
	) throws StreamWriteException, DatabindException, IOException {
		int result = left + right; //result -> 우리가 사용할 모델
		Map<String, Object> target = Collections.singletonMap("result", result);
//			이동하는 방식, 이미 연산 명령이 끝난 정보를 넘길 필요가 없다
//			폼으로 가더라도 
		session.setAttribute("result", result);
		//result는 숫자이고 인트형이라서 json화 시킬 수 없기때문에 컬렉션을 만든다
		//이름과 값을 갖고 있느 컬렉션을 만든다
		//1. 마샬링
		//2. 직렬화
		ObjectMapper mapper = new ObjectMapper();
		// resp가 필요해졌다
		mapper.writeValue(resp.getOutputStream(), target);
		//write계열 마샬링(직렬화), read->언마샬링계열
		
//			return "redirect:/caluate";
		
	}
}
