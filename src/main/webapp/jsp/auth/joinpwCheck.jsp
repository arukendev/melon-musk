<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.semi.auth.AuthDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="text-align: center">
	<h3>* 삭제하시려면 비밀번호를 입력하세요 *</h3>
    <form method="post" action="deleteForm.jsp" onsubmit="return blankCheck(this)">
    비밀번호 : <input type="password" name="pw" maxlength="10" autofocus>
    <input type="submit" value="중복확인">
    </form>
    </div>
    
    <script>
    function blankCheck(f){
    	var pw=f.pw.value;
        pw=pw.trim();
        if(pw.length<3){
        	alert("비밀번호는 3자 이상 입력해주십시오.");
            return false;
        }//if end
        return true;
    }//blankCheck() end
    </script>
</body>
</html>