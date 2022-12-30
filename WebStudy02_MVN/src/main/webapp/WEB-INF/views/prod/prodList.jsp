<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/prod/prodList.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>일렬번호</th>
			<th>상품분류</th>
			<th>상품명</th>
			<th>거래처명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>상품구매자수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="prodList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty prodList }">
				<c:forEach items="${prodList }" var="prod">
					<tr>
						<td>${prod.rnum }</td>
						<td>${prod.lprodNm }</td>
						<td>
							<c:url value="/prod/prodView.do" var="viewURL">
								<c:param name="who" value="${prod.prodId }"/>
							</c:url>
							<a href="${viewURL }">${prod.prodName }</a>
						</td>
						<td>${prod.buyer.buyerName}</td>
						<td>${prod.prodCost }</td>
						<td>${prod.prodPrice }</td>
						<td>${prod.cartQty }</td>
						
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">조건에 맞는 회원이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				${pagingVO.pagingHTML }
				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="lprodNm">분류명</option>
						<option value="buyerName">거래처명</option>
						<option value="prodName">상품명</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="button" value="검색" id="searchBtn" /> 
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<h4>Hidden Form</h4>
<form id="searchForm">
	<input type="text" name="page" />
	<input type="text" name="searchType" />
	<input type="text" name="searchWord" />
</form>
<script type="text/javascript">
	$("[name=searchType]").val("${pagingVO.simpleCondition.searchType}");
	$("[name=searchWord]").val("${pagingVO.simpleCondition.searchWord}");
	
	let searchForm = $("#searchForm");
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(){
		let inputs = searchUI.find(":input[name]"); //: 붙이면 그 안에 모든 게 대상이 됨
		$.each(inputs, function(index, input){ //인덱스와 하나의 인풋 테이터를 받을 수 있는 블록 변수
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
	
	$("a.paging").on("click", function(event){
		event.preventDefault();
		//page란 데이터에서 몇페이지를 클릭했는지를 찾아야한다.
		let page = $(this).data("page");
		if(!page) return false;
		searchForm.find("[name=page]").val(page); //여기에 벨류를 셋팅해줘야한다
		searchForm.submit();
		return false;
	});
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>