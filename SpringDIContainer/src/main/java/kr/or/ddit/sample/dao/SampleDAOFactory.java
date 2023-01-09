package kr.or.ddit.sample.dao;

public class SampleDAOFactory {
	public static SampleDAO getSampleDAO() {
//		return new SampleDAOImpl_Oracle();
		return new SampleDAOImpl_Postgre();
		//비즈니스 로직을 건드릴 필요없이 여기만 교체하면 된다. 결합력을 낮춘다
	}
	//공장을 외부로 빼기 위해 컨테이너를 쓴다는 개념.
	//전략패턴에서 전략의 주입자를 바깥으로 뺴야한다.
}
