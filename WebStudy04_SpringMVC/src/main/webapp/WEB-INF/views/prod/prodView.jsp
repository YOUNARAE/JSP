<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prodView.jsp</title>
</head>
<body>
	<table>
		<tr>
			<th>상품아이디</th>
			<td>${prod.prodId}</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prodName}</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>${prod.lprodNm}</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<table>
					<thead>
					<tr>
						<th>거래처명</th>
						<th>담당자명</th>
						<th>지역</th>
					</tr>
					</thead>
					<tbody>
					<c:set var="buyer" value="${prod.buyer }"/>
					<tr>
						<td>
							<c:url value="/buyer/buyerView.do" var="buyerViewURL">
								<c:param name="what" value="${ buyer.buyerId}"></c:param>
							</c:url>
							<a href="${buyerViewURL }">${buyer.buyerName }</a>
						</td>
						<td>${buyer.buyerCharger}</td>
						<td>${buyer.buyerAdd1 }</td>
					</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prodCost}</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prodPrice}</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prodSale}</td>
		</tr>
		<tr>
			<th>상품요약</th>
			<td>${prod.prodOutline}</td>
		</tr>
		<tr>
			<th>상품상세</th>
			<td>${prod.prodDetail}</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
				<img src="${pageContext.request.contextPath }/recources/prodImages/${prod.prodImg}" /> <!-- 클라이언트 사이드가 되려면 ${pageContext.request.contextPath }붙인다 -->
			</td>
		</tr>
		<tr>
			<th>재고</th>
			<td>${prod.prodTotalstock}</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prodInsdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prodProperstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor}</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prodDelivery}</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prodUnit}</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prodQtyin}</td>
		</tr>
		<tr>
			<th>출고량</th>
			<td>${prod.prodQtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prodMileage}</td>
		</tr>
		<tr>	
			<c:url value='/prod/' var="listURL" />
			<a class="btn" style="border:3px solid red" href="${listURL }">목록으로</a>
			<c:url value="/prod/prodUpdate.do" var="updateURL">
				<c:param name="what" value="${prod.prodId}"></c:param>
			</c:url>
			<a href="${updateURL }"  style="border:3px solid red" class="btn" >상품수정</a>
		</tr>
		<tr>
			<th>구매목록</th>
			<td>
				<table>
					<thead>
						<th>구매자명</th>
						<th>휴대폰</th>
						<th>이메일</th>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty prod.memberSet }">
								<c:forEach items="${prod.memberSet }" var="user">
									<c:url value="/member/memberView.do" var="memberViewURL">
										<c:param name="who" value="${ user.memId }" />
									</c:url>
									<tr>
										<td>
											<a href="${memberViewURL }">${user.memName }</a>
										</td>
										<td>${user.memHp }</td>
										<td>${user.memMail }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="3">제발 팔려라! </td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
					<tr>
						<td>
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
												<button type="submit" data-bs-toggle="modal" data-bs-target="#exampleModal">삭제</a>
											</div>
										</form>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>