package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;

@Mapper
//맵퍼를 써야 BoardDAO에 프록시로 구현이 된다.
public interface AttatchDAO {
//	첨부파일은  페이징 처리 할 필요가 없다
	
	public int insertAttatches(BoardVO board); //동시에 수정할 수 있어야해서 복수형으로 받는다,  
//	등록은 글에다가 하는 거니까 글에는 여러개의 첨부를 할 수 있으니까 헤즈 매니 관계이다.
//	파일이 1개면 1이고 5개면 5개다
	public List<AttatchVO> selectAttatchList(int boNo); 
//	굳이 필요하다면, 하지만 첨부파일만 따로 조회하는 게 아니라 해당하는 글에 대해 조회한다.
	public AttatchVO selectAttatch(int attNo);
	
//	첨부가 된 그 파일의 게시글을 수정하는 개념이다.
//	updateAttatch
	
	public int deleteAttatchs(BoardVO board);
//	지우려는 파일이 게시글에 포함되어있어야한다.
//	VO에 써놓은  int[] delAttNos배열과 이 갯수가 일치하여야한다.
	
	
}
