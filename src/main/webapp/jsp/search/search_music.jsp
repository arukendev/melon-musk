<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="sm" items="${serachMusics}">
		<div>
			<a href="MusicC?musicId=${sm.musicId}">${sm.musicName}</a>
		</div>
		<div>
			<a href="ArtistC?artistId=${sm.artistId}">${sm.artistName}</a>
		</div>
		<div>
			<a href="AlbumC?albumId=${sm.albumId}">${sm.albumName}</a>
		</div>
	</c:forEach>
</body>
</html>