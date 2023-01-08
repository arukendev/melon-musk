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
					<img src="${account.au_img}">
	  			</div>
	  		</div>
	  		<div class="profile_content">
	  			<div class="profile_title">Name</div>
	  			<div class="profile_result">${account.au_name}</div>
	  		</div>
	  		<div class="profile_content">
	  			<div class="profile_title">ID</div>
	  			<div class="profile_result">${account.au_id}</div>
	  		</div>
	  		
	  		<div class="profile_content">
	  			<div class="profile_title">Interest</div>
	  			<div class="profile_result">${account.au_interest}</div>
	  		</div>
	  		<div class="profile_content">
	  			<div class="profile_title">Introduce</div>
	  			<div class="profile_result">${account.au_introduce}</div>
	  		</div>
	  		<div>
	  			<button class="profile_button" onclick="location.href='UpdateC'">수정</button>
	  			<button class="profile_button" onclick="location.href='DeleteC'">삭제</button>
	  		</div>
  		</div>
    </div>
</body>
</html>