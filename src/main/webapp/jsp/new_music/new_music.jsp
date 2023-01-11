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
		<h1>국내 최신곡</h1>
	</div>
	<div class="list_subtitle">
		<h6>Powered by</h6>
		<img src="https://cdnimg.melon.co.kr/resource/image/web/common/logo_melon142x99.png">
	</div>
	<div class="list_container">
	<c:forEach var="n" items="${newMusic}">
		<div class="list_boxs">
				<div class="list_boxs_box" onclick="location.href='MusicC?musicId=${n.musicId}'">
					<span>${n.rank}</span>
				</div>
				<div class="list_boxs_box" onclick="location.href='MusicC?musicId=${n.musicId}'">
					<img src="${n.img}">
				</div>
				<div class="list_boxs_box" onclick="location.href='MusicC?musicId=${n.musicId}'">
					<a href="MusicC?musicId=${n.musicId}">${n.music}</a>
				</div>
				<div class="list_boxs_box" onclick="location.href='ArtistC?artistId=${n.artistId}'">
					<a href="ArtistC?artistId=${n.artistId}">${n.artist}</a>
				</div>
				<div class="list_boxs_box" onclick="location.href='AlbumC?albumId=${n.albumId}'">
					<a href="AlbumC?albumId=${n.albumId}">${n.album}</a>
				</div>
			</div>
	</c:forEach>
	</div>
</body>
</html>