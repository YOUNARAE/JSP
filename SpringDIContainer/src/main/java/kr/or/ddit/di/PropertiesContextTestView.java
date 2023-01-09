package kr.or.ddit.di;

import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesContextTestView {
	public static void main(String[] args) {
		System.getProperties().forEach((k,v)->{
			System.out.printf("%s : %s\n", k, v);
		});
		System.getenv().forEach((k,v)->{
			System.err.printf("%s : %s\n", k, v);
		});
		//컨테이너 생성, db1,2를 따로 따로 생성해봐
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/sample/conf/Properties-Context.xml");
		context.registerShutdownHook();
		
		Properties dbInfo = context.getBean("dbInfo",Properties.class);
		log.info("dbInfo : {}", dbInfo);
		DBInfoVO dbInfo1 = context.getBean("dbInfo1",DBInfoVO.class);
		log.info("dbInfo1 : {}", dbInfo1);
		DBInfoVO dbInfo2 = context.getBean("dbInfo2",DBInfoVO.class);
		log.info("dbInfo2 : {}", dbInfo2);
	}
}
