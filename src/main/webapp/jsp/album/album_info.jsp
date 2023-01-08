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
	<h1>${album.type}</h1>
	<h1>${album.name}</h1>
	<img name="img" src="${album.img}">
	<h1>
		<c:forEach var="ar" items="${artists}">
			<a href="ArtistC?artistId=${ar.id}">${ar.name}</a>
		</c:forEach>
	</h1>
	<h1>발매일 : ${album.date}</h1>
	<h1>장르 : ${album.genre}</h1>
	<h1>발매사 : ${album.publisher}</h1>
	<h1>기획사 : ${album.agency}</h1>
	<h1>앨범 정보</h1>
	<h1>${album.info}</h1>
</body>
</html>