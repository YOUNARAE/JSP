package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/bts/*", loadOnStartup=2) //loadOn..으로 생성 순서를 작성자가 제어할 수 있음
public class BTSMemberServlet extends HttpServlet{	
	private ServletContext application; // 전체를 통틀어 어플리케이션은 하나여야하고 이로 인해서 btsMembers를 전역으로 사용할 수 있게 됨
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1 요청정보, 코드값
		String requestURI = req.getRequestURI();
		String code = Optional.of(requestURI)
						        .map(uri -> uri.substring(req.getContextPath().length()))
						        .map(uri -> uri.substring("/bts/".length()))
						       	.get();	
		Map<String, String[]> members = (Map) application.getAttribute("btsMembers");
		String[] contents = members.get(code);
		///bts/drfghd <-클라이언트에서 이런 주소가 들어올 경우의 예외처리
		if(contents==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); //404
			return;
		}
		String contentPage = contents[1];
		req.setAttribute("contentPage", contentPage);
		req.getRequestDispatcher("/WEB-INF/views/bts/btsLayout.jsp").forward(req,resp);
	}
}
