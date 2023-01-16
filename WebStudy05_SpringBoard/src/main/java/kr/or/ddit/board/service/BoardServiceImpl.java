package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.exception.NotExistBoardException;
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
	private AttatchDAO attatchDAO;
	@Inject
	private PasswordEncoder encoder;
	
	@Value("#{appInfo.saveFiles}")
	private File saveFiles;
	
	@PostConstruct
	private void init() {
		log.info("EL로 주입된 첨부파일 저장 경로 : {}", saveFiles);
	}
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;

	private int processAttathList(BoardVO board) {
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList==null || attatchList.isEmpty()) return 0;
		// metadata 저장  - DB(ATTATCH)
		// binary 저장 - Middle Tier : (D:\saveFiles)
		int rowcnt = attatchDAO.insertAttatches(board);
		try {
			for(AttatchVO attatch : attatchList) {
//				if(1==1)
//					throw new RuntimeException("강제 발생 예외");
				attatch.saveTo(saveFiles);
			}
			return rowcnt;
		} catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	@Transactional
	@Override
	public int createBoard(BoardVO board) {
		String plain = board.getBoPass();//이 비밀번호를 암호화해야한다
		String encoded = encoder.encode(plain);
		board.setBoPass(encoded);
		
		int rowcnt = boardDAO.insertBoard(board);
		// 첨부 파일 등록
		rowcnt += processAttathList(board);
		return rowcnt;
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
	public BoardVO retrieveBoard(int boNo){
		BoardVO board = boardDAO.selectBoard(boNo);
		if(board==null)
			throw new NotExistBoardException(boNo);
		boardDAO.incrementHit(boNo); //위 과정을 건너뛰었다면 제대로 읽혔다는 뜻이기떄문에 조회수를 증가시켜준다
		return board;
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
