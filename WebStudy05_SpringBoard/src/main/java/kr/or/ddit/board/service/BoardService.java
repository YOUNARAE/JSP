package kr.or.ddit.board.service;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface BoardService {
	/**
	 * @param board
	 * @return 
	 */
	public int createBoard(BoardVO board); //이넘이 없으니까 int로 성공한 갯수를 돌려줘도 됨.
	public void retrieveBoardList(PagingVO<BoardVO> pagingVO);
	/**
	 * @param boNo
	 * @return 존재 여부(NotExistZBoardException)
	 */
	public BoardVO retrieveBoard(int boNo); // 하나만 받아야하니까 보드 넘버를 받는다
	//인증에 실패했다고 발생하는 상황이 생길 수 있는 것을 고려한다.
	//
	/**
	 * @param board
	 * @return 존재 여부(NotExistZBoardException), 인증성공여부(AuthenticationException), rowcnt
	 */
	public int modifyBoard(BoardVO board); //근데 수정은 자기만 해야하는데?!
	public int removeBoard(int boNo);
	
	public AttatchVO retrieveForDownload(int attNO);
}
