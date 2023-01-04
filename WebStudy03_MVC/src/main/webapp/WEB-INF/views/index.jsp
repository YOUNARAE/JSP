<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<h4>WELCOME</h4>
<c:choose>
	<c:when test="${not empty sessionScope.authMember }">
		<h4>로그인된 사용자??? : <%=request.getUserPrincipal() %></h4>
		<a href="<c:url value='/mypage.do'/>">${authMember.memName }[${authMember.memRole }] 님</a>
		<form name="logoutForm" action="<c:url value='/login/logout.do'/>" method="post"></form>
<%-- 	<a href="<c:url value='/login/loginOut.do'/>"> --%> 
<%-- 로그인 인증과 관련된 구조는 모든 것들은 포스트 방식이어야한다. 그래서 폼태그를 이용해야해서 a에 있는 주소를 제거하고 온클릭이벤트를 생성한다 --%>
		<a href="#" class="logoutBtn">로그아웃</a>
		<script>
			$(".logoutBtn").on("click", function(event){
				event.preventDefault();
				document.logoutForm.submit();
				return false;
			});
		</script>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login/loginForm.jsp'/>">로그인</a>
		<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
	</c:otherwise>
</c:choose>