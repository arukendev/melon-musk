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
	<h1>${music.name}</h1>
	<c:forEach var="ar" items="${artists}">
		<h1><a href="ArtistC?artistId=${ar.id}">${ar.name}</a></h1>
	</c:forEach>
	<img src="${music.alImg}">
	<h1>앨범명</h1>
	<h1><a href="AlbumC?albumId=${music.alId}">${music.alName}</a></h1>
	<h1>발매일</h1>
	<h1>${music.date}</h1>
	<h1>장르</h1>
	<h1>${music.genre}</h1>
	<h1>가사</h1>
	<c:choose>
		<c:when test="${music.lyrics eq 'none'}">
			<h1>등록된 가사가 없습니다.</h1>
		</c:when>
		<c:otherwise>
			<h1>${music.lyrics}</h1>
		</c:otherwise>
	</c:choose>
	<h1>유튜브 링크</h1>
	<c:choose>
		<c:when test="${music.link eq 'none'}">
			<h1>등록된 링크가 없습니다.</h1>
		</c:when>
		<c:otherwise>
			<h1>${music.link}</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>