package kr.or.ddit.sample.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SampleController {
	//컨드롤러에 컴포넌트가 붙어있으면 컨트롤러가 컴포넌트를 상속받았다는 의미가 될 수 있다.
	private SampleService service; //우리가 직접 생성하지 않음.의존관계를, 주입받기만 하면 된다.
	
	@Inject //@Autowired보다는 Inject를 권장한다
	public void setService(SampleService service) {
		this.service = service;
		log.info("주입된 service : {}", service);
	}
}
