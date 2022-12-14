package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/04/getGreetingMessage")
public class GetMessageControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1.요청분석(line, header, body)
		String accept = req.getHeader("Accept");
		String locale = req.getParameter("locale");
		Locale clientLocale = null;
		if(locale!=null) {
//			ko, em : language tag, locale code
			clientLocale = Locale.forLanguageTag(locale);
		} else {
			clientLocale = req.getLocale(); // Accept-language header로 결정됨.
		}
		String name = req.getParameter("name"); //asdfasdf
		if(name==null||name.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		Object message = "null";
//		2. 모델확보
		try {
			message = retrieveMessage(clientLocale, name);
		}catch (MissingResourceException e) {
			//니가 찾으려고 하는 url 코드는 우리 서버 안에는 없다는 것을 표시해줘야한다.
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
//		3. 모델 공유(setAttribute)
//		Map<String, Object> target = Collections.singletonMap("message", message);
		req.setAttribute("message", message); //모델 공유
		
//		4. 뷰 선택
		String viewName = null;
		int statusCode = HttpServletResponse.SC_OK;
		if(accept.contains("json")) {
			viewName = "/jsonView.do";
		} else if(accept.contains("xml")) {
			viewName = "/xmlView.do";
		} else if(accept.contains("plain")){
			viewName = "/WEB-INF/views/04/plainView.jsp"; //모델 선택
		} else {
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}
		
		if(statusCode==HttpServletResponse.SC_OK) {
//		5. 뷰로 이동(redirect)
			req.getRequestDispatcher(viewName).forward(req, resp); 
		} else {
			resp.sendError(statusCode);
		}
	}
	
	private Object retrieveMessage(Locale clientLocale, String name) {
//      베이스 네임에는 확장자와 로케일도 포함이 안된다
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, clientLocale);
		return bundle.getString(name);
	}
}
