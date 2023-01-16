<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:if test = "${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
<table class="table">
<tr>
	<th>글번호</th>
	<td><input class="form-control" type="number" required name="boNo"
		value="${board.boNo}" /><span class="text-danger">${errors.boNo}</span>
	</td>
</tr>
<tr>
	<th>제목</th>
	<td><input class="form-control" type="text" required
		name="boTitle" value="${board.boTitle}" /><span
		class="text-danger">${errors.boTitle}</span>
	</td>
</tr>
<tr>
	<th>작성자</th>
	<td><input class="form-control" type="text" required
		name="boWriter" value="${board.boWriter}" /><span
		class="text-danger">${errors.boWriter}</span></td>
</tr>
<tr>
	<th>아이피</th>
	<td><input class="form-control" type="text" required name="boIp"
		value="${board.boIp}" /><span class="text-danger">${errors.boIp}</span>
	</td>
</tr>
<tr>
	<th>이메일</th>
	<td><input class="form-control" type="text" name="boMail"
		value="${board.boMail}" /><span class="text-danger">${errors.boMail}</span>
	</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input class="form-control" type="text" required name="boPass"
		value="${board.boPass}" /><span class="text-danger">${errors.boPass}</span>
	</td>
</tr>
<tr>
	<th>내용</th>
	<td><input class="form-control" type="text" name="boContent"
		value="${board.boContent}" /><span class="text-danger">${errors.boContent}</span>
	</td>
</tr>
<tr>
	<th>작성일</th>
	<td><input class="form-control" type="date" name="boDate"
		value="${board.boDate}" /><span class="text-danger">${errors.boDate}</span></td>
</tr>
<tr>
	<th>조회수</th>
	<td><input class="form-control" type="number" name="boHit"
		value="${board.boHit}" /><span class="text-danger">${errors.boHit}</span></td>
</tr>
<c:if test="${not empty board.attatchList}">
<tr>
	<th>첨부파일</th>
	<td>
		<c:forEach items="${board.attatchList}" var="attatch" varStatus="vs">
			${attatch.attFilename } ${vs.last? "" : "," } 
		</c:forEach>
	</td>
</tr>
</c:if>
<tr>
	<c:if test="${sessionScope.authMember eq board }"> <!-- 세션스코프에 있는 로그인되어있는 아이디와 멤버에 저장되어있는 로그인정보 , vo에 아이디 비교하는 부분 -->
		<td colspan="2">
			<a href="<c:url value='/member/memberUpdate.do'/>">수정</a>
			<a data-bs-toggle="modal" data-bs-target="#exampleModal">탈퇴</a>
		</td>
	</c:if>
</tr>
</table>