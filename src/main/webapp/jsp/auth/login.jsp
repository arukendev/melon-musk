<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="login_form" action="LoginC" method="post" onsubmit="return call()">
		<div style="color:red">${alert}</div>
        <div class="profile_content">
          <label for="id">ID</label>
          <input type="text" id="id" name="id" placeholder="ID를 입력해주세요" />
        </div>
        <div class="profile_content">
          <label for="pw">Password</label>
          <input
            type="password"
            id="pw"
            name="pw"
            placeholder="패스워드를 입력해주세요"
          />
        </div>
        <div class="profile_buttons">
          <button class="profile_button">로그인</button>
          <button type="button" class="profile_button" onclick="location.href='AuthC'">회원가입</button>
        </div>
	</form>
	<script src="js/auth/acValueCheck.js"></script>
    <script src="js/auth/acValidCheck.js"></script>
</body>
</html>