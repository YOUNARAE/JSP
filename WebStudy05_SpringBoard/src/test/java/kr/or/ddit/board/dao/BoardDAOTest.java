package kr.or.ddit.board.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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

	private BoardVO board;
	
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
		
		board = new BoardVO();
		board.setBoWriter("작성자33");
		board.setBoContent("내용33");
		board.setBoIp("192.186.222");
		board.setBoMail("내용33");
		board.setBoDate("2023-01-16");
	}

//	@Test
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
		BoardVO board = boardDAO.selectBoard(224);
		assertNotNull(board);
		board.getAttatchList()
			.stream().forEach(System.out::println);
	}
	
	@Test
	public void testInsertBoard() {
		boardDAO.insertBoard(board);
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
