package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class SampleView {
	public static void main(String[] args) {
//		SampleDAO dao = new SampleDAOImpl_Postgre();
//		SampleService service = new SampleServiceImpl(dao);

		// 컨테이너에서 주입받음(유일한 new : 컨테이너 객체 생성할 때 사용)
		// XML의 위치필요
		ApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/Sample-context.xml");
		// SampleService 인터페이스 구현체가 있으면 주입
		SampleService service = context.getBean(SampleService.class);
		StringBuffer model = service.retrieveInformation("PK_2");
		System.out.println(model);
	}
}
