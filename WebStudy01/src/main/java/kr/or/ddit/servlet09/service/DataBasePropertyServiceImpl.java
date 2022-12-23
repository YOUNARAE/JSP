package kr.or.ddit.servlet09.service;

import java.util.List;

import kr.or.ddit.servlet09.dao.DataBasePropertyDAO;
import kr.or.ddit.servlet09.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService{
	//이미 사용된 시그니처로 정의할 때 임플리먼트를 이용한다
	private DataBasePropertyDAO dao = new DataBasePropertyDAOImpl(); //의존관계 형성 
	
	
	@Override
	public List<DataBasePropertyVO> retrievePropertyList(String propertyName) {
		List<DataBasePropertyVO>  list =  dao.selectPropertyList(propertyName); // 여기서 리스트를 받아왈 수 있게 됨
		
		list.stream()//스트림은 그 안에 있는 api를 하나하나 건드릴 수 있다.
			.forEach(System.out::println);
				//메소드 레퍼런스 : 원형 vo->System.out.println(vo)
				//함수의 파라미터로 어떤 것을 쓰겠다고 넘겨주겠다는 코드
		return list;
	} 
	
	
}
