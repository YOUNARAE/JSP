package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AbstractController {
	public String process(HttpServletRequest req, HttpServletResponse resp) // 모든 컨트롤러들은 뷰 네임으로 돌려받아야해서 타입이 String
		throws ServletException, IOException; 
}
