package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/01/imageForm2.do")
public class ImageStreamingFormServlet01_copy extends HttpServlet {
	private ServletContext application;
	private String targetFolder;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
//		System.out.println(application.toString());
		targetFolder = application.getInitParameter("targetFolder");
//		System.out.println("여기는? : " + targetFolder);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.요청 분석
		resp.setContentType("text/html;charset=UTF-8");
		//2.모델확보
//		File folder = new File(imageFolder);
//		folder라는 파일객체를 새로 생성해서 그 객체에 imageFolder 주소값을 넣는다
		
//		File[] imageFiles = folder.listFiles((dir, name)->{
//		생성한 객체 folder에 파일을 리스트로 넣는다 (폴더명, 이름)
//			String mime = application.getMimeType(name);
//		getMimeType으로 어플리케이션 객체의 마임타입을 반환한다
//			return mime !=null && mime.startsWith("image/");
//			이것을 리턴받는다
//			마임이 널 값이 아니고 마임이 images
//			startsWith:지정한 문자로 시작하는지 확인할 것
//		});
		File folder = new File(targetFolder);
		File[] targetFiles = folder.listFiles();
		
		//3.모델공유
		StringBuffer content = new StringBuffer();//스트링 버퍼는 그냥 +,랑 concat을 안 하려고 쓴다
		content.append("<html>	\n");
		content.append("<body>	\n");
		content.append(String.format("<form action='%s/imageStreaming2.do'>\n",req.getContextPath()));		
		content.append("<select name='image'>	\n");
		
		//가운데 동적으로 들어가야하는 소스
		for(File tmp : targetFiles) {
			content.append(String.format("<option>%s</option>", tmp.getName()));
		}
		content.append("</select>	\n");	
		content.append("<input type='submit' value='전송'>	\n");			
		content.append("</form>	\n");		
		content.append("</body>	\n");
		content.append("</html>	\n");
				
		//4.뷰선택
		PrintWriter out = resp.getWriter();
		out.println(content);
		out.close();
		
		//5.뷰이동
	}
	
	
}















