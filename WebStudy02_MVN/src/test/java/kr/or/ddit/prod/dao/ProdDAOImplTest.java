package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로거를 사용할 수 있게 해주는 어노테이션 (lombok제공)
public class ProdDAOImplTest {

	private ProdDAO dao = new ProdDAOImpl();
	
	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		log.info("buyer : ", prod.getBuyer());
		prod.getMemberSet().stream()
			.forEach(user->{
				log.info("구매자 : {}", user);
			});//구매자정보
	}

}