package kr.or.ddit.servlet04;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/04/calculate")
public class CalculateServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
resp.setContentType("text/html; charset=UTF-8");
		
		String accept = req.getHeader("Accept"); // */*
		Map<String, Object> targetMap = new HashMap<String, Object>();
		if( null != req.getParameter("leftOp") && !"".equals(req.getParameter("leftOp")) ) {
			String target = "";
			int leftOp = Integer.parseInt(req.getParameter("leftOp"));
			String operator = req.getParameter("operator");
			int rightOp = Integer.parseInt(req.getParameter("rightOp"));
			
			double targetValue = 0.0;
			if ("PLUS".equals(operator)) {
				targetValue = (double) leftOp + (double) rightOp;
				target = "" + leftOp + " + " + rightOp + "= " + targetValue;
			} else if ("MINUS".equals(operator)) {
				targetValue = (double) leftOp - (double) rightOp;
				target = "" + leftOp + " - " + rightOp + "= " + targetValue;
			} else if ("MULTIPLY".equals(operator)) {
				targetValue = (double) leftOp * (double) rightOp;
				target = "" + leftOp + " * " + rightOp + "= " + targetValue;
			} else if ("DIVIDE".equals(operator)) {
				targetValue = (double) leftOp / (double) rightOp;
				target = "" + leftOp + " / " + rightOp + "= " + targetValue;
			}
			targetMap.put("key", target);
		}else {
			targetMap.put("key", "");
		}
		
		req.setAttribute("target", targetMap); //모델 공유
		String path = "";
		
		if(accept.startsWith("*/*")||accept.toLowerCase().contains("html")) {
			path = "/WEB-INF/views/03/calculateForm.jsp"; //모델 선택
		} else if(accept.toLowerCase().contains("json")) {
			path = "/jsonView.do";
		} else if(accept.toLowerCase().contains("xml")){
			path = "/xmlView.do";
		} 
		req.getRequestDispatcher(path).forward(req, resp); //4.모델이동
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);		
	}
}
