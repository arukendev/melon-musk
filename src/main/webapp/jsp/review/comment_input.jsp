<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ReviewCommentC">
		<input hidden name="no" value="${review.id}">
		<textarea name="txt"></textarea>
		<button>전송</button>
	</form>
</body>
</html>