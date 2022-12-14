<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="7kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.jsp</title>
</head>
<body>
<h4>response(HttpServletResponse)</h4>
<pre>
	Http의 response packaging
	1. Response Line : Status Code(응답상태코드, XXX)
	    100~ : ..ING... ->웹소켓 프로토콜과 관련
	    200~ : OK 
	    300~ : 최종 처리하기 위하 클라이언트의 추가 액션이 필요함. (response body가 없음.) 
	    		-개발자가 직접 셋팅하지 않는다. 
	            -개발자가 직접 사용하는 게 아니라 서버나 프레임워크에서 정해줌 
	            -300번대는 받았다면 뭔가 더 처리를 해주어야 없어진다는 의미이다, 응답을 받은 후에 사용자가 뭔가가 한가지 액션을 더 해야한다. ex)나래 범수 예시
	        304(cache data 관련) : Not modified 
	        301/302/307 : Moved + Location response header와 함께 사용.(redirect request)
	        <%--
//	        	request.getRequestDispatcher("/04/messageView.jsp").forward(request, response); // 서버 내에서 이동
//				절대경로, 서버사이드, 
 				String location = request.getContextPath()+"/04/messageView.jsp";  
 	        	response.sendRedirect(location); //서버 밖에서 이동해야하기때문에 클라이언트로부터 새로운 요청이 발생.
	        --%>
	    400~ : client side error -> Fail
			-클라이언트서버에서 문제가 생겼을 때, 아무나 클라이언트를 허용해서는 안된다(클라이언트가 다 선한 목적을 가지지는 않음)
	    	-서버사이드에서 문제가 생겼을 때 클라이언트쪽에 요청을 안 줘야한다.
	    	-구체적으로 상태코드가 여러가지로 쪼개서 사용한다
	    	400 : <%=HttpServletResponse.SC_BAD_REQUEST %>, 클라이언트 전송 데이터(파라미터) 검증 시 활용.
	    	401 / 403 : 인증(Authentication, 신원을 확인하는 것)과 인가(Authorization, 신원확인을 마친 사람에게 권한이 주어졌는지를 확인하는 과정) 기반의 접근 제어에 활용
	    	      <%=HttpServletResponse.SC_UNAUTHORIZED %>,<%=HttpServletResponse.SC_FORBIDDEN %>
	    	      
	    	404 : <%=HttpServletResponse.SC_NOT_FOUND %>
	    	405 : <%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %>, 현재 요청의 메소드에 대한 콜백 메소드가 재정의되지 않았을 때
	    	406/415 : contextn-type(MIME)과 관련된 상태코드
	    	<%=HttpServletResponse.SC_NOT_ACCEPTABLE %> : Accept request 헤어데 설정된 MIME 데이터를 만들어낼 수 없을 때.
	    		ex)accept:application/json(XXX)
	    	<%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %> : Content-Type request 헤더를 해석할 수 없을 때.
	    		ex)content-type:application/json --> unmarshalling(XXX)
	    	
	    500~ : server side error -> Fail, 500(Internal Server Error)
	    
	2. Response Header : meta data
	    Content(body)에 대한 부가정보 설정 : Content-*, Content-Type(MIME), Content-Length(size)
	     							Content-Disposition(content name, 첨부여부)
	     <%--
	     	response.setHeader("Content-Dispostion", "inline[attatchment];filename=\"파일명\"");
	     --%>							
	     			
		Cache control : 자원에 대한 캐싱 설정
		Auto Request : 주기적으로 갱신되는 자원에 대한 자동 요청
	 	Location 기반의 이동구조(Redirection).
	 	--3가지 모두 동기방식
	    
	3. Response Body(Content body, message body) :
<%--  response.getWriter(), response.getOutputStream() , <%= %>, out --%>
	
</pre>
</body>
</html>