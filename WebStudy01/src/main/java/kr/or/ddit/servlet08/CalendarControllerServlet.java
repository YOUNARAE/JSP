package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/10/calendar.do")
public class CalendarControllerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청을 분석하는 단계가 필요하다. 2개의 파라미터를 검증 후 이용해야한다.
		String yearParam = req.getParameter("year");
		String monthParam = req.getParameter("month");
		String language = req.getParameter("language");
		
		Locale clientLocale = req.getLocale();
		if( language != null && !language.isEmpty()) {
			clientLocale = Locale.forLanguageTag(language); //클라이언트 입장에서 언어를 받아서 처리해주는
			req.setAttribute("language", language);
		}
		
		// 모델확보 (쓸 날짜를 확보)
		Calendar calendar = Calendar.getInstance(); //디스패치로 이동하는 과정에서 최소한의 영역을 쓰는 것이 시스템에 부하가 덜 간다
		// 돼지코가 필요해짐 adaptee
		
		try {
			if(yearParam != null && monthParam != null) {
				int year = Integer.parseInt(yearParam);
				int month = Integer.parseInt(monthParam);
				calendar.set(YEAR, year);
				calendar.set(MONTH, month);
			}
			
			req.setAttribute("calendar", new CalendarWrapper(calendar, clientLocale));
			
			String viewName = "/WEB-INF/views/calendar.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
		
		}catch (NumberFormatException e) {
			resp.sendError(400, e.getMessage());
			return;
		}
		
//		if(yearParam != null && !yearParam.matches("\\d{4}")) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
		
	}
}

