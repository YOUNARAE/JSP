package kr.or.ddit.object;

import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

public class ObjectPlayground {
	public static void main(String[] args) throws Exception {
		
		//클래스가 어떤 형태인지, 인스턴스가 어떻게 생성되는지, 객체가 어떤 상태로 생기는지에 대해 알아봄
		
		String qualifiedName = "kr.or.ddit.object.Parent";
		Class<?> type1 = Parent.class; //대문자 패런츠에 클래스에 인스턴스에 접근하는 객체
		Class<?> type2 = Class.forName(qualifiedName); // 클래스의 포네임의 반환되는 리턴 타입은 클래스
		// 타입1와 타입2는 다른 애가 아니라 같은애다.
		// 클래스는 힙에 적재되지 않고 상수 메모리에 적재된다(같은 클래스에 대해 2번 이상 저장하지 않음) -> 버추얼머신 자체에 들어가려고 jar를 설치
		VirtualMachine vm = VM.current();
		System.out.printf("type1 address : %d \n", vm.addressOf(type1));
		System.out.printf("type2 address : %d \n", vm.addressOf(type2)); 
		// 주소값이 같다. 
		
		//인스턴스생성
		Parent parent1 = new Parent(); // 패런트라는 객체 생성
		//리플렉션 구조를 사용한다. 생성자를 안 쓰고
		Object parent2 = type1.newInstance();
		System.out.printf("parent1 address : %d \n", vm.addressOf(parent1));
		System.out.printf("parent2 address : %d \n", vm.addressOf(parent2));
		
		System.out.printf("parent1 == parent2 : %b \n", parent1==parent2);
		System.out.printf("parent1.equals(parent2) : %b \n", parent1.equals(parent2)); // 패런트를 만들때 이퀄스를 오버라이딩하지 않았기때문에
	
		int number1 = 20; //상수공간에 적재됨
		int number2 = 20; 
		System.out.printf("number1 address : %d \n", vm.addressOf(number1));
		System.out.printf("number2 address : %d \n", vm.addressOf(number2));
		//변수가 가지고 있는 값이 같으면 같은 공간을 바라보고 있다는 의미
		
		StringBuffer sb1 = new StringBuffer("ORIGINAL"); //힙 메모리에 적재됨. 
		StringBuffer sb2 = new StringBuffer("ORIGINAL");
		System.out.printf("sb1 address : %d \n", vm.addressOf(sb1));
		System.out.printf("sb2 address : %d \n", vm.addressOf(sb2));
		
		number1 = sample(number1, sb1);
		System.out.printf("number1 : %d \n", number1 ); // call by value
		System.out.printf("sb1 : %s \n", sb1 );  // call by reference
		
		// 대조 질문 중에 잘 나오는 것 중에 하나 call by value,reference,
		// 이퀄 연산자와 == 연산자 
		
		Child child = new Child(); //패런트로 상속받아 만듬
		child.template(); //상속받아서 이 메서드를 가지고 있음
		
	}
	
	private static int sample(int number, StringBuffer sb) { //파라미터 : 기본형데이터, 객체참조형(값과 주소가 별도로 관리된다)
		number = number + 1 ; //1 증가시킴
		sb.append("APPEND");
		return number;
	}
}
