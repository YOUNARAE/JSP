package kr.or.ddit.memo.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemoVO;
import lombok.extern.slf4j.Slf4j;
//뉴 키워드 없애고 싱글톤 디자인패턴 없애기
//뉴 키워드는 컨테이너 객체를 생성할 때 딱 한번만 만든다.
//ApplicationContextAware 이게 가지고 있는 셋 인터페이스를 자신을 넣어준다
@Slf4j
@Repository
public class FileSystemMemoDAOImpl implements MemoDAO {
	@Inject
	private ApplicationContext context;

//	@Override
//	public void setApplicationContext(ApplicationContext context) throws BeansException {
//		this.context = context;
//	}
	
	
	@PostConstruct 
	private void init() {
//		모든 인젝션이 끝나고 얘가 동작한다.
		dataBase = context.getResource("file:d:/memos.dat");
		log.info("리소스 로딩 : {}", dataBase);
//		리소스를 찾아낸 이후에 아래쪽 코드가 실행된다
		try(
			InputStream fis = dataBase.getInputStream(); //1차 스트림이 필요하다.
//			FileInputStream fis = new FileInputStream(dataBase); //위에 코드로 이 소스가 없어져도 된다.
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
		){
			memoTable = (Map<Integer, MemoVO>) ois.readObject();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			this.memoTable = new HashMap<>();
		}
//		context에는 모든 게 주입되어있는 상태다
	}
//	private File dataBase = new File("d:/memos.dat");
//	컨테이너 그 자체가 리소스 로더가 된다.
	private Resource dataBase; //스프링에서는 파일 데이터들은 리소스로 받는다
	private Map<Integer, MemoVO> memoTable; //메모의 코드값을 키로 잡음 integer
	
//	public FileSystemMemoDAOImpl() {
//		이 생성자 필요없어짐
//	}
	
	@Override
	public List<MemoVO> selectMemoList() {
		return new ArrayList<>(memoTable.values());
	}

	@Override
	public int insertMemo(MemoVO memo) {
		int maxCode = memoTable.keySet().stream()
						.mapToInt(key->key.intValue())
						.max() // 있으면 맥스값으로 설정되고
						.orElse(0); //없으면 엘스값으로 설정된다는 의미
		//키를 싹 꺼내와야함,스트림으로 키 하나하나 필터를 걸 수 있다
		
		memo.setCode(maxCode+1);
		memoTable.put(memo.getCode(),memo); //코드는 안에 들어있으니까 그걸 키로 잡는다
		serializeMemoTable();
		return 1;
	}
	
	private void serializeMemoTable() {
		try(
		
			FileOutputStream fos = new FileOutputStream(dataBase.getFile());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(memoTable);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		// 파일시스템으로 파일까지 수정해야한다. 그 작업은 serializeMemo에서 다 해줌
		return 0;
	}

	@Override
	public int deleteMemo(int code) {
		// TODO Auto-generated method stub
		return 0;
	}

}
