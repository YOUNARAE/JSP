package kr.or.ddit.exception;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 에러나 예외(Throwable) : 예상하지 못했던 비정상적인 처리 상황.
 * 		- Error : 개발자가 직접 처리하지 않는 에러계열.
 * 		- Exception : 개발자가 직접 처리할 수 있는 예외 계열.
 * 			- checked exception (Exeption) : 반드시 처리해야만 하는 예외.
 * 				ex) IOException, SQLException, 
 * 			- unchecked exception (RuntiomeException) : 처리하지 않더라도 메소드 호출구조를 통해 JVM에게 제어권이 전달되는 예외.
 * 				ex) NullPointerException, IndexOutOfBoundException 
 * 			
 *  ** 예외 처리 방법
 *  직접처리(능동처리) : try(closeable object)~catch~finally
 *  위임처리(수동처리) : 메소드의 호출자에게 throws로 예외 제어권 위임.
 *  
 *  ** 커스텀 예외 사용 방법
 *  1. Exception이나 RuntimeException의 자식 클래스 정의(예외 클래스)
 *  2. throw 예외 인스턴스 생성
 */
public class ExceptionDesc {
	public static void main(String[] args){ //JVM을 호출하는 호출자에게 최종적으로 제어권이 전달하려면 메소드에서 throws가 되어야 한다.
//		try {
//			String data = getSampleData();
//			System.out.println(data);			
//		}catch (IOException e) {
////			System.err.println(e.getMessage());
//			e.printStackTrace();
//		}
		
		try {
			System.out.println(getSampleDataWithRE());
		}catch(NullPointerException | UserNotFoundException e) {
			System.err.println(e.getMessage());
			System.err.println("정상 처리 위장 가능");
		}
		
//		System.out.println(getSampleChangeException());
	}
	
	private static String getSampleData() throws IOException{
		String data = "SAMPLE";
		if(1==1)
			throw new IOException("강제 발생 예외");
		return data;
	}
	
	private static String getSampleDataWithRE(){
		String data = "SAMPLERE";
		if(1==1)
			throw new UserNotFoundException("강제 발생 예외");
		return data;
	}
	
	private static String getSampleChangeException() {
		String data = "SAMPLEChangeException";
		try {
			if(1==1)
				throw new SQLException("강제 발생 예외");
			return data;
		} catch (SQLException e) {
			//쓰로우스할 수 있는 예외를 일부러 발생을 시켜보자
			throw new RuntimeException(e);
		}
	}
}
