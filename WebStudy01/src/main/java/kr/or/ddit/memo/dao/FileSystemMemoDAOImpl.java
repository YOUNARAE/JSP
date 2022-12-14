package kr.or.ddit.memo.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemoVO;

public class FileSystemMemoDAOImpl implements MemoDAO {
	private static FileSystemMemoDAOImpl instance;
	public static FileSystemMemoDAOImpl getInstance() {
		if(instance==null) {
			instance = new FileSystemMemoDAOImpl();
		}
		return instance;
	}

	private File dataBase = new File("d:/memos.dat");
	private Map<Integer, MemoVO> memoTable; //메모의 코드값을 키로 잡음 integer
	
	public FileSystemMemoDAOImpl() {
		//역질렬화작업을 해야함, 최초로 데이터에 접근할 때 복원하는 작업
		try(
			FileInputStream fis = new FileInputStream(dataBase);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
		){
			memoTable = (Map<Integer, MemoVO>) ois.readObject();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			this.memoTable = new HashMap<>();
		}
	}
	
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
			FileOutputStream fos = new FileOutputStream(dataBase);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(memoTable);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
