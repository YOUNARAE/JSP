package kr.or.ddit.memo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

public class MemoService{
	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();

	public List<MemoVO> retrieveMemoList(){
		return dao.selectMemoList();
	}
}
