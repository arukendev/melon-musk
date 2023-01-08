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
	<c:forEach var="c" items="${charts}">
		<div>
			<div>
				<span>${c.rank}</span>
			</div>
			<div>
				<img src="${c.img}">
			</div>
			<div>
				<a href="MusicC?musicId=${c.musicId}">${c.music}</a>
				<a href="ArtistC?artistId=${c.artistId}">${c.artist}</a>
			</div>
			<div>
				<a href="AlbumC?albumId=${c.albumId}">${c.album}</a>
			</div>
		</div>
	</c:forEach>
</body>
</html>