package kr.or.ddit.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

//public class IndexController implements AbstractController{
@Controller
public class IndexController {
	
	@RequestMapping("/index.do")
//	@Override
//	public String welcome(HttpServletRequest req, HttpServletResponse resp) {
	public String welcome(HttpServletRequest req){ //아규먼트에 resp를 처리할 수 있는 작업을 해주어서 빼도 된다
		req.setAttribute("contentPage", "/WEB-INF/views/index.jsp");
//		String viewName = "/WEB-INF/views/layout.jsp";
		return "layout";
//		req.getRequestDispatcher(viewName).forward(req, resp);
	}

}
