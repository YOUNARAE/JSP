package kr.or.ddit.memo;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.memo.conf.MemoContextConfiguration;
import  kr.or.ddit.memo.service.MemoService;

@Controller
public class MemoTestView {
	private MemoService service; //다시 뷰가 서비스에 의존하게 됨

	@Required
	@Inject
	public void setMemoService(MemoService service) {
		this.service = service;
	}
	
	public void printMemoList() {
		service.retrieveMemoList().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
////		MemoService service = new MemoService();
////		service.retrieveMemoList().forEach(System.out::println);
////		차후에 자동으로 종료될 수 있는 조건으로 넣어야한다
//		ConfigurableApplicationContext context = 
//				new GenericXmlApplicationContext("classpath:kr/or/ddit/memo/conf/auto/root-context.xml");
////		서로 다른 구현체를 생성해준다 어떤 게 앞에 붙었냐에 따라서
//		context.registerShutdownHook(); 
//		ConfigurableApplicationContext childContext = 
//				new ClassPathXmlApplicationContext(
//						new String[] {"kr/or/ddit/memo/conf/auto/servlet-context.xml"}
//						, context
//					);
////		서로 다른 구현체를 생성해준다 어떤 게 앞에 붙었냐에 따라서
//		context.registerShutdownHook(); 
//		childContext.registerShutdownHook(); 
		
		ConfigurableApplicationContext context = 
				new AnnotationConfigApplicationContext(MemoContextConfiguration.class);
		context.registerShutdownHook();
		
//		MemoTestView view = childContext.getBean(MemoTestView.class);
		MemoTestView view = context.getBean(MemoTestView.class);
		view.printMemoList();
	}

}
