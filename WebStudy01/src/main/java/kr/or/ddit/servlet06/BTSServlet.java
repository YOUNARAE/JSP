package kr.or.ddit.servlet06;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet({"/bts","/bts/*"}) : 단일책임이 불가능
@WebServlet(value="/bts", loadOnStartup=1)
public class BTSServlet extends HttpServlet{	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext application = config.getServletContext();
		application.setAttribute("btsMembers", getBtsMemberList());
	}
	
	public Map<String, String[]> getBtsMemberList() { //전제 멤버의 정보에 관해 공유할 수 있음
		Map<String, String[]> members = new LinkedHashMap<>();
		int idx=1;
		
		members.put("B00"+idx++,new String [] {"RM","/WEB-INF/views/bts/rm.jsp"});
		members.put("B00"+idx++,new String [] {"진","/WEB-INF/views/bts/jin.jsp"});
		members.put("B00"+idx++,new String [] {"슈가","/WEB-INF/views/bts/suga.jsp"});
		members.put("B00"+idx++,new String [] {"제이홉","/WEB-INF/views/bts/jhop.jsp"});
		members.put("B00"+idx++,new String [] {"지민","/WEB-INF/views/bts/jimin.jsp"});
		members.put("B00"+idx++,new String [] {"뷔","/WEB-INF/views/bts/bui.jsp"});
		members.put("B00"+idx++,new String [] {"정국","/WEB-INF/views/bts/jungkuk.jsp"});
		return members;
	}
	
	public String[] getMemberContent(String code) { //멤버 1명의 정보에 관해 공유할 수 있음
		String[] content = getBtsMemberList().get(code); //코드값을 넘기는 것으로 사용한다
		return content;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//bts 전체 멤버에 해당하는 데이터를 만들어줘야한다.
		
		//요청정보 확인
		//String accept = req.get;
		//모델확보, 방탄들의 리스트를 모두 가지고 있는 리스트를 확보한다.
		 Map<String, String[]> bts = getBtsMemberList();
		
		//모델공유, 가져온 리스트에서 파라미터값을 넣어준다
		//String members = req.getParameter("members")
		req.setAttribute("bts", bts);
		System.out.println("==데이터가 뭘로 나오냐? : "+ bts.toString());
		//뷰선택
		String viewName = "/jsonView.do";
		//뷰이동
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
}
