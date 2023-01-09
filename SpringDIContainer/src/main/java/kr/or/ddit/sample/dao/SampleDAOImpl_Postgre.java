package kr.or.ddit.sample.dao;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_Postgre implements SampleDAO {
//DB서버가 바껴서 새로운 다오를 구현했다는 상황	
	private Map<String, String> dummyDB;

	public SampleDAOImpl_Postgre() { //기본생성자 생성
		super();
		log.info("{} 객체 생성", getClass().getSimpleName());
		dummyDB = new HashMap<>();
		int idx = 0; 
		dummyDB.put("PK_"+ ++idx, "PostgreSQL 레코드 "+idx);
		dummyDB.put("PK_"+ ++idx, "PostgreSQL 레코드 "+idx);
		dummyDB.put("PK_"+ ++idx, "PostgreSQL 레코드 "+idx);
	}

	@Override
	public String selectRawData(String primaryKey) {
		return dummyDB.get(primaryKey); 
	}

}
