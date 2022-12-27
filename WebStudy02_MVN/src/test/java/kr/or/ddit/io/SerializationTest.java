package kr.or.ddit.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemoVO;

/**
 * 
 * Serialization(직렬화)
 *  :  전송이나 저장을 위해 객체의 상태를 바이트 배열로 변환하는 과정.
 *  
 */
public class SerializationTest {
//	private Map<String, Object> writeData;
	private MemoVO writeData; //VO에 담긴 데이터를 읽어들여서 
	private File writeFile; //writerFile이라는 파일에 담으려고 한다
	
	@Before
	public void setUp() {
//		writeData = new HashMap<>();
//		writeData.put("code1", new Integer(23));
//		writeData.put("code2", "TEXT");
//		writeData.put("code3", new Boolean(true));
		writeData = new MemoVO();
		writeData.setCode(1);
		writeData.setWriter("작성자");
		writeData.setContent("내용");
		//2022-12-14 17:20:00
		//String.format("%tc",LocalDateTime.now());
		//%[argument_index$][flags][width][.precision]conversion
		String date = String.format("%1$ty-%1$tm-%1$td %1$tH:%1$tM:%1$tS",LocalDateTime.now());
		writeData.setDate(date);
		
		writeFile = new File("d:/sample.dat");
	}

	@Test
	public void serializeTest() throws IOException {
		try(
			FileOutputStream fos = new FileOutputStream(writeFile);	
			ObjectOutputStream oos = new ObjectOutputStream(fos);	
		){
			oos.writeObject(writeData); //직렬화를 하거나 역직렬화가 가능한 게 모든 객체가 가능한 것은 아니다
			// 직렬화, 역직렬화의 대상이 되는 것은 Serializable를 임플리먼트한 경우에만 가능하다
		}		
	}
	
    @Test
	public void deSerializeTest() throws IOException, ClassNotFoundException {
		try(
			FileInputStream fis = new FileInputStream(writeFile); //기록을 해놨던 파일 다시 읽기
			ObjectInputStream ois = new ObjectInputStream(fis);
		){
//			Map<String, Object> map = (Map<String, Object>) ois.readObject();
			MemoVO memo = (MemoVO) ois.readObject();
			System.out.println(memo);
		}
	}
}
