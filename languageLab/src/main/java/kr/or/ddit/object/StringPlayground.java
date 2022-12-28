package kr.or.ddit.object;

public class StringPlayground {
	public static void main(String[] args) {
		String str1 = "SAMPLE";
		str1 = str1 + "append"; //샘플과 어픈드가 더해지는 곳 + 샘플 + 어팬드를 더한 것
		String str2 = "SAMPLE";
		String str3 = new String("SAMPLE");
		String str4 = new String(str1);
		
		StringBuffer original = new StringBuffer("SAMPLE"); //힙메모리에 저장되있고
		original.append("append");//힙에 있는 주소 공간에 append란 단어를 저장
		
		System.out.printf("str1 == str2 : %b \n", str1 == str2); //true
		System.out.printf("str2 == str3 : %b \n", str2 == str3); //false
		System.out.printf("str3 == str4 : %b \n", str3 == str4); //false
		System.out.printf("str4 == str1 : %b \n", str4 == str1); //false
		System.out.printf("str4 == str1 : %b \n", str4.intern() == str1); //true, intern:객체 안에 숨어있는 실제 값, 상수 메모리에 올라가있는 str1
		System.out.printf("str4 == str1 : %b \n", str4.intern() == str2); //true
		System.out.printf("str4 == str1 : %b \n", str4.intern() == str3.intern()); //true
		
		//이퀄연산자보다는 이퀄스를 꺼내쓰거나 인턴스가 더 확실하고 안전한 방법이다.
		
		//String은 기본형으로도 볼 수 있고 참조형으로도 볼 수 있다.
		//같은 상수를 만드는 str1,str2
		//뉴 스트링하면 힙에 만들어지기 때문에 값이 아닌 새로운 주소를 갖게 된다.
		// 내부에 갖고 있는 문자열로 str4를 만든다고 해도 값으로 새로운 주소를 만들기때문에 같아질 수 없다.
	}
}
