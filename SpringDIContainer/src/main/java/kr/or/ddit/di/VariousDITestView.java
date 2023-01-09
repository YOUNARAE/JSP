package kr.or.ddit.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariousDITestView {
	public static void main(String[] args) {
		//컨테이너 객체 생성, 컨테이너는 필요없으면 자동으로 소멸할 수 있는 구조, 컨테이너의 모든 빈들은 라이프 사이클 콜백을 가지고 있어야한다., 초기화, 소멸 로그를 갖고 있어야함.
		//현재 빈을 메서드에서 주입을 받는다.
//		VariousDIVO divo = new VariousDIVO();
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/di/conf/VariousDI-Context.xml");
//		VariousDIVO divo = context.getBean(VariousDIVO.class);
		context.registerShutdownHook();
		
		VariousDIVO vo1 = context.getBean("vo1",VariousDIVO.class);
		VariousDIVO vo2 = context.getBean("vo2",VariousDIVO.class);
		log.info("주입된 객체 : {}", vo1);
		log.info("주입된 객체 : {}", vo2);
	}
}
