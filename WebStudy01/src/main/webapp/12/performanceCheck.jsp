<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>성능 고려사항</h4>
<pre>
	응답 소요 시간 : latency time(네트워크지연시간 99.9999%) + processing time()
	case 1 - 16ms; 커넥션을 생성하는 케이스
	case 2 - 1120ms / DBCP case 4 - 25ms
	case 3 - 31ms
	
	
	
	커넥션을 연결할 때가 가장 오랜 시간이 필요하다 - ex)칼국수 집 반죽 만드는 과정
	커넥션을 담아놓을 수 있는 객체가 필요한데 이것을 <-pull이라고 한다.
	<%
		long startTime = System.currentTimeMillis();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CODE, WRITER, CONTENT, \"DATE\" ");
		sql.append(" FROM TBL_MEMO ");
		for(int i=1; i<=100;i++){
			try (
				//커넥션 수립
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				
				ResultSet rs = pstmt.executeQuery();
				List<MemoVO> memoList = new ArrayList<>();
				
				while(rs.next()) {
					MemoVO memo = new MemoVO();
					memoList.add(memo);
					memo.setCode(rs.getInt("CODE"));
					memo.setWriter(rs.getString("writer"));
					memo.setContent(rs.getString("CONTENT"));
					memo.setDate(rs.getString("DATE"));
				}
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		long endTime = System.currentTimeMillis();
	%>
	소요시간 : <%=endTime-startTime %>ms
</pre>
</body>
</html>