<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			
	<table id="loginAtreaTbl">
	<tr>
	<td>${r }</td>
	</tr>
	<tr>
	<td>${sessionScope.account.au_id }(${sessionScope.account.au_name })님 안녕하세요</td>
	</tr>
	<tr>
	<td ><button class="loginBtn my" onclick="location.href='MyPageC?id=${sessionScope.account.au_id }'">마이페이지</button>
	<button class="loginBtn out" onclick="location.href='LoginC'">로그아웃</button> </td>
	</tr>
	</table>
	
</body>
</html>