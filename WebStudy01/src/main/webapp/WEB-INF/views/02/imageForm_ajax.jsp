<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jpeg-red, png-green, gif-blue  -->
<style type="text/css">
	.red{
		background-color:red;
	}
	.green{
		background-color:springgreen;
	}
	.blue{
		background-color:blue;
	}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/jquery-3.6.1.min.js"></script>
</head>
<body>
	<form name="imgForm" action='<%=request.getContextPath() %>/imageStreaming.do'>
	<select name='image'> 
	</select>
	<input type='submit' value='전송' />
	</form>
	<div id="imgArea">
	</div>
	
	<script type="text/javascript">
	 const DIVTAG = $("#imgArea");
	 const SELECTTAG = $("[name=image]").on("change",function(event){ // 자바로 치면 final. 상수로 만든다
		 let option = $(this).find("option:selected"); //옵션을 찾았음
		 let mime = option.attr("class");
		 let clzName = matchedClass(mime);
		 $(this).removeClass(colors);
		 $(this).addClass(clzName);
		 
		 let srcURL = document.imgForm.action;
		 let queryString = $(document.imgForm).serialize();
		 let src = "%U?%P".replace("%U",srcURL).replace("%P",queryString);
		 
		 let img = $("<img>").attr("src", src)
		 DIVTAG.html(img);
	 }); 
	 const changeCondition = {
		jpeg:"red"
		, png:"green"
		, gif:"blue"
	 }
	 const colors = [];
	 $.each(changeCondition, function(prop,propValue){
		 console.log(prop+", "+propValue)
		 colors.push(propValue);
	 });
	 
	 let matchedClass = function(mime){ // 자바스크립트에서는 함수도 객체다
		 let clzName = "";
		 for(let prop in changeCondition){
			let idx = mime.indexOf(prop);	
			if(idx>=0){
				clzName = changeCondition[prop];
				break;
			}
		 }
		 return clzName;
	 }
	 $.ajax({
	      dataType : "json",
	      success : function(resp) { //언마샬링이 끝나서 돌아온다
			console.log(resp.length);
	        let options = []; //비어있는 배열로 옵션을 만든다.옵션 텍스트 자체를 만드는 것이 아님
	      //컨트롤 쉬프트 C를 누르면 한줄 주석이 된다
	      	$.each(resp, function(index, file){
	      		let option = $("<option>")
	      					.addClass(file.mime)
	      					.html(file.name); // 옵션태그가 1개 만들어짐
	      		options.push(option);
	      	});
	      	SELECTTAG.append(options);
	      },
	      error : function(jqXHR, status, error) {
	         console.log(jqXHR);
	         console.log(status);
	         console.log(error);
	      }
	   });
	  /* $("select").on("change",function(){
		if($('select option:selected').attr('class')=="image/jpeg"){
			$(this).attr("class","image/jpeg red");
		} else if($('select option:selected').attr('class')=="image/png"){
			$(this).attr("class","image/png green");
		} else if($('select option:selected').attr('class')=="image/gif"){
			$(this).attr("class","image/gif blue");
		} else {
			
		}
	  }); */
	  
	  
	</script>
</body>
</html>