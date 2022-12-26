package kr.or.ddit.designpattern.templatemethodpattern;

public abstract class TemplateClass {
	// 템플릿 클래스에는 절대 바뀌지 않는 패턴 템플릿이 필요하다
	public final void template() {
		//아무리 상속이 되도 오버라이딩이 불가능하다( final때문에). 절대 순서가 바뀔 수 없다.
		//순서를 정의하고 있는 것을 템플릿메소드라고 한다.
		//반복되는 후크메소드로 구성된다.
		stepOne();
		stepTwo();
		stepThree();
	}
	
	// hook method
	//이 안에 정의되는 순서는 절대 바뀔 수 없다
	private void stepOne(){
		System.out.println("1단계");
	}
	
	protected abstract void stepTwo(); //추상메소드가 필요하다.

	
	private void stepThree(){
		System.out.println("3단계");
	}
}
