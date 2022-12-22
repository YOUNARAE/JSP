<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<!-- 
1일이 언제 시작하느냐가 중요함, 현재 요일일 떄 일주일일때 몇번째인지를 생각해라
 -->
<c:set var="dayCount" value="1"/> <!-- 하루씩 증가 -->
<c:set var="offset" value="${calendar.offset }" />
<c:set var="weekDays" value="${calendar.weekDays }"/>

<body>

<form>
	
</form>
<table>
	<h4>${calendar.toString() }</h4>
	<thead>
		<tr>
			<!-- 여기 7번 돌아야합니다 -->
			<c:forEach var="idx" begin="<%= Calendar.SUNDAY %>" end="<%= Calendar.SATURDAY %>">
   			  <td>${weekDays[idx]}</td>
			</c:forEach>
		</tr>
	</thead>
	<tboby>
		<!-- 여기가 6번 돌아야하고 -->
		<c:forEach begin="1" end="6">
		<tr>
			<!-- 여기가 7번 돌아야하는데 -->
			<c:forEach begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">
				<c:set var="dayStr" value="${dayCount - offset}" />
					<td>${dayStr }</td>
			</c:forEach>
		</tr>
		</c:forEach>
	</tboby>
</table>
</body>
</html>