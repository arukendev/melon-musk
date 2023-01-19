<%@page import="com.semi.auth.AuthDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- <form action="../../DeleteC" method="get"> -->
<div style="text-align: center"></div>
	<h3>* 비밀번호 확인 결과 *</h3>
<%
    //1) 사용가능한 아이디일 경우, 아이디 입력 폼에 넣기 위함
    String pw=request.getParameter("pw");
    out.println("입력 ID : <strong>" + pw + "</stong>");
    if(AuthDAO.duplecatePW(pw)==true){

    	out.println("<p>삭제하시겠습니까?.</p>");
	out.println("<a href='javascript:apply(\"" + pw + "\")'>[적용]</a>");

%>
	<script>
    	function apply(){
            //2) 중복확인 id를 부모창에 적용
            //부모창 opener
    		window.opener.location='../../DeleteC';
    		window.close();
        }//apply () end
    </script>
 <%
 	}else{
    	out.println("<p style='color: red'>비밀번호가 틀려요.</p>");
    }//if end
 %>
 <hr>
 <a href="javascript:history.back()">[다시시도]</a>
 &nbsp; &nbsp;
 <a href="javascript:window.close()">[창닫기]</a>
 
</body>
</html>