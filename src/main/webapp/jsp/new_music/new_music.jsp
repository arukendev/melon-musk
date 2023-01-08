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
	<c:forEach var="n" items="${newMusic}">
		<div>
			<div>
				<span>${n.rank}</span>
			</div>
			<div>
				<img src="${n.img}">
			</div>
			<div>
				<a href="MusicC?musicId=${n.musicId}">${n.music}</a>
				<a href="ArtistC?artistId=${n.artistId}">${n.artist}</a>
			</div>
			<div>
				<a href="AlbumC?albumId=${n.albumId}">${n.album}</a>
			</div>
		</div>
	</c:forEach>
</body>
</html>