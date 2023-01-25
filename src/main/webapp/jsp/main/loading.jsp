<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main/loading.css">
</head>
<body>
	<input hidden id="param" value="${parameter}">
	<div class="loading_box">
		<img src="files/main/loading.gif">
		<span>처리 중 입니다...</span>
	</div>
	<script type="text/javascript" src="js/main/loading.js"></script>
</body>
</html>