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
	<div class="list_title">
		<h1>TOP 100</h1>
	</div>
	<div class="list_subtitle">
		<h6>Powered by</h6>
		<img src="https://cdnimg.melon.co.kr/resource/image/web/common/logo_melon142x99.png">
	</div>
	<div class="list_container">
		<c:forEach var="c" items="${charts}">
			<div class="list_boxs">
				<div class="list_boxs_box" onclick="location.href='MusicC?musicId=${c.musicId}'">
					<span>${c.rank}</span>
				</div>
				<div class="list_boxs_box" onclick="location.href='MusicC?musicId=${c.musicId}'">
					<img src="${c.img}">
				</div>
				<div class="list_boxs_box" onclick="location.href='MusicC?musicId=${c.musicId}'">
					<a href="MusicC?musicId=${c.musicId}">${c.music}</a>
				</div>
				<div class="list_boxs_box" onclick="location.href='ArtistC?artistId=${c.artistId}'">
					<a href="ArtistC?artistId=${c.artistId}">${c.artist}</a>
				</div>
				<div class="list_boxs_box" onclick="location.href='AlbumC?albumId=${c.albumId}'">
					<a href="AlbumC?albumId=${c.albumId}">${c.album}</a>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>