package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/10/calendar")
public class CalendarController_copy extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청분석 , 요청받을 것들, 내가 고를 것들 = 월, 일, 언어
		String yearParam = req.getParameter("year");
		String monthParam = req.getParameter("month");
		String language = req.getParameter("language");
		
		//날짜를 표시해줘야하는 데에 필요한 모든 작업들을 할 것이다.
		//앞에 jsp에서 필요했던 거, 날짜,년,일, 시작하는 날짜,끝나는 날짜,
		//캘린터 쓸 꺼니까 캘린더 모델을 확보해야하지
		Calendar calendar = Calendar.getInstance();
		req.setAttribute("calendar", calendar);
		String viewName = "/WEB-INF/views/calendar_copy.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
