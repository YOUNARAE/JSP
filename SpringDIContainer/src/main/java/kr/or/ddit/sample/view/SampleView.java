package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import kr.or.ddit.sample.dao.SampleDAOImpl_Postgre;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;

public class SampleView {
	public static void main(String[] args) {
//		SampleDAO dao = new SampleDAOImpl_Postgre();
//		SampleService service = new SampleServiceImpl(dao); //전략의 주입자로 생성자를 이용하고 있었다.
		//전략의 주입자가 될 필요가 없다
		//컨테이너에서 객체를 받아와야한다. 그럴려면 컨테이너에 미리 객체가 생성되어있어야함
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/Sample-context.xml");//스프링 붙이면 new는 거의 없어지는데 여기 어플리케이션 실행할 때 딱1번 나온다
		SampleService service = context.getBean(SampleService.class); //여기에 컨테이너 객체가 있으면 나에게 줘라
		StringBuffer model = service.retrieveInformation("PK_2");
		System.out.println(model);
	}
}
