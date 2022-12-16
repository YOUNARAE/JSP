package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.memo.vo.CalculateVO;

@WebServlet("/04/calculate")
public class CalculateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		
		String viewName = null;
		if (accept.startsWith("*/*") || accept.toLowerCase().contains("html")) {
			viewName = "/WEB-INF/views/03/calculateForm.jsp";
		} else if (accept.toLowerCase().contains("json")) {
			viewName = "/jsonView.do";
		} else {
			viewName = "/xmlView.do";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CalculateVO cv = null;
		try(
			InputStream is = req.getInputStream();
		){
			cv = new ObjectMapper().readValue(is, CalculateVO.class);
		}
		
		req.setAttribute("expression", cv.getExpression()); // jsonView를 위한 것
		req.setAttribute("message", cv.getExpression()); // plainView를 위한 것
		
		String accept = req.getHeader("Accept");
		String viewName = null;
		if (accept.contains("json")) {
			viewName = "/jsonView.do";
		} else {
			viewName = "/WEB-INF/views/04/plainView.jsp";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
