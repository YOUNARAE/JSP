<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prod/prodForm.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test="${not empty message}">
	<!-- 메세지가 있으면 -->
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>
	<h4>상품 등록하기</h4>
	<form:form method="post" modelAttribute="prod" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품아이디</th>
				<td><form:input path="prodId" class="form-control" readonly="true" /> 
				<form:errors path="prodId" element="span" cssClass="text-danger" /></td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><form:input path="prodName" class="form-control" /> <form:errors
						path="prodName" element="span" cssClass="text-danger" /></td>
			</tr>
			<tr>
				<th>상품분류</th>
				<td><form:select path="prodLgu">
						<option value>분류</option>
						<c:forEach items="${lprodList }" var="lprod">
							<!-- lprod로 접근하는 타입은Map이다 -->
							<form:option value="${lprod.lprodGu}" label="${lprod.lprodNm}" />
						</c:forEach>
					</form:select> <form:errors path="prodLgu" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>거래처</th>
				<td>
					<%-- <form:select path="prodBuyer" items="${buyerList }" itemLabel="buyerName" /> --%>
					<form:select path="prodBuyer">
						<option value>거래처</option>
						<c:forEach items="${buyerList }" var="buyer">
							<form:option value="${buyer.buyerId}" label="${buyer.buyerName}"
								cssClass="${buyer.buyerLgu}" />
						</c:forEach>
					</form:select> <form:errors path="prodBuyer" element="span"
						cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>구매가</th>
				<td>
					<form:input path="prodCost" class="form-control" />
					<form:errors path="prodCost" element="span" cssClass="text-danger" />
				</td>

			</tr>
			<tr>
				<th>판매가</th>
				<td>
					<form:input path="prodPrice" class="form-control" />
					<form:errors path="prodPrice" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>세일가</th>
				<td>
					<form:input path="prodSale" class="form-control" />
					<form:errors path="prodSale" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>상품요약</th>
				<td>
					<form:input path="prodOutline" class="form-control" />
					<form:errors path="prodOutline" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>상품상세</th>
				<td>
					<form:input path="prodDetail" class="form-control" />
					<form:errors path="prodDetail" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td>

				<input type="file" name="prodImage" class="form-control"/>
				<form:errors path="prodImage" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td>
					<form:input path="prodTotalstock" class="form-control"  type="number"/>
					<form:errors path="prodTotalstock" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>입고일</th>
				<td><input class="form-control" type="date" name="prodInsdate"
					value="${prod.prodInsdate}" /><span class="text-danger">${errors.prodInsdate}</span></td>
			</tr>
			<tr>
				<th>적정재고</th>
				<td>
					<form:input path="prodProperstock" class="form-control" type="number"/>
					<form:errors path="prodProperstock" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>크기</th>
				<td>
					<form:input path="prodSize" class="form-control" />
					<form:errors path="prodSize" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>색상</th>
				<td>
					<form:input path="prodColor" class="form-control" />
					<form:errors path="prodColor" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>배송방법</th>
				<td>
					<form:input path="prodDelivery" class="form-control" />
					<form:errors path="prodDelivery" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>단위</th>
				<td>
					<form:input path="prodUnit" class="form-control" />
					<form:errors path="prodUnit" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>입고량</th>
				<td>
					<form:input path="prodQtyin" class="form-control" />
					<form:errors path="prodQtyin" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>출고량</th>
				<td>
					<form:input path="prodQtysale" class="form-control" type="number"/>
					<form:errors path="prodQtysale" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>
					<form:input path="prodMileage" class="form-control" type="number"/>
					<form:errors path="prodMileage" element="span" cssClass="text-danger" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장" /> <input
					type="reset" value="취소" /></td>
			</tr>
		</table>
		</form>
	</form:form>
	<script type="text/javascript">
		$("[name=prodLgu]").on("change", function() {
			let prodLgu = $(this).val(); //셀렉트 쿼리라서 바로 네임으로 못 꺼내니까 제이쿼리화 시켜서 val값으로 꺼낸다
			prodBuyerTag.find("option:gt(0)").hide(); // 0번째 제외하고 가리기
			prodBuyerTag.find("option." + prodLgu).show();
		});
		let prodBuyerTag = $("[name=prodBuyer]");
	</script>
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>