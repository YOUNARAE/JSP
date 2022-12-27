package kr.or.ddit.designpattern.templatemethodpattern;

import java.util.Arrays;

public class Playground {
	public static void main(String[] args) { // 템플릿 클래스를 통해 접근할 수 있던 메소드는 유일하게 퍼플릭이었던 TemplateClass
		 TemplateClass[] array = new TemplateClass[] { new DerivedClass1(), new DerivedClass2() };
		 Arrays.stream(array)
		 		.forEach(TemplateClass::template); 
		 //다형성을 구현하는 메소드다
	}
}
