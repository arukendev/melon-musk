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
	<div class="detail_main">
		<div class="artist_musics">
		<div class="artist_musics_title">
			<h1>인기 곡</h1>
			<a href="ArtistMusicC?artistId=${artist.id}&muIndex=1">더 보기</a>
		</div>
		<c:forEach var="am" items="${artistMusic}">
			<div class="artist_music">
				<span>${am.rank}</span>
				<a href="MusicC?musicId=${am.id}">${am.name}</a>
			</div>
		</c:forEach>
		<c:forEach var="in" items="${indexs}">
			<a href="ArtistMusicC?artistId=${param.artistId}&muIndex=${in.value}">${in.number}</a>
		</c:forEach>
		</div>
	</div>
</body>
</html>