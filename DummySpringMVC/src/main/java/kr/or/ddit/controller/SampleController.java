package kr.or.ddit.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.SampleService;

@Controller
public class SampleController {
	@Inject
	private SampleService service;
	
	@RequestMapping("/sample2")
	public String commandHandler(HttpServletRequest req) {
		String info = service.retrieveInfo();
		req.setAttribute("info", info);
		return "sample/view2";
	}
	//실제핸들러는 메소드가 된다.
	//실제핸들러로 수집되야함
	@RequestMapping(value="/sample", method=RequestMethod.GET) //이요청을 통해 이 메서드를 받겠다는 의미
	public String sample(HttpServletRequest req) { 
		//로지컬 뷰네임을 리턴받아야해서 String 타입이다
		//필요한 건 핸들러어댑터에게 어규먼트 메서드를 받을 수 있다
		req.setAttribute("now", new Date());
		//로지컬 뷰 네임을 반환하기 전에 모델을 만들고 뷰에대한 정보를 만들어줘야한다.
		return "sample/view";
	}
}
