<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/elCollection.jsp</title>
</head>
<body>
<h4>EL에서 집합 객체 접근 방법</h4>
<%
	String[] array = new String[]{"value1", "value2"};
	List<String> list = Arrays.asList("value1","value2");
	Set<String> set = list.stream().collect(Collectors.toSet());
	
	Map<String, Object> map = new HashMap<>();
	map.put("key-1", "value1");
	map.put("key 2", "value2");
	
	pageContext.setAttribute("array", array);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("set", set);
	pageContext.setAttribute("map", map);
	
	pageContext.setAttribute("memo", null);
%>
<pre>
	el에서는 그냥 출력하는 게 목적이라서 오류를 잘 못잡아내고 그냥 화이트 스페이스로만 나타난다
	원래 el은 근본적으로 메소드를 호출할 수 없는 구조이다. 
	그래서 리스트를 가져오는 get을 쓸 수 없음
	리스트랑 배열이랑 둘 다 인덱스로 꺼내는 문법이다
	
	array : <%--  =array[3] --%>, ${array[3] }
	
	list : <%-- <%=list.get(3) %> --%>, ${list[3] } 
	ex) memo's writer : ${memo.writer }, ${memo.getWriter() }, \${memo['writer'] }
	
	set : <%=set %> ${set }
	map : <%=map.get("key-1") %>, ${map.get("key-1") }, ${map.key-1 }, ${map['key-1'] }
	<%=map.get("key 2") %>, ${map.get("key 2") }, \${map.key 2 }, ${map['key 2'] } 3번 형태가 가장 안전함
	ex) int i 2;
	
</pre>
</body>
</html>