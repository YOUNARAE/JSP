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
	<tbody id="listBody">
<%-- 		<c:set var="prodList" value="${pagingVO.dataList }" /> --%>
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${not empty prodList }"> --%>
<%-- 				<c:forEach items="${prodList }" var="prod"> --%>
<!-- 					<tr> -->
<%-- 						<td>${prod.rnum }</td> --%>
<%-- 						<td>${prod.lprodNm }</td> --%>
<%-- 						<td>${prod.buyer.buyerName}</td> --%>
<!-- 						<td> -->
<%-- 							<c:url value="/prod/prodView.do" var="viewURL"> --%>
<%-- 								<c:param name="who" value="${prod.prodId }"/> --%>
<%-- 							</c:url> --%>
<%-- 							<a href="${viewURL }">${prod.prodName }</a> --%>
<!-- 						</td> -->
<%-- 						<td>${prod.prodCost }</td> --%>
<%-- 						<td>${prod.prodPrice }</td> --%>
<%-- 						<td>${prod.memCount }</td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<!-- 				<tr> -->
<!-- 					<td colspan="7">조건에 맞는 회원이 없음.</td> -->
<!-- 				</tr> -->
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> <!-- 동기요청으로 데이터를 안 가져온다는 의미 --> --%>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<div class="pagingArea">
					<%-- ${pagingVO.pagingHTML } --%>
				</div>
				<div id="searchUI">
					<select name="prodLgu">
						<option value>분류</option>
						<c:forEach items="${lprodList }" var="lprod"> <!-- lprod로 접근하는 타입은Map이다 -->
							<option value="${lprod.lprodGu}">${lprod.lprodNm}</option>
						</c:forEach>
					</select>
					<!-- VO를 쓰냐 맵을 쓰나 모양은 똑같다 -->
					<select name="prodBuyer">
						<option value>거래처</option>
						<c:forEach items="${buyerList }" var="buyer">
							<option value="${buyer.buyerId}" class="${buyer.buyerLgu}">${buyer.buyerName}</option>
						</c:forEach>
					</select>
					
<!-- 				<input type="text" name="prodLgu" placeholder="분류코드" />
					<input type="text" name="prodBuyer" placeholder="거래처코드" /> -->
					<input type="text" name="prodName" placeholder="상품명" />
					<input type="button" value="검색" id="searchBtn" /> 
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<h4>Hidden Form</h4>
<form id="searchForm">
	<input type="text" name="page" />
<!-- 	<input type="text" name="searchType" /> 여기 이름들은 데이터 UI를 따라간다 -->
<!-- 	<input type="text" name="searchWord" /> -->
	<input type="text" name="prodLgu" placeholder="분류코드" />
	<input type="text" name="prodBuyer" placeholder="거래처코드" />
	<input type="text" name="prodName" placeholder="상품명" />
</form>
<script type="text/javascript">
// 	$("[name=searchType]").val("${pagingVO.simpleCondition.searchType}");
// 	$("[name=searchWord]").val("${pagingVO.simpleCondition.searchWord}");
	//컨트롤러에서 객체 하나 만들어줘서 paginVO를 빼도 된다
	$("[name=prodLgu]").on("change", function(){
		let prodLgu = $(this).val(); //셀렉트 쿼리라서 바로 네임으로 못 꺼내니까 제이쿼리화 시켜서 val값으로 꺼낸다
		prodBuyerTag.find("option:gt(0)").hide(); // 0번째 제외하고 가리기
		prodBuyerTag.find("option."+prodLgu).show();	
	});
// 	.val("${detailCondition.prodLgu}");
// 	let prodBuyerTag = $("[name=prodBuyer]").val("${detailCondition.prodBuyer}");
	let prodBuyerTag = $("[name=prodBuyer]");
