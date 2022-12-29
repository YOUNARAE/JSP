<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	Connection conn=null;	//Connection 타입의 변수를 선언

	String driver="org.postgresql.Driver";
    //JDBC 드라이버 이름을 설정함. JDBC드라이버 이름은 사용하는 DBMS마다 다르며,
    //앞의 예제는 PostgreSQL을 기준으로 한 JDBC임.
    
	String url="jdbc:postgresql://localhost:5432/postgres";	
	String user = "postgres";
	String password = "python";
    //JDBC 드라이버를 사용하여 접속할 URL을 의마함. 이 코드는 localhost 주소의
    //5432 포트로 접속하며, SID명은 postgres를 사용함.
    //PostgreSQL을 설치하게 되면, 기본적으로 SID명이 postgres로 설정됨.
	
	Boolean connect=false;
	
	try{
		Class.forName(driver);	//JDBC 드라이버 이름으로 드라이버를 로드함.
		conn=DriverManager.getConnection(url,user,password);
        //설정했던 JDBC URL을 이용하여 Connection 객체를 얻어옴. (이 때는 DBMS
        //에 사용하는 아이디와 비밀번호를 알고있어야 함.)
		
		connect=true;
        //데이터베이스에 제대로 연결되어 Connection 객체가 생성되면 connect 변수
        //값을 true로 변경하여 PostgreSQL이 제대로 연동되었음을 표시함.
		
		conn.close();	//데이터베이스와의 연결을 해제함
	}catch(Exception e){
		connect=false;
		e.printStackTrace();
	}
    //데이터베이스와 연결할 때 Exception이 발생하면 Exception 내용을 출력함.
    //드라이버 이름이 틀리거나 주소와 포트명 또는 SID명이 제대로 입력되지 않으면
    //Exception이 발생함.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>
<%if(connect==true){ %>
	연결됨
<%}else{ %>
	연결안됨
<%} %>
</h3>
</body>
</html>