package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO boardDAO;
	
	@Inject
	private PasswordEncoder encoder;
	
	@PostConstruct
	private void init() {
		log.info("주입된 객체 : {}", boardDAO);
	}
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public int createBoard(BoardVO board) {
		return 0;
	}

	@Override
	public void retrieveBoardList(PagingVO<BoardVO> pagingVO) { //call by ref로 활용해서 반환타입이 필요없다.
		//1.커런트페이지셋팅(클라이언트가 해줌), 토탈페이지셋팅, 페이지 셋팅 -
		int totalRecord = boardDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardDAO.selectBoardList(pagingVO);
		pagingVO.setDetaList(dataList);
	}

	@Override
	public BoardVO retrieveBoard(int boNo) {
		return null;
	}

	@Override
	public int modifyBoard(BoardVO board) {
		return 0;
	}

	@Override
	public int removeBoard(int boNo) {
		return 0;
	}

	@Override
	public AttatchVO retrieveForDownload(int attNO) {
		return null;
	}

}
