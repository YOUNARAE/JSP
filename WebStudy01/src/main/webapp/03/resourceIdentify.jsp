<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourceIdentify.jsp</title>
</head>
<body>
<h4>자원의 종류와 식별방법</h4>
<pre>
	: 자원의 위치와 경로 표기 방법에 따라 분류
	
	1.File system resource : d:/contents/images/cat1.jpg
	<%
		String realPath="d:/contents/images/cat1.jpg";
		File fileSystemResource = new File(realPath);
		
	%>
	파일의 크기 : <%=fileSystemResource.length() %>

	2.Class path resource : /kr/or/ddit/images/cat2.png 
	<%
		String qualifiedName = "../images/cat2.png";
		realPath = DescriptionServlet.class.getResource(qualifiedName).getFile();
		realPath = DescriptionServlet.class.getClassLoader().getResource("kr/or/ddit/images/cat2.png").getFile();
		File classPathResource = new File(realPath);
	%>
	실제경로 : <%=realPath %>
	파일의 크기 : <%=classPathResource.length() %>
`
	3.Web resource : https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif
	http://localhost/WebStudy01/resources/jquery-3.6.1.min.js
	<%
		String resourceURL = "http://localhost/WebStudy01/resources/jquery-3.6.1.min.js";
//		String resourceURL = "https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif";
		URL url = new URL(resourceURL);
		URLConnection conn = url.openConnection();
		String resourcePath = url.getPath();
		int lastIdx = resourcePath.lastIndexOf('/');
		String fileName = resourcePath.substring(lastIdx+1);
		String folderPath = "d:/contents/images";
		File downloadFile = new File(folderPath, fileName);
		InputStream is = conn.getInputStream();
		Files.copy(is, Paths.get(downloadFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
	%>
	resourcePath : <%=resourcePath %>
	
	** 웹자원에 대한 식별성 : URI
	URI(Uniform Resource Identifier : 범용 자원 식별자)
	
	URL(Uniform Resource Locateor : 범용 자원 위치자)
	URN(Uniform Resource Name : 같은 이름이 있으면 중복되고, 우선 등록되어있는 출석부의 개념이 필요하다. 이름을 파악하기 위해서 )
	URC(Uniform Resource Content : 이름에 속성까지 더해져서 부르는 URN의 단점을 극복하기 위한 방법 )
	
	URL 구조
	http://localhost/WebStudy01/resources/jquery-3.6.1.min.js
	프로토콜 / 
	protocol(scheme)://IP(DN):port/context/depth1.....depthN/resourceName/
	//-> 루트를 표현하기 위한 구분자
	
	DomainName 
	3 level www.naver.com - 가장 마지막에 있는 레벨 GlobalTopLevelDomain : GTLD
	4 level www.naver.co.kr - NationalTopLevelDomain : NTLD
	
	URL 표기방식
	절대경로(**) : 최상위루트부터 전체 경로 표현 - 생략가능한 요소가 존재.
	    클라이언트가 접근하느냐 내가 접근하느냐에 따라 주소가 달라질 수 있다
		client side : /WebStudy01/resources/images/cat1.jpg
		             : context path 부터 시작됨.
		server side : /resources/images/cat1.jpg
				     : context path 이후의 경로 표기.
	상대경로 : 기준점(브라우저의 현재 주소)을 중심으로 경로 표현, 앞에 슬러시가 없으면 상대경로디다
</pre>
<%
	//InputStream is2 = application.getResourceAsStream(request.getContextPath() + "/resources/images/cat1.jpg");
	String realPath1 = application.getRealPath("/resources/images/cat1.jpg");
	String realPath2 = application.getRealPath(request.getContextPath()+"/resources/images/cat1.jpg");
	
	request.getRequestDispatcher("/WEB-INF/views/dept1/test.jsp").forward(request,response); //서버사이드
	// 포워드에서는 서버 안에서만 해당함
	response.sendRedirect(request.getContextPath() + "/member/memberForm.do"); 
	// 리다이렉트는 헤더까지 가서 새로운 요청을 발생시킨다
%>
<img src="<%=request.getContextPath()%>/resources/images/cat1.jpg" alt=""/>
<img src="../resources/images/cat1.jpg" alt=""/>
<img src="cat1.jpg" alt=""/><br/>
<%-- 서버사이드 방식으로 접근한 파일의 크기 : <%=is2.available()%> --%>
realPath1 : <%=realPath1 %><br/>
realPath2 : <%=realPath2 %>
</body>
</html>