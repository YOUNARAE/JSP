<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<select name="member">
	<option value>멤버선택</option>
</select>
<script>
	let memberSelect = $('[name="member"]').on("change",function(event){ //모든 핸들러에서는 발생한 이벤트를 값으로 받을 수 있다는 뜻
		let code = $(this).val(); //this는 셀렉트이고 이 안에서 val()을 잡아야 옵션까지 들어간다. 옵션을 선택해서 값을 꺼낼때는 셀렉트 태그에서 선택해서 꺼내야한다.
<%-- 	location.href= "<%=request.getContextPath() %>/bts/" + code; --%>
		$.ajax({
			url : "<%=request.getContextPath() %>/bts/" + code,
			dataType : "html",
			success : function(resp) {
				memberSelect.after(resp);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});
	$.ajax({
		url : "<%=request.getContextPath() %>/bts",
		dataType : "json",
		success : function(resp) {
			let options = [];
			$.each(resp.bts, function(code, values){//-<-스트링배열의 값 values:RM이나 진이 들어있음					
				let option = $("<option>").val(code) //키 값을 넣어줘야한다.
							 .text(values[0]);
				options.push(option);
			});
			memberSelect.append(options);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>