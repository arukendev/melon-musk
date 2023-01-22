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
	<div class="list_wrapper">
		<div class="list_titles">
			<div class="list_title">
				<h1>국내 최신곡</h1>
			</div>
			<div class="list_subtitle">
				<h6>Powered by</h6>
				<img src="files/main/melon.png">
			</div>
		</div>
		<div class="list_container">
		<c:forEach var="n" items="${newMusic}">
			<div class="list_boxs" onclick="location.href='MusicC?musicId=${n.musicId}'">
					<div class="list_boxs_num">
						<span>${n.rank}</span>
					</div>
					<div class="list_buttons">
						<div class="list_music_like">
							<form action="LoginC">
								<button>
									<i class="far fa-heart"></i>
								</button>
							</form>
						</div>
						<div class="list_music_add">
							<form action="AddPlChartMusicC">
								<input hidden name="musicId" value="${n.musicId}">
								<button>
									<i class="fas fa-plus"></i>
								</button>
							</form>
						</div>
					</div>
					<div class="list_boxs_img">
						<a href="AlbumC?albumId=${n.albumId}">
							<img src="${n.img}">
						</a>
					</div>
					<div class="list_boxs_info">
						<a href="MusicC?musicId=${n.musicId}">${n.music}</a>
						<span>${n.artist}</span>
					</div>
				</div>
		</c:forEach>
		</div>
	</div>
</body>
</html>