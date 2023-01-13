<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberList</title>
</head>
<body>
<table border="1">
		<c:forEach items="${memberList }" var="memberList">
			<tr>
				<td>${memberList.memId }</td>
				<td>${memberList.memPass }</td>
				<td>${memberList.memName }</td>
			</tr>
		</c:forEach>
</table>
</body>
</html>