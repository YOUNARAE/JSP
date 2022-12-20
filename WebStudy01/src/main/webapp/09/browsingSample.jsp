<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/browsingSample.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
<script>
	//셀렉터로 골라놓은 제이쿼리 함수
	$.fn.generateFileList=function(){
		let target = this.data("target");
		let ulTag = this;
		$.ajax({
			url : "<%=request.getContextPath()%>/browsing/getFileList",
			data : {
				target : target
			},
			dataType : "json",
			success : function(resp) {
				let liTags = [];
				$.each(resp.files, function(index, wrapper){
					//files는 배열이 되었기 때문에 index를 받고 아규먼트 한개 한개를 가지고 와야함
					let li = $("<li>").addClass("list-group-item")
										.data("contentType",wrapper.contentType)
										.html(wrapper.name);
					liTags.push(li)
				});
				ulTag.html(liTags);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		return this;
	}
</script>
</head>
<body>
<div class="row">
	
	<div class="col">
		<h4>src : /resources/images</h4>
		<ul class="list-group" id="srcUL" data-target="/resources/images">
			<li data-content-type="image/jpeg" class="list-group-item active">cat1.jpg</li>
		</ul>
	</div>
	<div class="col">
		<!--  미션
		<input type="radio" name="command" value="COPY" checked />COPY
		<input type="radio" name="command" value="MOVE" />MOVE
		
		<button id="controlBtn" class="btn btn-primary">실행</button> -->
		<button id="copyBtn" class="btn btn-primary">COPY</button> 클릭하면 복사가 되어야하는데 비동기요청으로 넘어가야한다.
	</div>
	<div class="col">
		<h4>dest : /destImgs</h4>
		<ul class="list-group" id="destUL" data-target="/destImgs">
			
		</ul>
	</div>
</div>
<script type="text/javascript">
//1단계 li 태그에 들어가는 내용들을 넣을 수 있어야한다.
//액티브 클래스를 토글링 할 수 있어야하는 UI를 만드는 게 2단게
//3단계; 카피 btn을 눌렀을 떄 복사가 가능한 이벤트
//4단계: 소스파일과 destFile넘겨야함
//5단계 : 이 걸 넘겨서 복사해야함
//6단계 : 의사코드로 만들어서 작업해야한다
	let srcUL = $("#srcUL").generateFileList().on("click", "li", function(event){
// 		alert("1번방식");
		$(this).siblings("li").removeClass("active");
		$(this).addClass("active");
	});
	//이벤트 핸들러를 만들 때 동적인 구조로 만들때에는 반드시 1번 형식으로 이벤트 형식을 만들어야한다
	//2번은 li태그가 아직 생성이 안된 동적 구조이기때문에 선택할 수 있는 li가 없는 것
	$("#srcUL>li").on("click",function(){		
		alert("2번방식");
		
	});
	let destUL = $("#destUL").generateFileList();
	$(document).on("click", "#copyBtn", function(event){
		
		//선택한 파일이 있냐 없냐를 먼저 생각한다
		let fileName = srcUL.find("li.active").text();
		
		//아무것도 선택된 파일이 없다면
		if(!fileName) return false;
		let srcFile = srcUL.data("target") + "/" + fileName;
		let data = {
				srcFile : srcFile
				, destFolder : destUL.data("target")
				, command : "COPY"
			
			};
		$.post("<%=request.getContextPath()%>/browsing/fileManager", data, function(resp){
			if(resp.result)
				destUL.generateFileList();
			else {
					alert("파일관리 실패");
			}
		}, "json");
		
// 		$.ajax({ //위에 소스로 대처함
// 			url : ,
// 			method : "post",
// 			data : ,
// 			dataType : "json",
// 			success : function(resp) {
				
// 			},
// 			error : function(jqXHR, status, error) {
// 				console.log(jqXHR);
// 				console.log(status);
// 				console.log(error);
// 			}
// 		});

	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>