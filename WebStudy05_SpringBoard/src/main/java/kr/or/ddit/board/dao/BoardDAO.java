package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Mapper
public interface BoardDAO {
	public int insertBoard(BoardVO board); // rowcont를 받아야해서 int
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO); //전체 리스트들을 조절할 수 있는 페이징VO에 있는 것을 받아야해서
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	// SearchVO를 받지 않는 이유는 상세검색을 해야하니까 그렇게 검색하는 페이지까지 받아야해서
	//단순 검색과 상세조건까지 다 받을 수 있어서 PagingVO 로 한다
	public BoardVO selectBoard(int boNo); 
	//널 값이 반환됐을 경우의 수가 고려되야한다. 검색했는데 없을 경우
	public void incrementHit(int boNo); //조회수 한개를 증가시키는 것
	public int updateBoard(BoardVO board); //boardVO를 받아야 수정이 된다
	public int deleteBoard(int boNo);
}