// 	$("[name=prodName]").val("${detailCondition.prodName}");
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea").on("click", "a.paging", function(event){ //div를 클릭하는 게 아니라 그 안에 있는 애를 클릭한다
		event.preventDefault();
		//page란 데이터에서 몇페이지를 클릭했는지를 찾아야한다.
		let page = $(this).data("page");
		if(!page) return false; //페이지가 없을 땐 아무것도 하지 않는다. 
		searchForm.find("[name=page]").val(page); //여기에 벨류를 셋팅해줘야한다
		searchForm.submit();
		return false;
	});
	
	let makeTrTag = function(prod){
		let aTag = $("<a>")
					.attr("href", "${pageContext.request.contextPath}/prod/prodView.do?what=" + prod.prodId) //주소방식은 클라이언트사이드
					.html(prod.prodName);
		return $("<tr>").append(
			$("<td>").html(prod.rnum)
			, $("<td>").html(aTag)
			, $("<td>").html(prod.lprodNm)
			, $("<td>").html(prod.buyer.buyerName)
			, $("<td>").html(prod.prodCost)
			, $("<td>").html(prod.prodPrice)
			, $("<td>").html(prod.memCount)
		);
	}
	
	let searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();

		let url = this.action;
		let method = this.method;
		let queryString = $(this).serialize();
		
		$.ajax({
			url : url,
			method : method, //post -새로운 메모를 작성하겠다, put->메모를 수정하겠다, 
			data : queryString,
			dataType : "json",
			success : function(resp) { //언마샬링된 스크립틀릿 자료
				//성공했을 때 리셋해준다
				listBody.empty();
				pagingArea.empty();
				searchForm[0].page.value=""; // 검색이 이상하게 되니까 초기화 시켜준다, 페이지 전체가 새로고침되는 구조가 아니기때문에 UI에 남아있는 데이터들을 리셋해주는 과정이다
		
				let pagingVO = resp.pagingVO;
			
				let dataList = pagingVO.dataList;
				let trTags = [];
				if(dataList){
					$.each(dataList, function(index, prod){//이 안에서는 인텍스와 상품 한개의 정보를 받을 수 있다.
						trTags.push(makeTrTag(prod));
					});
				}else {
					let tr = $("<tr>").html(
						$("<td>").attr("colspan","7")
								.html("조건에 맞는 상품 없음.")
					);
					trTags.push(tr);
				}
				listBody.html(trTags);
				pagingArea.html(pagingVO.pagingHTML);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});		
		
		return false;
	}).submit();// 페이즌 ㄴ렌더링 도ㅒㅆ는데 데이터를 요청은 넘어가지 않는 상황
	
	let searchUI = $("#searchUI").on("click", "#searchBtn", function(){ //클릭한 것에서 한번 더 골라낸다 (이벤트의 더블링 구조에서 한번 더 셀렉팅을 하고 있는 구조다, 이걸 넣음으로서 this가 serchBtn이된다)
		//제일 먼저 서치 UI에 있는 모든 값들을 꺼내오는 것부터 처리한다	
		let inputs = searchUI.find(":input[name]"); //: 붙이면 그 안에 모든 게 대상이 되기때문에 파라미터로 넘기고 있는 네임속성을 잡아준다음 inputs에 넣어준다.
		$.each(inputs, function(index, input){ //반복문을 돌려서 inputs을 넣어 돌린다. 인덱스와 하나의 인풋 테이터를 받을 수 있는 블록 변수
			let name = this.name;
			let value = $(this).val(); //값을 꺼낼때는 this를 제이쿼리로 꺼내서 값을 꺼낸다. 그냥 select쿼리로 꺼내면 값이 안 온다
// 			searchForm.find("[name="+name+"]").val(value);
// 			searchForm.get(0) //searchForm에 숨어있는 html를 꺼내 쓸 수 있다
// 			searchForm.get(0)['searchType']
			searchForm[0][name].value = value; //앞에선 단노테이션인데 뒤에는 연상배열이니까 (0)을 [0] 사각배열로 바꿔줄 수 있다
		});
		searchForm.submit();
	});
	
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>