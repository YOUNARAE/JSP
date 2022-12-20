package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 문제 :
 * 폴더에 있는 파일들을 가지고 오는 목록을 출력해주고
 * 그 파일들을 선택하여 COPY를 누르면 복사되어야한다, webapp에 09번 폴더에
 * 선택한 파일은 체크드가 표시되어야하고 토글 기능을 추가하여야 한다.
 */
@WebServlet("/browsing/getFileList")
public class SpecificTargetFileBrwosingServlet_copy extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext(); //일반객체를 선언
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//요청분석해야하는데
		String target = req.getParameter("target");
		String targetPath = application.getRealPath(target);
		
		//타겟이 되는 폴더의 실제 경로 확보하기
		File targetFolder = new File(targetPath);
		
		//모델확보
		File[] fileList = targetFolder.listFiles();
		
		//모델공유
		req.setAttribute("files", fileList);
	}
}

