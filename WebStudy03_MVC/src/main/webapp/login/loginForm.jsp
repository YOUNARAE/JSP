<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
</head>

<body>
<form method="post" name="loginForm" action="<c:url value='/login/loginProcess.do'/>">
	<ul>
		<li>
			
			<c:set var="saveId" value="${cookie['saveId']['value'] }" /> <!-- 연상배열구조, 쿠키의 세이브 아이디의 배열을 찾는 -->
			<input type="text" name="memId" placeholder="아이디" value="${not empty validId ? validId : saveId }"/> <!-- 연결되어서 왔기때문에 이렇게 불러쓸 수 있다 -->
			<input type="checkbox" name="saveId"  ${not empty saveId ? 'checked' : '' } />아이디 기억하기			
			<c:remove var="validId" scope="session" />
			<!-- 
			미션.
			1.체크박스를 체크했을 땐 5일동안 들어왔을 때 내가 입력했던 아이디가 들어가있어야한다 
			2.체크박스를 체크하지 않았을 때 로그인했을 때 기존에 저장ㅎ되어있는 쿠키까지 지워야한다. -> 이 고려들은 로그인을 성공했을때만 고려하면 된다.
			-->
		</li>
		<li>
			<input type="password" name="memPass" placeholder="비밀번호"/>
			<input type="submit" value="로그인">
		</li>
	</ul>
</form>
</body>
</html>