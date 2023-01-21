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
			</div>
			<c:forEach var="am" items="${artistMusic}">
				<div class="artist_music">
					<div class="artist_music_num">
						<span>${am.rank}</span>
					</div>
					<div class="artist_music_info">
						<a href="MusicC?musicId=${am.id}">${am.name}</a>
						<span>${am.artist}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div>
			<c:forEach var="in" items="${indexs}">
				<a href="ArtistMusicC?artistId=${param.artistId}&muIndex=${in.value}">${in.number}</a>
			</c:forEach>
		</div>
	</div>
</body>
</html>