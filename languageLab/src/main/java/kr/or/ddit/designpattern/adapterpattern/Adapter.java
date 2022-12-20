package kr.or.ddit.designpattern.adapterpattern;

public class Adapter implements Target {

	private Adaptee adaptee;
	
	//어댑티 없이는 구현이 불가능한 생성자
	//wrapper instance
	public Adapter(Adaptee adaptee) { //이 생성자가 들어온 순간 기본 생성자는 없어진다. wㄱapper
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.specificRequest();
	}

}
