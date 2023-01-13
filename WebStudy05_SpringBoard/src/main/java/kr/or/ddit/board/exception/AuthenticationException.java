package kr.or.ddit.board.exception;

public class AuthenticationException extends RuntimeException{
	//인증을 하다가 문제 가 생겼다는 것을 아려주고 싶은 예외
	//UnchecedException
	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

}
