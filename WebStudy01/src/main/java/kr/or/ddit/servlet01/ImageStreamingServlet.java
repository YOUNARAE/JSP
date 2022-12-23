package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;


public class ImageStreamingServlet extends HttpServlet{
	private String imageFolder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
//		imageFolder = config.getInitParameter("imageFolder");
		application = getServletContext();
		imageFolder = application.getInitParameter("imageFolder");
		System.out.printf("받은 파라미터 : %s\n", imageFolder);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException
	{
		ServletContext application = getServletContext();
		// 응답데이터에 한글 컨텐츠를 포함시키기 위해, 뷁,벩
		String imageName = req.getParameter("image");
		
		// 이미지가 정상적으로 나왔을 때 쿠키가 만들어져야한다. 여러가지 버전의 서블릿 중에서 이미지 전송을 해주는 서블릿은 이 서블릿이라서 여기서 작업한다
		
		if(imageName==null || imageName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String mimeType = application.getMimeType(imageName);
		resp.setContentType(mimeType);
		
		File imageFile = new File(imageFolder, imageName);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		//위에는 저장안될 경우
		Cookie imageCookie = new Cookie("imageCookie", imageName);// 이미지의 네임을 쿠키로 객체 생성
		imageCookie.setPath(req.getContextPath()); //우리 프로젝트 내에서 어디에서나 사용가능할 수 있게 함
		imageCookie.setMaxAge(60*60*24*5);
		resp.addCookie(imageCookie); //이미지가 나갈 떄 이 쿠키도 숨겨서 저장해야한다.
		
		FileInputStream fis=null;
		OutputStream os=null;
		try {
			fis = new FileInputStream(imageFile);
			os = resp.getOutputStream();
			int tmp = -1;
			while((tmp=fis.read())!=-1){
				os.write(tmp);
			}
		}finally {
			if(fis!=null)
				fis.close();
			if(os!=null)
				os.close();
		}
	}	
}






