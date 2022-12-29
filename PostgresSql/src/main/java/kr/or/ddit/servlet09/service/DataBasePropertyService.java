package kr.or.ddit.servlet09.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface DataBasePropertyService {
	//이 객체를 사용하겠다는 정의만 
	public List<DataBasePropertyVO> retrievePropertyList(String propertyName);
}
