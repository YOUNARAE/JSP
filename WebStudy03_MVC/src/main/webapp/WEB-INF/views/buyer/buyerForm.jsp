<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>거래처 폼</h4>
<form method="post">
	<table class="table table-bordered">
		<tr>
			<th>거래처코드</th>
			<td>${buyer.buyerId}
				<input class="form-control" type="hidden" readonly 
				name="buyerId" value="${buyer.buyerId}" />
				<span class="text-danger">${errors.buyerId}</span>
			</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>
				<input class="form-control" type="text" 
				name="buyerName" value="${buyer.buyerName}" />
				<span
				class="text-danger">${errors.buyerName}</span>
			</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>
				<select name="buyerLgu">
					<option value>분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option value="${lprod.lprodGu }" 
							${lprod.lprodGu eq buyer.buyerLgu ? "selected":"" }
						>${lprod.lprodNm }</option>
					</c:forEach>
				</select>
				<span
				class="text-danger">${errors.buyerLgu}</span>
			</td>
		</tr>
		<tr>
			<th>거래은행</th>
			<td>
				<input class="form-control" type="text" name="buyerBank"
				value="${buyer.buyerBank}" />
				<span class="text-danger">${errors.buyerBank}</span>
			</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td>
				<input class="form-control" type="text" name="buyerBankno"
				value="${buyer.buyerBankno}" />
				<span class="text-danger">${errors.buyerBankno}</span>
			</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>
				<input class="form-control" type="text" name="buyerBankname"
				value="${buyer.buyerBankname}" />
				<span class="text-danger">${errors.buyerBankname}</span>
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<input class="form-control" type="text" name="buyerZip"
				value="${buyer.buyerZip}" />
				<span class="text-danger">${errors.buyerZip}</span>
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>
				<input class="form-control" type="text" name="buyerAdd1"
				value="${buyer.buyerAdd1}" />
				<span class="text-danger">${errors.buyerAdd1}</span>
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>
				<input class="form-control" type="text" name="buyerAdd2"
				value="${buyer.buyerAdd2}" />
				<span class="text-danger">${errors.buyerAdd2}</span>
			</td>
		</tr>
		<tr>
			<th>전번</th>
			<td>
				<input class="form-control" type="text" 
				name="buyerComtel" value="${buyer.buyerComtel}" />
				<span
				class="text-danger">${errors.buyerComtel}</span>
			</td>
		</tr>
		<tr>
			<th>팩스</th>
			<td>
				<input class="form-control" type="text" 
				name="buyerFax" value="${buyer.buyerFax}" />
				<span
				class="text-danger">${errors.buyerFax}</span>
			</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>
				<input class="form-control" type="text" 
				name="buyerMail" value="${buyer.buyerMail}" />
				<span
				class="text-danger">${errors.buyerMail}</span>
			</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>
				<input class="form-control" type="text" name="buyerCharger"
				value="${buyer.buyerCharger}" />
				<span class="text-danger">${errors.buyerCharger}</span>
			</td>
		</tr>
		<tr>
			<td colspan="2"> 
				<input class="btn btn-success" type="submit" value="저장" />
				<input class="btn btn-danger" type="reset" value="취소" />
				<a class="btn btn-secondary" href="<c:url value='/buyer/buyerList.do'/>">거래처목록</a>
			</td>
		</tr>
	</table>
</form>	
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>



















