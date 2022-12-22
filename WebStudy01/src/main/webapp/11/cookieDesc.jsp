<%@page import="java.net.URLEncoder"%>
<%@page import="kr.or.ddit.commons.wrapper.CookieHttpServletRequestWrapper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	session (server) vs cookie (client)
		: http의 stateless 특성으로 인해 커뮤니케이션 정보가 유지되지 않는 경우, 사용하는 저장소의 개념.
		
	사용 단계
	1. 쿠키 객체 생성(name, value)
	2. 응답(헤더, set-cookie)에 포함시켜 전송
	<%
		Cookie firstCookie = new Cookie("firstCookie", "firstValue");
		response.addCookie(firstCookie); //리스판스에 first쿠키라는 이름으로 저장이 하나 됨.
		//쿠키에 특스문자가 공백이 있으면 무조건 인코딩 해줘야한다.
		String koreanValue = URLEncoder.encode("한글값", "UTF-8");
		Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);
		response.addCookie(koreanCookie);
		
// 		Cookie domainCookie = new Cookie("domainCookie", "domain cookie value");
// 		domainCookie.setDomain("localhost");
// 		response.addCookie(domainCookie);
		Cookie otherPathCookie = new Cookie("otherPathCookie","otherPathCookievalue");
		otherPathCookie.setPath(request.getContextPath() + "/12");
		response.addCookie(otherPathCookie);
		
		Cookie longLiveCookie = new Cookie("longLiveCookie", "longLive");
// 		longLiveCookie.setPath(request.getContextPath()); 
		longLiveCookie.setPath("/");//콘텍스트루트 상관없이 로컬호스트면 다 사용할 수 있다는 주소의 의미 
		longLiveCookie.setMaxAge(60*60*24*7);
		//맥스에이지를 0으로 넣으면 이미 들어가있던 롱라이브쿠키가 삭제된다.
		response.addCookie(longLiveCookie);
	%>
	
	3. 브라우저가 자기 저장소에 저장. 
	4. 다음번 요청(헤더, cookie)을 통해 재전송
	
	5. 요청에 포함된 쿠키를 통해 상태를 복원.
	<a href="viewCookie.jsp">동일 경로에서 쿠키 확인</a>
	<a href="../12/viewCookie.jsp">다른 경로에서 쿠키 확인</a>
	<%--
// 		Cookie[] cookies = request.getCookies();
// 		Cookie finded = null;	
// 		if(cookies!=null){
// 			for(Cookie tmp : cookies){
// 				if("firstCookie".equals( tmp.getName() )){
// 					finded = tmp;
// 					break;
// 				}
// 			}
// 		} 이 코드 필요없어짐.
		String findedValue = new CookieHttpServletRequestWrapper(request).getCookieValue("koreanCookie");
		out.println("쿠키 값 : " + findedValue);			
	--%>
	
	** 쿠키 속성들
	필수 속성
		name : 식별자
		value : String, url encoded value
	부가 속성
		domain(host) : 다음번 요청에 포함시켜 재전송할지 여부를 결정하는 조건.
					ex) .naver.com(포스트 상위의 레벨 구조만 같으면 재전송하는 조건), www.naver.com, .com(x) 적어도 기관을 명시해주는 도메인까지는 조건으로 받아야한다.
		path : 다음번 요청에 포함시켜 재전송할지 여부를 결정하는 조건.
			path 설정이 명시되지 않은 경우, 쿠키 생성 경로가 반영됨. 
		maxAge : 쿠키의 만료 시한. 기본값 : 세션 만료 시한.
			ex) -1(브라우저가 종료가 되면 바로 삭제하라는 뜻임), 0(name, value.... 등 모든 속성이 동일한 쿠키인 경우 삭제).
		secure....
</pre>
</body>
</html>