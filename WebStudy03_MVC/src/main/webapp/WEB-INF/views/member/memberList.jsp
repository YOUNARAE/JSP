<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/memberList.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>회원 목록 조회</h4>

<table>
	<thead>
		<tr>
			<th>일련번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>이메일</th>
			<th>휴대폰</th>
			<th>거주지역</th>
			<th>마일리지</th>
			<th>구매건수</th>
		</tr>
	</thead>
	<tbody id="listBody">
		<c:set var="memberList" value="${pagingVO.dataList}"/>
		<c:choose>
			<c:when test="${not empty memberList }"><!-- 존재여부 확인 -->
				<c:forEach items="${memberList }" var="member">
				<tr>
					<td>${member.rnum }</td>
					<td>${member.memId }</td>
					<td>
						<c:url value="/member/memberView.do" var="viewURL">
							<c:param name="who" value="${member.memId }"/>
						</c:url>
						<a href="${viewURL }">${member.memName }</a>
					</td>
					<td>${member.memMail }</td>
					<td>${member.memHp }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMileage }</td>
					<td>${member.cartCount }</td>
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
<!-- 			<a href="?page=1">1</a> pageContext가 물음표 앞에 생략 , startPage ~ endPage까지의 숫자가 필요하다  -->
				${pagingVO.pagingHTML }
				<div id="searchUI"><!-- action이 없다는 건 지금 주소를 다시 사용한다는 것 -->
<!-- 				밑에 폼을 붙여서 지워도 됨	<input type="text" name="page" /> 언제나 여기서 page까지 3가지 파라미터가 넘어갈 수 있게 된다 -->
					<select name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">주소1</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="button" value="검색" id="searchBtn" /> 
<!-- div로 바껴서 서밋 버튼의 존재 이유가 없어짐<input type="submit" value="검색" /> 요청이 발생할 때 방향은 리스트 컨트롤러, 발생하는 요청은 doGet, 파라미터, 서치 타입과 서치 워드 2개 -->
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
// 	$("[name=searchType]").val("${pagingVO.simpleCondition.searchType}");
	$("[name=searchType]").val("${searchvo.searchType}");
// 	$("[name=searchWord]").val("${pagingVO.simpleCondition.searchWord}");
	$("[name=searchWord]").val("${searchvo.searchWord}");
	
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