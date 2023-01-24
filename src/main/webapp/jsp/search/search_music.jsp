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
	<div class="search_music_container">
		<div>
			<div class="search_title">
				<div class="result_num">
					<h1>검색 결과 <span>${number}</span>건</h1>
				</div>
				<div class="result_melon">
					<h6>Powered by</h6>
					<img src="files/main/melon.png">
				</div>
			</div>
			<c:forEach var="sm" items="${serachMusics}">
				<div class="search_music" onclick="location.href='MusicC?musicId=${sm.musicId}'">
					<div class="search_music_num">
						<span>${sm.number}</span>
					</div>
					<c:choose>
						<c:when test="${sm.like ne 1}">
							<div class="music_like">
								<form action="MusicLikeAddC" method="post">
									<input hidden name="musicId" value="${sm.musicId}">
									<button>
										<i class="far fa-heart"></i>
									</button>
								</form>
							</div>
						</c:when>
						<c:otherwise>
							<div class="music_like">
								<form action="MusicLikeDelC" method="post">
									<input hidden name="musicId" value="${sm.musicId}">
									<button>
										<i class="fas fa-heart"></i>
									</button>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="music_add">
						<form action="AddPlChartMusicC">
							<input hidden name="musicId" value="${sm.musicId}">
							<button>
								<i class="fas fa-plus"></i>
							</button>
						</form>
					</div>
					<div class="search_music_info">
						<a href="MusicC?musicId=${sm.musicId}">${sm.musicName}</a>
						<span>${sm.artistName}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="search_paging">
			<c:choose>
				<c:when test="${curPageNo == 1}">
				</c:when>
				<c:otherwise>
					<a href="SearchC?sel=mu&result=${param.result}&page=${curPageNo - 1}&index=${500 * curPageNo - 999}">
						<i class="fas fa-chevron-left"></i>
					</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="in" items="${indexes}">
				<a class="indexes" href="SearchC?sel=mu&result=${param.result}&page=${curPageNo}&index=${in.value}">${in.number}</a>
			</c:forEach>
			<c:choose>
				<c:when test="${curPageNo == pageCount}">
				</c:when>
				<c:otherwise>
					<a href="SearchC?sel=mu&result=${param.result}&page=${curPageNo + 1}&index=${500 * curPageNo + 1}">
						<i class="fas fa-chevron-right"></i>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src="js/main/mainPaging.js"></script>
</body>
</html>