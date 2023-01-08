<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="LoginC" method="post">
	
	
	<div id="">
        <div class="">
          <label for="id">ID</label>
          <input id="id" name="id" placeholder="ID를 입력해주세요" />
        </div>
        <div class="">
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
</body>
</html>