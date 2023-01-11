<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/memberView.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
<c:if test = "${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
<%-- 	<c:remove var="message" scope="session"/> //autoFlash방식때문에 없어져도 됨. --%>
</c:if>
</head>
<body>
<h4>회원 상세 보기</h4>
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
				<th>프로필</th>
				<td><img src="data:image/*;base64,${member.base64MemImg}" /></td>
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
						<a data-bs-toggle="modal" data-bs-target="#exampleModal">탈퇴</a>
					</td>
				</c:if>
			</tr>
			<tr>
				<th>구매기록</th>
				<td>
					<table>
						<thead>
							<tr>
								<th>상품아이디</th>
								<th>상품명</th>
								<th>분류명</th>
								<th>거래처명</th>
								<th>구매가</th>
								<th>판매가</th>
								<th>마일리지</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="prodList" value="${member.prodList}"/>
							<c:choose>
								<c:when test="${not empty prodList }">
									<c:forEach items="${prodList }" var="prod">
										<tr>
											<td>
												<c:url value="/prod/prodView.do" var="prodViewURL">
													<c:param name="what" value="${prod.prodId }" />
												</c:url>
												<a href="${prodViewURL }">${prod.prodId}<a/></td>
											<td>${prod.prodName}</td>
											<td>${prod.lprodNm}</td>
											<td>${prod.buyer.buyerName}</td>
											<td>${prod.prodCost}</td>
											<td>${prod.prodPrice}</td>
											<td>${prod.prodMileage}</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="7"> 구매기록 없음</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
						
					</table>
				</td>
			</tr>
			
		</tbody>
	</table>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form method="post" action="<c:url value='/member/memberDelete.do'/>">
					<div class="modal-body">
						<input type="password" name="memPass" required"/>
					</div>
					<div class="modal-footer">
						<button type="submit" data-bs-toggle="modal" data-bs-target="#exampleModal">탈퇴</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$('#exampleModal').on("hidden.bs.modal", function(evnet){
// 		$(this).find("form").get(0)
		$(this).find("form")[0].reset();
		
	});
	</script>
	<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>