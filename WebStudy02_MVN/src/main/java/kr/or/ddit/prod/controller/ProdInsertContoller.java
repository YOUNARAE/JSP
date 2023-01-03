package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.AbstractController;

public class ProdInsertContoller implements AbstractController {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String이 로지컬 뷰 네임.
		
		return "prod/prodForm"; //suffix,prefix 앞 뒤로 생략되있음
	}

}
