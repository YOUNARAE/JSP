package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StreamCopyTest {
	private File targetFile;
	private File destFile;
	
	@Before
	public void setUp() {
		//1.주소에 해당하는 파일을 객체화 시킨다.
		targetFile = new File("d:/contents/movies/target.mp4");
		destFile = new File("d:/target.mp4");
	}
	
//	@Test //5.521s 버터 기능 테스트
	public void copyTest1() throws IOException {
		try (
				//2.파일을 읽을 IO객체들을 생성한다
				FileInputStream fis = new FileInputStream(targetFile);
				FileOutputStream fos =  new FileOutputStream(destFile);
		){
			int tmp = -1;
			while((tmp=fis.read())!= -1) {
				fos.write(tmp);
			}
		}
	}	
	
//	@Test //0.006s
	public void copyTest2() throws IOException {
		try (
				//2.파일을 읽을 IO객체들을 생성한다
				FileInputStream fis = new FileInputStream(targetFile);
				FileOutputStream fos =  new FileOutputStream(destFile);
		){
			byte[] buffer = new byte[1024];
			int length = -1;
			int count = 1;
			while((length=fis.read(buffer))!= -1) {
				if(count++==1) {
					Arrays.fill(buffer, (byte)0);
				}
				fos.write(buffer, 0, length);
			}
		}
	}	
	
	@Test //0.029s
	public void copyTest3() throws IOException {
		try (
				//2.파일을 읽을 IO객체들을 생성한다
				FileInputStream fis = new FileInputStream(targetFile);
				FileOutputStream fos =  new FileOutputStream(destFile);
				
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
		) {
			int tmp = -1;
			while((tmp=bis.read())!= -1) {
				bos.write(tmp);
			}
		}
	}	
	
		
}
