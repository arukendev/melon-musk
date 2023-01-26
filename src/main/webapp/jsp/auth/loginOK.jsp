<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="login_buttons">
		<button class="index_login my" onclick="location.href='MyPageC?id=${sessionScope.account.au_id }'">마이페이지</button>
		<button class="index_login out" onclick="location.href='LoginC'">로그아웃</button>
	</div>
</body>
</html>