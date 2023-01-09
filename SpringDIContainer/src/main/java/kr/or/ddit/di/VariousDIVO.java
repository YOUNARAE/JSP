package kr.or.ddit.di;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor //기본생성자를 주입하지 않은 상태에서 만들어야함
@AllArgsConstructor //생성자 주입구조
public class VariousDIVO {
	
	private int number;
	private boolean flag;
	private double dblNumber;
	private char ch;
	private String str;
	
	private File fileSystemFile;
	private File classPathFile;
	
	public void init(){
		log.info("{} 객체 초기화 완료", getClass().getSimpleName());
	}
	
	public void destroy(){
		log.info("{} 객체 소멸", getClass().getSimpleName());
	}
}
