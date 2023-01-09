package kr.or.ddit.sample.service;

import java.util.Calendar;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import kr.or.ddit.sample.dao.SampleDAOImpl_Postgre;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SampleServiceImpl implements SampleService {
	



//	case1 : 의존객체를 new 키워드로 직접 생성.(결합력 최상)
//	private SampleDAO dao = new SampleDAOImpl_Oracle();
//	private SampleDAO dao = new SampleDAOImpl_Postgre();
//	case2 : Factory Object[Method] Pattern
//	private SampleDAO dao = SampleDAOFactory.getSampleDAO();
//	case3 : Strategy Pattern , 전략 주입자가 필요함.
	private SampleDAO dao; //의존해야되는 객체를 내가 생성하지 않는다
	//어떤 것도 사용하지 않겠다는 상태로 선언
	public SampleServiceImpl(SampleDAO dao) {
		super();
		this.dao = dao;
		log.info("{} 객체 생성 및 전략 객체({}) 주입", getClass().getSimpleName(), dao.getClass().getSimpleName());
	} //생성자 생성
//	case4 : DI Container

	public SampleServiceImpl() {
		super();
		log.info("{} 객체생성", getClass().getSimpleName());
	}
	
	public void setDao(SampleDAO dao) {
		this.dao = dao;
	}
	
	
	@Override
	public StringBuffer retrieveInformation(String pk) {
		String data = dao.selectRawData(pk);
		StringBuffer information = new StringBuffer();
		information.append(
			String.format("%tc에 조회된 데이터를 가공함 - %s", Calendar.getInstance(), data)
		);
		return information;
	}


}
