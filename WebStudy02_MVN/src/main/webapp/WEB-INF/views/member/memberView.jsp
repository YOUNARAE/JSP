<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/memberView.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>회원 상세 보기</h4>
<form method="post">
	<table border="1">
		<tbody>
			<tr>
				<th>회원아이디</th>
				<td>${member.memId}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>${member.memPass}</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td>${member.memName}</td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td>${member.memRegno1}</td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td>${member.memRegno2}</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>${member.memBir}</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>${member.memZip}</td>
			</tr>
			<tr>
				<th>주소1</th>
				<td>${member.memAdd1}</td>
			</tr>
			<tr>
				<th>주소2</th>
				<td>${member.memAdd2}</td>
			</tr>
			<tr>
				<th>집전번</th>
				<td>${member.memHometel}</td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td>${member.memComtel}</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>${member.memHp}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${member.memMail}</td>
			</tr>
			<tr>
				<th>직업</th>
				<td>${member.memJob}</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>${member.memLike}</td>
			</tr>
			<tr>
				<th>기념일</th>
				<td>${member.memMemorial}</td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td>${member.memMemorialday}</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>${member.memMileage}</td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td>${member.memDelete}</td>
			</tr>
			<tr>
				<!-- 선택적인 렌더링이 필요해지는 부분 -->
				<c:if test="${sessionScope.authMember eq member }"> <!-- 세션스코프에 있는 로그인되어있는 아이디와 멤버에 저장되어있는 로그인정보 , vo에 아이디 비교하는 부분 -->
					<td colspan="2">
						<a href="<c:url value='/member/memberUpdate.do'/>">수정</a>
						<a href="#" onclick="fn_delete();" >탈퇴</a>
						<form method="post" action="<c:url value='/member/memberDelete.do'/>" id="deleteForm">
							<input type="password" name="memPass" required"/>
							<span class="text-danger">${errors.memPass}</span>
						</form>
					</td>
				</c:if>
			</tr>
		</tbody>
	</table>
</form>
<jsp:include page="/includee/postScript.jsp"/>
<script type="text/javascript">
function fn_delete(){
	if( confirm("삭제할꺼냐하시겠습니까?") ){
		$("#deleteForm").submit();
	}else{
		return false;
	}
}
$('#btn_cancel').on('click',function(){
	location.href="<%=request.getContextPath()%>/UsrClsList.do"
});
</script>
</body>
</html>