package kr.or.ddit.sample.dao;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_Oracle implements SampleDAO {

	public void init() {
		log.info("{} 객체 초기화", getClass().getSimpleName()); // 가장 마지막에 실행됨
	}

	public void destroy() {
		log.info("{} 객체 소멸", getClass().getSimpleName());
	}

	private Map<String, String> dummyDB;

	public SampleDAOImpl_Oracle(Map<String, String> dummyDB) {//생성자방식
		super();
		log.info("{} 객체 생성, 생성자 주입으로 dummyDB 객체 주입.", getClass().getSimpleName());
		this.dummyDB = dummyDB;
//		dummyDB = new HashMap<>();
//		int idx = 0;
//		dummyDB.put("PK_" + ++idx, "oracle 레코드 " + idx);
//		dummyDB.put("PK_" + ++idx, "oracle 레코드 " + idx);
//		dummyDB.put("PK_" + ++idx, "oracle 레코드 " + idx);
		//주입받을 때 고려할 건 셋터로 주입받을 지 생성자로 받을지
	}

	@Override
	public String selectRawData(String primaryKey) {
		return dummyDB.get(primaryKey);
	}

}
