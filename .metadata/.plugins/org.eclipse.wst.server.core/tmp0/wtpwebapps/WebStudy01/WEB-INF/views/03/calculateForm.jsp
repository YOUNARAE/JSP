<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/jquery-3.6.1.min.js"></script>
</head>
<body>
<input type="radio" name="dataType" value="json"/>Json
<input type="radio" name="dataType" value="xml"/>XML
<form action="" method="post">
	<input type="number" name="leftOp" placeholder="좌측피연산자" />
	<select name="operator">
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp" placeholder="우측피연산자" />
	<button type="button" class="loadData">=</button>
</form>
<div id="resultArea">
	나래야 사랑한다~~
</div>
<!-- 총 몇개의 파라미터가 넘어가는 가 input 3개-->
<script type="text/javascript">
	console.log("== 테스트 시작  ==");
	let resultArea = $("#resultArea");
	let dataTypes=$("[name=dataType]");
	
	let makeTag = function(name,value){
		let p = $("<p>").append(
				$("<span>").html(name),
				$("<span>").html(value)
			);	
		return p;
	}
	let sucesses = {
		json:function(resp){
			console.log("== resp ==" + resp);
			let pTags = [];
			$.each(resp, function(name, value){
				pTags.push(makeTag(name, value));
			});
			resultArea.empty();
			resultArea.append(pTags);
		},		
		xml:function(domResp){
			console.log("== resp ==" + domResp);
			let root = $(domResp).find("HashMap");
			let trTags = [];
			root.children().each(function(idx,child){
				let name = child.tagName;
				let value = child.innerHTML;
				let tr = makeTag(name, value);
				trTags.push(tr);
			});
			resultArea.empty();
			resultArea.append(trTags);
		}
	}
	
	let btn = $(".loadData").on("click",function(){
		let dataType = dataTypes.filter(":checked").val();
		let leftOp=$("[name=leftOp]").val();
		let rightOp=$("[name=rightOp]").val();
		let operator=$("select[name='operator']").val();
		$.ajax({
			type: 'POST',
			data : {leftOp: leftOp
				, rightOp: rightOp
				, operator: operator
			},
			dataType : dataType,
			success : sucesses[dataType],
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});
</script>
</body>
</html>