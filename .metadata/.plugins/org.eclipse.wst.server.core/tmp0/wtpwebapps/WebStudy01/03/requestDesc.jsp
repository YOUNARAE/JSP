<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>>03/requestDesc.jsp</title>
</head>
<body>
<h4>request (HttpServletRequest)</h4>
<form method="post"></form>
<pre>
	Http의 요청 패키징 방식
	: 자원에 대한 식별+ 자원에 대한 행위 정보를 기본으로 함.
	
	1. Request Line : URI, http(request) Method
	     request Method : 행위, 의도(목적)
	     GETs(R)
	     POST(C)
	     PUT/PATCH(U) : put(무조건 다 수정하는 방식)
	     DELETE(D)
	     HEAD : 응답데이터의 패키징 구조(LINE + HEADER)
	     OPTIONS : 현재 서버가 특정 메소드를 지원하는지 여부를 확인하기 위한 사전 요청(preFlight request)에 사용.
	     TRACE : 서버 디버깅 용도로 제한적으로 사용.
	     
	     ex) /member/memberInsert.do
	     
	     RESTful URI(자원식별과 행위를 분리하자)-JSON/XML로 자원 표현.                   
	     /member GET 
	     /member a001 GET 
	     /member a001 PUT 
	     /member a001 DELETE
	     /member POST
	     <%
	     	String requestURI = request.getRequestURI();
	     	StringBuffer requestURL = request.getRequestURL();
	     	String method = request.getMethod();
	     	
	     %>
	     requestURI: <%=requestURI %>
	     requestURL: <%=requestURL %>
	     request method : <%=method %>
	     
	2. Request Header : 클라이언트에 대한 부가정보(meta data)
	                    : 이름-값 의 쌍으로 구성된 "문자" 데이터
	
		<%
		String userAgent = request.getHeader("user-Agent");
		out.println(userAgent);

		%>
	
	3. Request Body(optional) : POST, PUT
	                            클라이언트가 서버로 보내는 컨텐츠 영역(Content-body, Message-Body)
	                            
	    <%=request.getInputStream().available()%>
	
</pre>
<table border="1">
	<thead>
		<th>헤더명</th>
		<th>헤더값</th>
	</thead>
	<tbody>
		<%
		   Enumeration<String> em = request.getHeaderNames();
		
			while(em.hasMoreElements()){
				String name = em.nextElement();
		%>
		<tr>
			<td><%=name%></td>
			<td><%=request.getHeader(name)%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
</body>
</html>