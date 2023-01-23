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
				<h1>TOP 100</h1>
			</div>
			<div class="list_subtitle">
				<h6>Powered by</h6>
				<img src="files/main/melon.png">
			</div>
		</div>
		<div class="list_container">
			<c:forEach var="c" items="${charts}">
				<div class="list_boxs" onclick="location.href='MusicC?musicId=${c.musicId}'">
					<div class="list_boxs_num">
						<span>${c.rank}</span>
					</div>
					<div class="list_buttons">
						<c:choose>
							<c:when test="${sessionScope.account.au_id eq null}">
								<div class="list_music_like">
									<form action="LoginC">
										<button>
											<i class="far fa-heart"></i>
										</button>
									</form>
								</div>
							</c:when>
							<c:when test="${c.like ne 1}">
								<div class="list_music_like">
									<form action="MusicLikeAddC" method="post">
										<input hidden name="musicId" value="${c.musicId}">
										<button>
											<i class="far fa-heart"></i>
										</button>
									</form>
								</div>
							</c:when>
							<c:otherwise>
								<div class="list_music_like">
									<form action="MusicLikeDelC" method="post">
										<input hidden name="musicId" value="${c.musicId}">
										<button>
											<i class="fas fa-heart"></i>
										</button>
									</form>
								</div>
							</c:otherwise>
						</c:choose>
						<div class="list_music_add">
							<form action="AddPlChartMusicC">
								<input hidden name="musicId" value="${c.musicId}">
								<button>
									<i class="fas fa-plus"></i>
								</button>
							</form>
						</div>
					</div>
					<div class="list_boxs_img">
						<a href="AlbumC?albumId=${c.albumId}">
							<img src="${c.img}">
						</a>
					</div>
					<div class="list_boxs_info">
						<a href="MusicC?musicId=${c.musicId}">${c.music}</a>
						<span>${c.artist}</span>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>