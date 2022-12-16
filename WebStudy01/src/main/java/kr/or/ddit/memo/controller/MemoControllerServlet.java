package kr.or.ddit.memo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.vo.MemoVO;

@WebServlet("/memo")
public class MemoControllerServlet extends HttpServlet{
	// 의존관계(점선)을 형성해준다
	private MemoDAO dao = FileSystemMemoDAOImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청의 조건 : 헤더(Accept)
		//요청 조건에서 헤더값을 가져온다
		String accept = req.getHeader("Accept");
		if(accept.contains("xml")) {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}
		//2.모델확보
		//메모들을 보여줄 메모 객체를 생성해준다
		List<MemoVO> memoList = dao.selectMemoList();
		//boardService.selectAllBoard();
		//3.모델공유
		//생성한 메모 객체에 메모 내용들을 내용을 넣어준다
		req.setAttribute("memoList", memoList);
		//4.뷰 선택
		String viewName ="/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req,resp);			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//메모한건을 확보해야함
		//Post-Redirect-Get : PRG pattern		
		MemoVO memo = getMemoFromRequest(req);
		dao.insertMemo(memo);
		//메모를 작성하고서 새로 작성한 메모가 추가한 페이지를 확인하기 위해서 다시 /memo로 향한다.
		resp.sendRedirect(req.getContextPath() + "/memo");
		//a메모작성한 건 끝났다->여기 req에서는 모든정보는 남겨놓지 않고 끝났다 
	}
	
	
	private MemoVO getMemoFromRequest(HttpServletRequest req) throws IOException {
		//값을 가져와야한다. 현재 넣어야할 데이터들

//		MemoVO memo = new MemoVO();
//		memo.setWriter(req.getParameter("writer"));
//		memo.setContent(req.getParameter("content"));
//		memo.setDate(req.getParameter("date"));
		String contentType = req.getContentType();
		MemoVO memo = null;
		if(contentType.contains("json")) {
			try(
				BufferedReader br = req.getReader(); // body content read 용 입력 스트림
			){
				memo = new ObjectMapper().readValue(br, MemoVO.class);
			}
		} else if(contentType.contains("xml")) {
			try(
					BufferedReader br = req.getReader(); // body content read 용 입력 스트림
			){
				memo = new XmlMapper().readValue(br, MemoVO.class);
			}
		} else {
			memo = new MemoVO();
			memo.setWriter(req.getParameter("writer"));
			memo.setContent(req.getParameter("content"));
			memo.setDate(req.getParameter("date"));
		}
		return memo;
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
