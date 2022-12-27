<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/memberForm.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test = "${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>
<form method="post"> <!-- 여기까지 올 때는 겟이지만 포스트가 된다 -->
	<h4>회원가입하라</h4>
	<table>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="memId" placeholder="아이디" required /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="memPass" placeholder="비밀번호" /></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input type="text" name="memName" placeholder="회원명" value="${member.memName}"/></td>
		</tr>
		<tr>
			<th>주민번호</th>
			<td><input type="text" name="memRegno1" placeholder="주민번호 앞자리"/>-<input type="text" name="memRegno2" placeholder="주민번호 뒷자리"/></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><input type="date" name="memBir" placeholder="생년월일"/></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="memZip" placeholder="우편번호"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="memAdd1" placeholder="주소">-<input type="text" name="memAdd2" placeholder="상세주소"></td>
		</tr>
		<tr>
			<th>집전화</th>
			<td><input type="text" name="memHometel" placeholder="집전화"></td>
		</tr>
		<tr>
			<th>회사전화</th>
			<td><input type="text" name="memComtel" placeholder="회사전화"></td>
		</tr>
		<tr>
			<th>핸드폰번호</th>
			<td><input type="text" name="memHp" placeholder="핸드폰번호"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="memMail" placeholder="이메일"></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob" placeholder="직업"></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike" placeholder="취미"></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input type="text" name="memMemorial" placeholder="기념일"></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input type="date" name="memMemorialday" placeholder="기념일자"></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="text" name="memMileage" placeholder="마일리지"></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><input type="text" name="memDelete" placeholder="탈퇴여부"></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="전송" />
			</td>
		</tr>
	</table>
</form>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>