package kr.or.ddit.object;

public class Child extends Parent{
	@Override
	public void method() {
		System.out.println("child method execute");
	}
	
	@Override
	public void template() {
		//this.method(); //같은 영역안에서 패런트를 생성한 적이 없기ㅒ문에 child이다.  
		super.method(); //재정의
	}
}
