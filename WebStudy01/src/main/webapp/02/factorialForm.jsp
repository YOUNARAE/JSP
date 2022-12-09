<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
// 	$(document).ready(function(){});
// 	$(document).on("ready", function(){});
	$(function(){
		let resultArea = $("#resultArea");
		$("form[name]").on("submit", function(event){
			event.preventDefault();
			console.log($(this));
			console.log(this);
			console.log($(this)[0]);
			console.log($(this).get(0));
			let url = this.action;
			let method = this.method;
			let data = $(this).serialize();
			console.log(data);
			$.ajax({
				url : url,
				method : method,
				data : data,
				dataType : "html",
				success : function(resp) {
					resultArea.html(resp);
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
			return false;
		});
	});
</script>
</head>
<body>
<form name="facForm" action="<%=request.getContextPath() %>/02/factorial.do">
	<input type="number" name="number" />
	<input type="submit" value="전송" />
	<input type="reset" value="취소" />
	<input type="button" value="걍버튼" />
</form>
<div id="resultArea">

</div>
</body>
</html>













