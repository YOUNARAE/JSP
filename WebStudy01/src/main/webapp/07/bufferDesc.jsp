<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/bufferDesc.jsp</title>
</head>
<body>
<h4>out : JspWriter</h4>
<pre>
	출력 버퍼 관리자.
	현재 버퍼의 크기 : <%=out.getBufferSize() %>
	잔여 버퍼의 크기 : <%=out.getRemaining() %>
	auto flush 지원 여부 : <%=out.isAutoFlush() %>
	<%
		for(int i=1;i<=100;i++){
			out.println(i+"번쨰 반복");
			if(out.getRemaining()<50)
				out.flush();
			if(i==100)
// 				throw new RuntimeException("강제 발생 예외");
// 				request.getRequestDispatcher("/02/standard.jsp").include(request, response);
				pageContext.include("/02/standard.jsp", false); //이동하기 전에 먼저 플러시 true, 이동하지 않은 상태에서 플러시를 시키는 것이 false
		}
	%>
</pre>
</body>
</html>