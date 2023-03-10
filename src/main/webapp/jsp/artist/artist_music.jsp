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
				<div class="artist_music" onclick="location.href='MusicC?musicId=${am.id}'">
					<div class="artist_music_num">
						<span>${am.rank}</span>
					</div>
					<c:choose>
						<c:when test="${am.like ne 1}">
							<div class="music_like">
								<form action="MusicLikeAddC" method="post">
									<input hidden name="musicId" value="${am.id}">
									<button>
										<i class="far fa-heart"></i>
									</button>
								</form>
							</div>
						</c:when>
						<c:otherwise>
							<div class="music_like">
								<form action="MusicLikeDelC" method="post">
									<input hidden name="musicId" value="${am.id}">
									<button>
										<i class="fas fa-heart"></i>
									</button>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="music_add">
						<form action="AddPlChartMusicC">
							<input hidden name="musicId" value="${am.id}">
							<button>
								<i class="fas fa-plus"></i>
							</button>
						</form>
					</div>
					<div class="artist_music_info">
						<a href="MusicC?musicId=${am.id}">${am.name}</a>
						<span>${am.artist}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="search_paging">
			<c:choose>
				<c:when test="${curPageNo == 1}">
				</c:when>
				<c:otherwise>
					<a href="ArtistMusicC?artistId=${param.artistId}&page=${curPageNo - 1}&muIndex=${500 * curPageNo - 999}">
						<i class="fas fa-chevron-left"></i>
					</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="in" items="${indexes}">
				<a class="indexes" href="ArtistMusicC?artistId=${param.artistId}&page=${curPageNo}&muIndex=${in.value}">${in.number}</a>
			</c:forEach>
			<c:choose>
				<c:when test="${curPageNo == pageCount}">
				</c:when>
				<c:otherwise>
					<a href="ArtistMusicC?artistId=${param.artistId}&page=${curPageNo + 1}&muIndex=${500 * curPageNo + 1}">
						<i class="fas fa-chevron-right"></i>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src="js/main/mainPaging.js"></script>
</body>
</html>