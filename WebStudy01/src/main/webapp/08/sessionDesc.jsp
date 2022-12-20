<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/sessionDesc.jsp</title>
</head>
<body>
<h4>session(HttpSession)</h4>
<h4 id="timerArea"></h4>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom.js"></script>
<pre>
	(웹)세션이란? 
	    : 어플리케이션 서버를 사용하기 시작한 순간부터 사용 종료까지의 기한.
	    
	시작(생성) :  클라이언트의 최초 요청(재전송되는 아이디가 없는 요쳥) 발생. -> 식별자가 부여된 세션이 새로 생성.
				->세션 아이디가 요청에 대한 응답이 전소오딜 때 응답 헤더에 포함되어 클라이언트로 전송.
		세션아이디 : <%=session.getId() %>
		세션 생성 시점 : <%=new Date(session.getCreationTime()) %>
		마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
		timeout : <%=session.getMaxInactiveInterval() %>/s
		
		유지(tracking mode) : 세션 식별자(세션 아이디) 재전송 구조.
		1) COOKIE
		2) URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">URL트레킹모드</a>
		3) SSL(Secur Socket Layer) : 암호화라는 개념이 베이스로 깔려있다.
		
		소멸되는 기준 : 직접 로그아웃을 클릭한 경우(명확한 세션 만료 시점)
		* 쿠키를 지웠다고 해서 세션이 만료되었는지를 서버에서 확실하게 알 수는 없다
		
	종료(만료) : 
		1) 세션의 아이디가 재전송되지 않을 때. ex) 세션과 관련된 쿠키 삭제
		2) 브라우저가 종료될 때
		3) session timeout 이내에 새로운 요청을 통해 아이디가 재전송되지 않을 때.
		4) session invalidation(명시적인 로그아웃)
		
</pre>
<div id="msgArea">
	세션을 연장하시곘습니까?
	<input type="button" value="예" class="controlBtn" id="YES"/>
	<input type="button" value="아니오" class="controlBtn" id="NO"/>
</div>
<script>
	$("#timerArea").sessionTimer( ${pageContext.session.maxInactiveInterval}, {
		msgAreaSelector : "#msgArea", 
		btnSelector : ".controlBtn" 
	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>