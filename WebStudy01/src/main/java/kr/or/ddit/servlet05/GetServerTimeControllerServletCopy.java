package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/getServerTimeCopy")
public class GetServerTimeControllerServletCopy extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		2
		Date now = new Date();
		String nowStr = String.format(Locale.CANADA, "now : %tc",now);
		req.setAttribute("now", nowStr);//toString()
		resp.setHeader("Refresh", "1");
		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req,resp);		
	}
}
