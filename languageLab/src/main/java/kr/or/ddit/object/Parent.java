package kr.or.ddit.object;

public class Parent {
	private String code = "default";
	//해쉬코드를 만들 수 있다는 건
	//객체가 가진 상태값으로 비교를 했다는 의미이다.
	
	public void method() {
		System.out.println("parent method execute");
	}
	
	public void template() {
		method(); //hook method
		//this가 생성됨. this는 생성된 인스턴스를 의미함
		//차일드 인스턴스를 생성했기때문에 여기서는 this가 차일드가 된다
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parent other = (Parent) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}
