package kr.or.ddit.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceLoaderDesc {
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/di/conf/DIContainer-Context.xml");//SpringDIContainer/src/main/resources/kr/or/ddit/di/conf/DIContainer-Context.xml
		Resource resource1 = context.getResource("file:D:/contents/images/cat1.jpg"); //물리주소,파일시스템리소스
//		context.getResource("/SpringDIContainer/src/main/resources/log4j2.xml");
		Resource resource2 = context.getResource("classpath:log4j2.xml");
		//프리픽스만 바꾸면 로더가 알아서 해당 파일 위치에 가서 찾아낸다
		Resource resource3 = context.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		//자원을 찾고 로딩하고 메모리에 올리는 게 쉬워지게 만들어주는 Resource
		
		System.out.printf("file system resource : %s\n", resource1.getClass().getSimpleName());
		System.out.printf("class path resource : %s\n", resource2.getClass().getSimpleName());
		System.out.printf("web resource : %s\n", resource3.getClass().getSimpleName());
		
	}
}
