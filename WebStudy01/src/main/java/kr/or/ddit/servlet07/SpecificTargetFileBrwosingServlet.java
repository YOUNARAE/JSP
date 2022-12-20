package kr.or.ddit.servlet07;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.vo.MemoVO;

@WebServlet("/browsing/getFileList")
public class SpecificTargetFileBrwosingServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = config.getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청분석 파일이 선택되도록 한다
		String target = req.getParameter("target"); 
		//받아야하는 요청을 분석하고(검증도 포함)
		if(target==null || target.isEmpty()) { //파라미터가 안 넘어갔다는 의미
			//검증에 걸려서 나오는 오류는 400
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		//target : /resources/images

		String targetPath = application.getRealPath(target);
		File targetFolder = new File(targetPath);
		if(!targetFolder.exists()) { //폴더가 존재하지 않는다면
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		//위에 if문을 넘었다면 파일이 존재하였다는 의미. 
		//아래에서 걸렸다면 실패했다
		if(targetFolder.isFile()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		//위의 if문들을 통과하였다면 정상적으로 요청이 오고 있다는 의미다
		
		//2. 모델확보
		File[] fileList = targetFolder.listFiles();
		List<FileWrapper> wrapperList = Arrays.stream(fileList)
										.map(file->new FileWrapper(file, application))
										.collect(Collectors.toList());
		//=====위에까지 돼지코를 만든 작업이라고 생각하면 된다
		
		//3.
//		req.setAttribute("files", fileList); //돼지코를 마샬링해야한다
		req.setAttribute("files", wrapperList); 
		//4. 뷰선택
		String viewName ="/jsonView.do";
		//뷰이동
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
}
