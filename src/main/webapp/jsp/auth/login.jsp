<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="LoginC" method="post" onsubmit="return call()">
	
	
	<div id="login_form">
		<div style="color:red">${alert }</div>
        <div class="login_form">
          <label for="id">ID</label>
          <input id="id" name="id" placeholder="ID를 입력해주세요" />
        </div>
        <div class="login_form">
          <label for="pw">Password</label>
          <input
            type="password"
            id="pw"
            name="pw"
            placeholder="패스워드를 입력해주세요"
          />
        </div>
        <div class="login_button">
          <button>Log in</button>
        </div>
      </div>
	
	</form>
	<script src="js/auth/acValueCheck.js"></script>
    <script src="js/auth/acValidCheck.js"></script>
</body>
</html>