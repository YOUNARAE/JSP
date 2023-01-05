<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prod/prodForm.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test = "${not empty message}"><!-- 메세지가 있으면 -->
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>
<h4>상품 등록하기</h4>
<form method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>상품아이디</th>
			<td><input class="form-control" type="text" readonly 
				name="prodId" value="${prod.prodId}" /><span class="text-danger">${errors.prodId}</span></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><input class="form-control" type="text" 
				name="prodName" value="${prod.prodName}" /><span
				class="text-danger">${errors.prodName}</span></td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td><select name="prodLgu">
					<option value>분류</option>
					<c:forEach items="${lprodList }" var="lprod"> <!-- lprod로 접근하는 타입은Map이다 -->
						<option value="${lprod.lprodGu}"
							${lprod.lprodGu eq prod.prodLgu? "selected" : ""}
						>
						${lprod.lprodNm}</option>
					</c:forEach>
				</select>
				<span class="text-danger">${errors.prodLgu}</span>
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<select name="prodBuyer">
					<option value>거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option value="${buyer.buyerId}" class="${buyer.buyerLgu}" 
							${buyer.buyerId eq prod.prodBuyer? "selected" : ""}
						>
						${buyer.buyerName}</option>
					</c:forEach>
				</select>
				<span class="text-danger">${errors.prodBuyer}</span>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><input class="form-control" type="number" 
				name="prodCost" value="${prod.prodCost}" /><span
				class="text-danger">${errors.prodCost}</span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input class="form-control" type="number" 
				name="prodPrice" value="${prod.prodPrice}" /><span
				class="text-danger">${errors.prodPrice}</span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input class="form-control" type="number" 
				name="prodSale" value="${prod.prodSale}" /><span
				class="text-danger">${errors.prodSale}</span></td>
		</tr>
		<tr>
			<th>상품요약</th>
			<td><input class="form-control" type="text" 
				name="prodOutline" value="${prod.prodOutline}" /><span
				class="text-danger">${errors.prodOutline}</span></td>
		</tr>
		<tr>
			<th>상품상세</th>
			<td><input class="form-control" type="text" name="prodDetail"
				value="${prod.prodDetail}" /><span class="text-danger">${errors.prodDetail}</span></td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
				<input type="file" name="prodImage" accept="image/*"/>
				<!-- img는 디비용, image는 디비로 보낼때 쓰는 클라이언트용 -->
				<!-- <input class="form-control" type="text" 
				name="prodImg" value="${prod.prodImg}" />
				prodId는 디비에서 따온 것이라 저 아이디로 쓸 수 없다-->
				<span class="text-danger">${errors.prodImg}</span></td> 
		</tr>
		<tr>
			<th>재고</th>
			<td><input class="form-control" type="number" 
				name="prodTotalstock" value="${prod.prodTotalstock}" /><span
				class="text-danger">${errors.prodTotalstock}</span></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><input class="form-control" type="date" name="prodInsdate"
				value="${prod.prodInsdate}" /><span class="text-danger">${errors.prodInsdate}</span></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><input class="form-control" type="number" 
				name="prodProperstock" value="${prod.prodProperstock}" /><span
				class="text-danger">${errors.prodProperstock}</span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input class="form-control" type="text" name="prodSize"
				value="${prod.prodSize}" /><span class="text-danger">${errors.prodSize}</span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input class="form-control" type="text" name="prodColor"
				value="${prod.prodColor}" /><span class="text-danger">${errors.prodColor}</span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input class="form-control" type="text" name="prodDelivery"
				value="${prod.prodDelivery}" /><span class="text-danger">${errors.prodDelivery}</span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input class="form-control" type="text" name="prodUnit"
				value="${prod.prodUnit}" /><span class="text-danger">${errors.prodUnit}</span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input class="form-control" type="number" name="prodQtyin"
				value="${prod.prodQtyin}" /><span class="text-danger">${errors.prodQtyin}</span></td>
		</tr>
		<tr>
			<th>출고량</th>
			<td><input class="form-control" type="number"
				name="prodQtysale" value="${prod.prodQtysale}" /><span
				class="text-danger">${errors.prodQtysale}</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input class="form-control" type="number"
				name="prodMileage" value="${prod.prodMileage}" /><span
				class="text-danger">${errors.prodMileage}</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" />
				<input type="reset" value="취소" />
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	let prodBuyerTag = $("[name=prodBuyer]");
	$("[name=prodLgu]").on("change", function(){
		let prodLgu = $(this).val(); //셀렉트 쿼리라서 바로 네임으로 못 꺼내니까 제이쿼리화 시켜서 val값으로 꺼낸다
		if(prodLgu){
			//prodLgu가 적정한 값을 갖고 있으면
			prodBuyerTag.find("option:gt(0)").hide(); // 0번째 제외하고 가리기
			prodBuyerTag.find("option."+prodLgu).show();				
		}
	}).trigger("change");
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>