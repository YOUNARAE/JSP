<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/autoRequest.jsp</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.6.1.min.js"></script>
</head>
<body>
<!-- 문제 : radio버튼을 이용해서 파일 형식에 맞게 출력될 수 있도록하고,시작,중단버튼을 이용해서
          시간 제어가 가능해야한다. -->
<%--
	response.setIntHeader("Refresh", 1);
--%>
<%-- =new Date()--%>
<h4>현재 서버의 시간 : <span id="timeArea"> </span></h4>
<button class="controlBtn"  data-control-type="START">시작</button>
<button class="controlBtn"  data-control-type="STOP">멈춤</button>
<input type="radio" name="dataType" data-data-type="json" />JSON
<input type="radio" name="dataType" data-data-type="text" />PLAIN
<input type="radio" name="locale" value="<%=Locale.KOREAN.toLanguageTag()%>" checked />한국어
<input type="radio" name="locale" value="<%=Locale.ENGLISH.toLanguageTag()%>" />영어
<input type="radio" name="locale" value="<%=Locale.JAPANESE.toLanguageTag()%>" />일본어

<script type="text/javascript">
	// 먼저 jsp에서 UI 작업을 먼저 해준다
	let timeArea = $("#timeArea"); // span태그를 아이디를 이용해 timeArea에 제이쿼리 객체로 담는다 
	//1단계 : 없던 버튼이 생겼다. 버튼에 대한 이벤트를 처리해준다
	// 이벤트는 클릭이벤트다.
	let controlBtns = $(".controlBtn").on("click",function(){ //.controlBtn를 제이쿼리 객체로 만들어서 클릭이벤트를 열어준다
		//버튼이 2개이기 때문에 2개를 걸러주는 작업을 해야한다.
		//이 버튼을 눌렀을 때
		let controlType = $(this).data("controlType"); 
		//눌린 버튼을 제이쿼리 객체화를 시켜서 controlType에 담았다
		console.log(controlType);
		if(controlType=="START"){
			//버튼이 시작하면 시계가 작동해야된다
			let watch = setInterval(sendRequest,1000); //?이부분 와치를 넣은 이유를 모름
			timeArea.data("watch", watch); //타임 제이쿼리 객체에 data-watch-type을 넣는다
			//이 설정값은 setInterval을 담고 있다
		} else {
			//버튼이 중지일 때 시계가 멈춘다
			let watch = timeArea.data("watch");
			if(watch){						
				clearInterval(watch);
				timeArea.data("watch", null);
			}
		}
	});
	
	let sendRequest = function(){
		//2단계 : 라디오버튼이 선택되면 선택된 json이나 text dataType으로 떠야한다
		//필요한 준비물 
		let dataTypes = $('[name="dataType"]'); //여기에 2개 변수 가능
		let locales = $('[name="locale"]'); //여기에 3개 변수가능

		let sucesses = {
			json:function(resp){
				timeArea.html(resp.now);
			},
			test:function(plain){
				timeArea.html(plain);
			},
		}
		
		//네임이 같은 라디오 버튼 중에서 
		let dataType = dataTypes.filter(":checked").data('dataType');
		//하지만 처음에 시작할 때 아무것도 체크해주지 않은 상태였다.
		//체크된 것이 없을 때에는 json으로 우선 보여준다는 처리를 해줘야함
		
		console.log(dataType)
		//체크된 레디오 버튼에 대해서 선택한 값 data-type
		//3단계 : 라디오 버튼이 선택되면 선택된 언어로 파라미터를 받아와야한다.
		let locale = locales.filter(":checked").val();
		
		$.ajax({
			url : "${pageContext.request.contextPath}/05/getServerTimeCopy",
			dataType : dataType,
			success : sucesses[dataType],
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	}
	
</script>
</body>
</html>