<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
  	<div id="profile_wrapper">
  		<div id="profile_box">
	  		
	  		<div class="profile_content">
	  			<div id="profile_img">
					<img src="files/auth/${sessionScope.account.au_img}">
	  			</div>
	  		</div>
	  		<div class="profile_content">
	  			<div class="profile_title">Name</div>
	  			<div class="profile_result">${sessionScope.account.au_name}</div>
	  		</div>
	  		<div class="profile_content">
	  			<div class="profile_title">ID</div>
	  			<div class="profile_result">${sessionScope.account.au_id}</div>
	  		</div>
	  		
	  		<div class="profile_content">
	  			<div class="profile_title">Interest</div>
	  			<div class="profile_result">${sessionScope.account.au_interest}</div>
	  		</div>
	  		<div class="profile_content">
	  			<div class="profile_title">Introduce</div>
	  			<div class="profile_result">${sessionScope.account.au_introduce}</div>
	  		</div>
	  		<div>
	  			<button class="profile_button" onclick="location.href='UpdateC'">수정</button>
	  			 <input type="button" value="ID중복확인" onclick="pwCheck()">
	  			<!--  <button class="profile_button" onclick="pwCheck()">삭제</button>-->
	  		</div>
  		</div>
    </div>
    <script src="js/auth/acValueCheck.js"></script>
    <script src="js/auth/acValidCheck.js"></script>
    <script
      src="https://kit.fontawesome.com/6478f529f2.js"
      crossorigin="anonymous"
    ></script>
</body>
</html>