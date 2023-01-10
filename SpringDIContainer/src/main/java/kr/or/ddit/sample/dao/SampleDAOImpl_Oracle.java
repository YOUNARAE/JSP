package kr.or.ddit.sample.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("daoOracle")
public class SampleDAOImpl_Oracle implements SampleDAO {

	public void init() {
		log.info("{} 객체 초기화", getClass().getSimpleName()); // 가장 마지막에 실행됨
	}

	public void destroy() {
		log.info("{} 객체 소멸", getClass().getSimpleName());
	}

	private Map<String, String> dummyDB;
	
	@Required //셋터인데 꼭 필요한 주입이라해서 Required를 붙여준다. 하지만 직접 주입은 하지 못한다.
	@Resource(name="oracleDB") 
//	얘는 생성자에 쓸 수 없는 방법
	public void setDummyDB(Map<String, String> dummyDB) {
		this.dummyDB = dummyDB;
		log.info("{} 객체 생성, setter 주입으로 dummyDB 객체 주입.", getClass().getSimpleName());
	}
	//set로 호출하면 인젝션이 안될 수도 있다.
	

	@Override
	public String selectRawData(String primaryKey) {
		return dummyDB.get(primaryKey);
	}

}
