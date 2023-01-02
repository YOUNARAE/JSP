package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;


public class OtherDAOImplTest {

	private OthersDAO dao = new OtherDAOImpl();
	private PagingVO<ProdVO> pagingVO;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
	}
	
	@Test
	public void testSelectLprodList() {
		List<Map<String, Object>> lprodList = dao.selectLprodList();
		lprodList.stream()
				.forEach(System.out::println);
	}
}
