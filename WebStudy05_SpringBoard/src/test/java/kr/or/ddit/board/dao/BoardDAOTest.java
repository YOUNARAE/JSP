package kr.or.ddit.board.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebAppConfiguration
@RunWith(SpringRunner.class) //jnit context형성
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml") // 2, 가상컨테이너 하나 생성
public class BoardDAOTest {
	
	@Inject
	private BoardDAO boardDAO;
	
	private PagingVO<BoardVO> pagingVO;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
	}

	@Test
	public void testSelectBoardList() {
		List<BoardVO> dataList = boardDAO.selectBoardList(pagingVO);
		assertNotEquals(0, dataList.size());
	}

//	@Test
	public void testSelectTotalRecord() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSelectBoard() {
		fail("Not yet implemented");
	}

//	@Test
	public void testUpdateBoard() {
		fail("Not yet implemented");
	}

//	@Test
	public void testDeleteBoard() {
		fail("Not yet implemented");
	}

}
