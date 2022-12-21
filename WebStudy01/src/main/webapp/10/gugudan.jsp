<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table {
		border:solid 1px;
	}
	
	table td {
		border:1px solid #000;
	}
		
	.red {
		background:red;
	}
		
	.green {
		background:green;
	}
		
</style>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<!-- 1. JSTL 과 EL 2단부터 9단까지 구구단 출력(table태그 활용). -->
<!-- 	(2*3=6) -->
<!-- 2. 세번째 row의 배경색을 빨간색으로 출력(inline css 속성 사용하지 않도록) -->
<!-- <table> -->
<!-- 	<tr> -->
<%-- 	<c:forEach var="i" begin="2" end="9" > --%>
<!-- 		<td> -->
<%-- 			<h4>${i}단</h4> --%>
<%-- 			<c:forEach var="cnt" begin="1" end="9" step="1"> --%>
<!-- 			<p> -->
<%-- 				${cnt } * ${i } = ${cnt * i } --%>
<!-- 			</p> -->
<%-- 			</c:forEach> --%>
<!-- 		</td> -->
<%-- 	</c:forEach> --%>
<!-- 	</tr> -->
<!-- </table> -->
<c:set var="minDan" value="${empty param.min ? 2 : param.min }"/>
<c:set var="maxDan" value="${not empty param.max ? param.max : 9 }"/>


	
<form>
	<select name="min">
		<c:forEach var="dan1" begin="2" end="9">
			<option value="${dan1}" ${dan1 eq minDan ? "selected" : ""}>${dan1}단</option>
		</c:forEach>
	</select>
	<select name="max">
		<c:forEach var="dan2" begin="2" end="9">
		<option value="${dan2}" ${dan2 eq maxDan ? "selected" : ""}>${dan2}단</option>
		</c:forEach>
	</select>
	<button type="submit" class="submit_bt">SUBMIT</button>
</form>
<table>
	<c:forEach var="i" begin="${minDan}" end="${maxDan}" varStatus="vs">
		<c:choose>
			<c:when test="${vs.count eq 3}">
				<c:set var="clzAttr" value="red"/>
			</c:when>
			<c:when test="${vs.last}">
				<c:set var="clzAttr" value="green"/>
			</c:when>
			<c:otherwise>
				<c:set var="clzAttr" value="normal"/>
			</c:otherwise>
		</c:choose>
		<tr class="${clzAttr}">
		<c:forEach var="j" begin="1" end="9">
			<td>${i}*${j} = ${i*j }</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table>

<script type="text/javascript">
// 	$("[name=min]").val("${minDan}");
// 	$("[name=max]").val("${maxDan}");
	
	
let submitBtn = $(".submit_bt").on("click", function(event){
//		alert("1번방식");
	console.log("뭐냐");
	//버튼을 눌렀을 때
});
</script>
</body>
</html>