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
<h4>Restful(request)가 구성되어있을 때 리퀘스트를 구성하고 있는 구조들을 모두 가능한 사용하자는 의미) 기반의 메모 관리</h4>
<form id="myForm" action="${pageContext.request.contextPath}/memo" method="post" onsubmit="return false;">
	<input type="text" name="writer" placeholder="작성자" value=""/>
	<input type="text" name="date" placeholder="작성일" value=""/>
	<textarea name="content"></textarea>
	<input type="submit" value="등록" />
</form>
<table class="table-bordered">
	<thead>
		<tr>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
</table>
<!-- <!-- Button trigger modal --> -->
<!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> -->
<!--   Launch demo modal -->
<!-- </button> -->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
//등록 버튼을 누르면 글이 등록되게 한다
//단 ajax를 이용해야함(비동기방식)
//submit버튼의 공식적인 기능은 없애줘야한다(동기라서)
//클릭하면 ajax를 이용하여 writer,content들의 내용들을 다 잡아서 보내야한다.
//input태그 date에는 현재 시간이 들어가야한다.
   $("#myForm").on("submit", function(event){
      event.preventDefault();
      console.log(this);
      console.log($(this));
      console.log($(this)[0][0].value);
      console.log($(this)[0][1].value);
      console.log($(this)[0][2].value);
      console.log($(this).serialize());

      $.ajax({
         url : "${pageContext.request.contextPath}/memo",
         method : "post",
         data : $(this).serialize(),
         dataType : "json",
         success : function(resp) {
        	 makeListBody(resp.memoList);
         },
         error : function(jqXHR, status, error) {
            console.log(jqXHR);
            console.log(status);
            console.log(error);
         }
      });
      return false;
   });
 

// 	EDD(Event Driven Development)
	$("#exampleModal").on("show.bs.modal",function(event){
// 		this==event.target(모달그자체/이벤트그자체)
		let memo = $(event.relatedTarget).data("memo"); // modal을 오픈할 때 사용한 클릭 대상, tr
		$(this).find(".modal-body").html(memo.content);
	}).on("hidden.bs.modal", function(event){
		$(event.target).find("modal-body").empty();
	});
	
    let listBody = $("#listBody");
	let makeListBody = function(memoList){
		listBody.empty();
		let trTags = [];
		if(makeListBody.length>0){
			//data-bs-toggle="modal" data-bs-target="#exampleModal"
			$.each(memoList, function(index, memo){ //인덱스와 한건의 메모를 값으로 받아온다
				let tr = $("<tr>").append(
					$("<td>").html(memo.writer) //메모라는 원형 데이터(매개변수에있는거)		
					, $("<td>").html(this.date)		
				).attr({
					"data-bs-toggle":"modal",
					"data-bs-target":"#exampleModal"
				}).data("memo",memo);
				trTags.push(tr);
			});
		} else {
			let tr = $("<tr>").html(
				$("<td>")
					.attr("colspan","2")
					.html("작성된 메모가 없습니다")
			);
			trTags.push(tr);
		}
		listBody.append(trTags);
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/memo",
		method : "get", //post -새로운 메모를 작성하겠다, put->메모를 수정하겠다, 
		dataType : "json",
		success : function(resp) {
			//json데이터로 제대로 넘어올 시에 보여줘야하는 것
			makeListBody(resp.memoList);
			//등록이 제대로 됐을 때 날짜를 넣어줘야함
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