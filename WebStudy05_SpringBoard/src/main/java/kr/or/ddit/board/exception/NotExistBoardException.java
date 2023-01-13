package kr.or.ddit.board.exception;

public class NotExistBoardException extends RuntimeException{

	//unchecked 구조
	public NotExistBoardException(int boNo) { // 이 예외를 어떤 글이 존재하지 않을 때를 표현하고 싶다.
		super(String.format("%d 번의 글은 존재하지 않습니다.", boNo));
	}
	
}
