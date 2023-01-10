package kr.or.ddit.memo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

@Service
public class MemoService{
//	private MemoDAO dao = FileSystemMemoDAOImpl.getInstCance();
//	여기에 인젝트를 붙이면 public이 되고
	private MemoDAO dao; // 이 객체를 주입받을 때 어떤 방법으로 주입할지를 결정해야한다.
	
	//필수가 아니면 옵셔널 ->셋터로 써도 됨.
	//필수다 -> 생성자로 주입을 받아야한다.
	
	//반드시 이걸 생성해야하면 여기에 붙인다 inject를, 생성자에도 사용이 가능하다
	@Inject
	public MemoService(MemoDAO dao) {
		super();
		this.dao = dao;
	}
	

	public List<MemoVO> retrieveMemoList(){
		return dao.selectMemoList();
	}

	
}
