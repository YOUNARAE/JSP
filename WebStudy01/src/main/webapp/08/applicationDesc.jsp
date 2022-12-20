<%@page import="java.io.InputStream"%>
<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/applicationDesc.jsp</title>
</head>
<body>
<h4>application(ServletContext)</h4>
<pre>
	: 하나의 컨텍스트와 해당 컨텍스트를 운영중인 서버의 정보를 가진 객체(singleton)
	  (Servlet Container[WAS, JSP container] 와 커뮤니케이션하기 위한 객체)
	  
	  1. MIME 확보(서버가 가진 정보 획득)
	  	 <%=application.getMimeType("test.jpg") %>
	  	 <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	  	 <%=application.getServerInfo() %>
	  	 <%=application.getContextPath() %>
	  	 <%=request.getServletContext().getContextPath() %>
	  	
	  2. 컨텍스트 파라미터 획득
	  	 <%=application.getInitParameter("imageFolder") %>
	  3. logging <% application.log("정상로그 메세지"); %>
	  4(******). 현재 컨텍스트의 웹리소스 획득.
	  	 <%
			String imageURI = "/resources/images/cat1.jpg";
			String realPath = application.getRealPath(imageURI);
			
			String saveFolderURI = "/09";
			String saveFolderPath = application.getRealPath(saveFolderURI);
			
			//매체입력
			File imageFile = new File(realPath);
			File destFile = new File(saveFolderPath, imageFile.getName());
			
			 try(
				InputStream is = application.getResourceAsStream(imageURI);
				//2단계
// 				FileInputStream fis = new FileInputStream(imageFile); //미디어에 직접 넘거가기 때문에 주소를 넘겨준다	
// 				BufferedInputStream bis = new BufferedInputStream(fis);
// 				FileOutputStream fos = new FileOutputStream(destFile);
// 				BufferedOutputStream bos = new BufferedOutputStream(fos);
			){
// 				 3단계,데이터를 기록하는단계
// 				 Files.copy(in, target, options)
// 				 Files.copy(bis, destFile.toPath(), StandardCopyOption.REPLACE_EXISTING); //REPLACE_EXISTING:기존에 파일이 존재하면 새로 덮어쓰기 해줘
				 Files.copy(is, destFile.toPath(), StandardCopyOption.REPLACE_EXISTING); //REPLACE_EXISTING:기존에 파일이 존재하면 새로 덮어쓰기 해줘
			 }catch(IOException e){
				 throw new RuntimeException(e);
			 }
	  	 %>
	  	 <%=imageFile.length() %>
	  	 <%=realPath %>
	  	 
</pre>
<img src="<%=request.getContextPath() %><%=saveFolderURI %>/<%=destFile.getName() %>" />
</body>
</html>














