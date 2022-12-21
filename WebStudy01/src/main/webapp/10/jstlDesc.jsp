<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jstlDesc.jsp
</title>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Libarar)</h4>
<pre>
	: 커스텀 태그들 중에서 많이 활용될 수 있는 것들을 모아놓은 라이브러리.
	&lt;prefix:tagname attr_name="attr_value" &gt;
	1. 커스텀 태그 로딩 : taglib directive
	2. prefix를 통한 태그 접근
	
	** core 태그 종류
	1. EL 변수(속성)와 관련된 태그 : set, remove
		<c:set var="sample" value="샘플값" scope="session"/> , 여기서 사용되는 var는 변수명이 아니다.
		${sample }, ${sessionScope.sample }
		<c:remove var="sample" scope="session" />
		${sample }
	2. 조건문 : if(조건식){블럭문}, swith~case(조건값)...~default
	     단일조건문 : if
	     <c:if test="${empty param.name1 }">
	     	파라미터 없음.
	     </c:if>
	     <c:if test="${not empty param.name1 }">
	     	파라미터 있음.
	     </c:if>
	     다중조건문 : choose ~ when ~ otherwise
	     <c:choose>
	     	<c:when test="${empty param.name1}">
	     		파라미터있음
	     	</c:when>
	     	<c:when test="${not empty param.name1}">
	     		파라미터djqtdma
	     	</c:when>
	     	<c:otherwise>
	     	파라미터 있음
	     	</c:otherwise>
	     </c:choose>
	     
	3. 반복문 : foreach, forTokens, for(선언절, 조건절, 증감절) for( : 반복할 대상 합계 객체)
	<c:set var="array" value='<%=new String[]{ "value1", "value2"} %>' />
	<c:forEach var="i" begin="0" end="${fn:length(array)-1}" step="1" varStatus="vs">
		${array[i] }--> 현재 반족의 상태(LoopTagStatus) : ${vs.index}, ${vs.count }, ${vs.first }, ${vs.last }
	</c:forEach>
	<c:forEach items="${array }" var="element">
		${element }
	</c:forEach>
	
	int num = 3;
	intnum=3;
	selectmem_idfrommember;
	아버지 가방에 들어가신다
	<c:forTokens items="1,2,3,4" delims="," var="token">
		${token }
	</c:forTokens>
	
	4. 기타
	   url 재작성 : url(client side path, session parameter), redirect
	   	<c:url value="/06/MemoView.jsp"/>
	   	쿠키가 없더라도 세션 파라미터를 자체적으로 만들어줄 수 있다.
	   	<a href="<c:url value='/10/jstlDesc.jsp'/>">세션유지</a>
<%-- 	   	<c:redirect url="/06/MemoView.jsp" /> --%> <!-- 주석이 이렇게 들어가면 서버사이트 코드라는 의미이다.-->
	   	<%--
	   		String location = request.getContextPath() + "/06/MemoView.jsp";
	   		response.sendRedirect(location);
	   	--%>
	    표현구조 : out
<%-- 	    	<jsp:include page=""></jsp:include> : 동일 컨텍스트 안에 있을 때만 끌고 올 수 있다. 동적인 페이지를 만들때 A+B --%>
	    	 : 동적맵퍼이지만 컨텍스트의 제한이 없다.
	    	<c:out value="<h4>출력값</h4>" escapeXml="false"/>
</pre>
<c:import url="https://www.naver.com" var="naver" />
<c:out value="${naver }" escapeXml="ture" />

</html>