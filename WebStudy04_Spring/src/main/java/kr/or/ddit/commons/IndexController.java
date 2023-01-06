package kr.or.ddit.commons;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
