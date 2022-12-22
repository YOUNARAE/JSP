<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/elDesc.jsp</title>
</head>
<body>
<h4>EL(Expression Languqge)</h4>
<pre>
	: 표현(속성 데이터 출력)을 목적으로 하는 스크립트 형태의 언어
	<%
		String data = "데이터";
		pageContext.setAttribute("attr-Data", data);
	%>
	1. 속성( EL 변수 : Scope안에 저장되어있는 데이터) 접근 방법 , 
		<%=data %>, ${attr-Data }
<!-- el에서는 일반 변수는 사용할 수 없고 속성으로 사용하여야한다. 특정 scope 객체의 속성이 필요해진다
       한페이지 안에서만 가지고 놀꺼면 el이 필요가 없고 주로 모델2구조에서 많이 사용된다.  -->
    pageScope, requestScope, sessionScopel, applicationScope
       ${pageScope.attr-Data } , ${pageScope['attr-Data']}<!-- 마지막 방법이 가장 안전하고 빠른 구조이다 -->
       
	2. 연산자 종류 (el에서는 주인공이 연산자이다. 피연산자가 아니다.)
		산술연산자 : +=*/%
			${3+4 }, ${"3"+4 }, ${"3"+"4" }, ${attr+4 }, \${"a"+4 }
			${4/3 }, ${attr*data }
		연산의 결과가 데이터타입으로 따라간다.
		자바스크립트에서는 데이터 타입이 따로 없고 let으로만 실행하고, 타입은 실제 데이터가 사용될 때 결정하는 것과 비슷하다
		논리연산자 : &&(and, short-curit), ||(or, , short-curkit), !(not);
			${trun and true }, ${"true" and true}, ${true or "false" }, ${false or attr }, ${not attr } 
		비교연산자 : &gt;(gt), &lt;(lt), &gt;=(ge), &lt;=(le), ==(eq), !=(ne)
				${3 lt 4 }, ${4 ge 3 }, ${4 eq 3}, <!-- ${4 ne 3 }  --> 
		단항연산자 : empty , exist -> null -> length, size check
		<% 
			pageContext.setAttribute("attr", "  ");
			pageContext.setAttribute("listAttr", Arrays.asList("a", "b"));
			
		%>
			${empty attr } 
			list attr : ${not empty listAttr }
			값이 있는지 없는지, 유효한지 아닌지 체크할 때 주로 사용한다
		삼항연산자 : 조건식 ? 참표현:거짓표현
			${ not empty attr ? "attr비어있음" : attr }
			${listAttr },${not empty listAttr ? listAttr : "기본값" }
	3. (속성)객체에 대한 접근법
		<%
			MemoVO memo = new MemoVO();
			pageContext.setAttribute("memoAttr", memo);
			memo.setWriter("작성자");
		%>
		${memoAttr }, ${memoAttr.writer }, ${memoAttr['writer'] }
	4. (속성)집합 객체에 대한 접근법 : <a href="elCollection.jsp">elCollection.jsp</a>
	5. EL 기본 객체 : <a href="elObject.jsp">elObject</a>
	
	
	
</pre>
</body>
</html>