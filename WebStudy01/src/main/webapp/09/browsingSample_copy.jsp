<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/browsingSample_copy.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript">
	$.ajax({
		url : "<%=request.getContextPath() %>/browsing/getFileList",
		method : "get",
		data : {},
		dataType : "json",
		success : function(resp) {
			//여기에 li 가져오는 데이터를 넣어줘야된다.
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
</script>
<body>
<div class="row">
	<div class="col">
		<h4>src : /resources/images</h4>
		<ul id="srcUL" class="list-group">
			<li class="list-group-item">cat1.jpg</li>
		</ul>
	</div>
	<div class="">
		<button id="copyBtn" class="btn btn-primary">COPY</botton>
	</div>
	<div class="col">
		<h4>dest :  /destImgs</h4>
		<ul id="destUL" class="list-group">
		</ul>	
	</div>
</div>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>