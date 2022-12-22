<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/pageContextDesc.jsp</title>
</head>
<body>
<h4>pageContext(pageContext)</h4>
<pre>
 : 하나의 JSP 페이지와 관련된 모든 정보(기본 객체)를 가진 객체 
 현재 jsp에 대한 상황판단에 대판 모든 것을 pageContext가 가지고 있다.
 -제일먼저 생성되는 객체
 -나머지 객체들을 초기화하는 객체
 
 	1. EL에서 주로 기본 객체를 확보할 때 사용. 
 	   ex) <%=request.getContextPath() %>, ${pageContext.request.contextPath }
 	       <%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %>
 	       ${pageContext.request.contextPath}
 	2. 에러 데이터 확보
 	3. 흐름제거(요청 분기) : forward/include
 	4. 영역 제어(******)
 	
</pre>

<h4>Scope</h4>
<pre>
	Servlet[JSP] Container : 서블릿객체나 JSP 객체의 모든 관리 권한을 가진 주체(IoC-Inversion of Control:제어의 역전구조,제어권이 프레임워크로 넘어감).
	
	Scope : 웹 어플리케이션에서 데이터를 공유하기 위해 사용되는 저장 공간(자료구조)(Map%lt;String Object&gt;). 
	Attribute : scope를 통해 공유되는 데이터(String name/Object value).
	
	: Scope라는 저장 공간을 소유한 기본 객체의 생명주기와 동일함. 사용할 일이 없어지면 바로 삭제하는 속성 : 플래시 어플래쉬
	page scope : pageContext의 소유 공간, 현재 페이지에서만.
	request scope : 해당 영역의 소유 요청 객체가 소멸될 때 함께 소멸됨.
	session scope : 해당 영역을 소유한 세션 객체와 생명주기 동일.
	application scope : ServletContext와 동일한 생명주기를 가짐.
	
	setAttribute(name, value), getAttribute(name), removeAttribute(name)
	json을 토큰으로 이중구조로 만들어서 받으면 세션 개념이 없어질 수도 있다.
	
	<%
		pageContext.setAttribute("pageAttr", "페이지속성");
		request.setAttribute("requestAttr", "요청 속성"); //리다이렉트경우에는 요청이 나가서응답이 오는 순간에 요청은 없어지기때문에 null로 뜨는 것이다
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "어플리케이션 속성");
		
		pageContext.setAttribute("sample", "페이지샘플");
		pageContext.setAttribute("sample", "요청샘플", PageContext.REQUEST_SCOPE);
// 		1.forwrad
// 		2.include
		String viewName = "/09/attrView.jsp";
// 		request.getRequestDispatcher(viewName).forward(request, response);
// 		request.getRequestDispatcher(viewName).include(request, response);
// 		3.redirect
		String location = request.getContextPath() + viewName;
// 		response.sendRedirect(location);
	%>
</pre>
<h4>공유된 속성 데이터들</h4>
<pre>
	
<%-- sample : ${sample }  --%>
	sample : ${requestScope.sample } 
	<%
	////el은 가장 사용 범위가 적은 애부터 출력한다
	%>
	page scope : <%=pageContext.getAttribute("pageAttr") %> , ${pageAttr }
	request scope : <%=request.getAttribute("requestAttr") %> ${requestAttr }
	session scope : <%=session.getAttribute("sessionAttr") %> ${sessionAttr }
	<%
		// 	필요가 없어지면 바로 지워야한다 flash attribute 방식
		session.removeAttribute("sessionAttr");
	%>
	application scope : <%=application.getAttribute("applicationAttr") %> ${applicationAttr }
</pre>


</body>
</html>