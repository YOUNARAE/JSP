package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/browsing/fileManager")
public class FileManagerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청분석, 3개의 파라미터를 확보한다. 복사가 되도록한다
		
		int sc = validate(req); //<--여기에 modelMap이 담긴다
		Map<String, Object> modelMap = (Map<String, Object>) req.getAttribute("modelMap");
		if(sc==200) {
			boolean result = fileManage(modelMap); // 밑에 있는 fileManag의 예외를 여기서 떠앉게 된다
			
			req.setAttribute("result", result); //최종적으로 마샬링되는 데이터 안에 트루나 펄스라고 표현되는 것이 포함된다는 의미
			String viewName = "/jsonView.do";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			resp.sendError(sc);
		}
	}

	private boolean fileManage(Map<String, Object> modelMap) throws IOException{ //낱개의 데이터를 하나로 묶어서 전달하는 메서드
		//파일을 복사하는 과정이 필요하다
		File sourceFile = (File) modelMap.get("sourceFile");
		File destinationFolder = (File) modelMap.get("destinationFolder");
//		File destFile = new File(destinationFolder, sourceFile.getName());
		Path destFilePath =  Paths.get(destinationFolder.getCanonicalPath(), sourceFile.getName()); //폴더의 절대경로
		//파일과 패스의 차이 : 파일은 자원이 갖고 있는 여러가지 객체, 패스는 자원이 갖고 있는 경로만을 말한다
		String command = (String) modelMap.get("command");
		
		Files.copy(sourceFile.toPath(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
		//복사 뿐만 아니라 이동,삭제까지 가능하며 도 가능하다 현재 블럭이 파일을 관리하는 코드들이라고 할 수 있다
		return true;
	}
	
	private int validate(HttpServletRequest req) {
		String srcFile = req.getParameter("srcFile");
		String destFolder = req.getParameter("destFolder");
		String command = req.getParameter("command");
		int sc = 200;
		
		Map<String, Object> modelMap = new HashMap<>();
		req.setAttribute("modelMap", modelMap); //미리 넣어놓으면 리퀘스트가 사라질때까지 이 맵이 된다
		
		//요청에 대한 검증
		if(srcFile==null||srcFile.isEmpty()
			||destFolder==null||destFolder.isEmpty()
			||command==null||command.isEmpty()) { //파라미터가 아예 안 넘어왔거나 비어있을 때
			sc = 400;
		}else {
			ServletContext application = req.getServletContext();
			String srcPath = application.getRealPath(srcFile);
			File sourceFile = new File(srcPath);
			if(!sourceFile.exists()) { //존재하지 않다면
				sc = 404;
			}else if(sourceFile.isDirectory()) { //폴더라서 처리 불가능
				sc = 400;
			}else {
				modelMap.put("sourceFile",sourceFile);
			}
			String destPath = application.getRealPath(destFolder);
			File destinationFolder = new File(destPath);
			if(!destinationFolder.exists()) {
				sc = 404;
			}else if(destinationFolder.isFile()) {
				sc = 400;
			}else {
				modelMap.put("destinationFolder",destinationFolder);
			}
			if(!"COPY".equals(command)) {
				sc = 404;
			}else {
				modelMap.put("command", command);
			}
		}
		return sc;
	}
}
