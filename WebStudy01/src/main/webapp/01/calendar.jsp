<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
   
   Calendar now = Calendar.getInstance();
   int year = now.get(Calendar.YEAR);   // 현재 연도 
   int month = now.get(Calendar.MONTH)+1; //1월은 0으로 시작해서 1을 더해줌 현재달 
   
   String str_year = request.getParameter("year");      // 파라미터에 연도 값과 
   String str_month = request.getParameter("month");   // 파리미터에 월 값 가져와 
   
   // 가져온 값이 있는 경우 
   if(str_year != null){
      year = Integer.parseInt(str_year);
   }
   if(str_month != null){
      month = Integer.parseInt(str_month);
   }
   now.set(year, month-1, 1); // 출력할 년도, 월로 설정
    
   // now.set(year, 10, 1); // 출력할 년도, 월로 설정
   
   year = now.get(Calendar.YEAR); //변화된 년도 값 가져오기
   month = now.get(Calendar.MONTH)+1; // 월 값 가져오기
   
   int endDay = now.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당월의 마지막 날짜
   int week = now.get(Calendar.DAY_OF_WEEK); //1~7(일월화수목금토)
   
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 만년달력</title>
<style type="text/css">
    *{padding: 0px; margin: 0px;}  /* 브라우저별 기본 여백 차이가 있기에 작성한다. */
    body{font-size: 9pt;}
    td{font-size: 9pt;}
    a{cusor: pointer; color: #000000; text-decoration: none; font-size: 9pt; line-height: 150%;}
    a:HOVER, a:ACTIVE{font-size: 9pt; color: #F28011; text-decoration: underline;}
</style>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
function sendIt(){
   var strYear = $("select[name='year']").val();
   var strMonth = $("select[name='month']").val();
  //alert("strYear==" + strYear + "=strMonth="+strMonth);
  
  console.log(strYear);
  console.log(strMonth);
   
   var url="calendar.jsp?year="+strYear+"&month="+strMonth;
   location.href=url; //url이 가지고 있는 값으로 이동
}

</script>
<body>
    <center>
       <%=year%>
       <%=month%>
       <%=endDay%>
       <%=week%>
        <table width="210" border="0" cellpadding="1" cellspacing="2">
            <tr height="30">
                <td align="center">
                    <form action ="" method="post">
                        <select name="year" onchange="sendIt()">
                        <%
                           for(int i=year-3;i<year+3;i++){      
                        %>
                           <option value="<%= i%>" <%=year==i?"selected='selected'":"" %>><%=i %>년</option>
                        <%
                           }
                        %>
                        </select>
                       
                        <select name="month" onchange="sendIt()">
                        <%
                           for(int i=1;i<=12;i++){      
                        %>
                           <option value="<%= i%>" <%=month==i?"selected='selected'":"" %>><%=i %>월</option>
                        <%
                           }
                        %>
                        </select>
                           
                    </form>
                </td>
            </tr>
        </table>
       
        <table width="210" border="0" cellpadding="2" cellspacing="1" bgcolor="#cccccc">
            <tr height="25">
                <td align="center" bgcolor="#e6e4e6"><font color="red">일</font></td>
                <td align="center" bgcolor="#e6e4e6">월</td>
                <td align="center" bgcolor="#e6e4e6">화</td>
                <td align="center" bgcolor="#e6e4e6">수</td>
                <td align="center" bgcolor="#e6e4e6">목</td>
                <td align="center" bgcolor="#e6e4e6">금</td>
                <td align="center" bgcolor="#e6e4e6"><font color="blue">토</font></td>
            </tr>
            <%
               int startDay = 0; // 1일이 어느요일 칸에서 시작하느냐에 따른 변수 먼저 선언
               out.println("<tr>");
               for(int i=1;i<week;i++){  // 공백일자 시작요일 1 일요일  
                  out.println("<td></td>");
                  startDay++;
               }
               
               String sun ="";
               String sat ="";
               for(int i=1; i<=endDay; i++){
                  out.println("<td>"+i+"</td>");
                  startDay++;
                  if(startDay==7&&i!=endDay){
                     out.println("</tr><tr>");
                     startDay=0;
                  }
               }
               
            
            %>
        </table>
    </center>
</body>
</html>

