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
	<div class="detail_header">
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${music.name}</h1>
			</div>
			<div class="detail_titles_subtitles">
				<c:forEach var="ar" items="${artists}">
					<c:choose>
						<c:when test="${ar.id eq '0'}">
							<span>${ar.name}</span>
						</c:when>
						<c:otherwise>
							<a href="ArtistC?artistId=${ar.id}">${ar.name}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<div class="detail_header_img">
			<img src="${music.alImg}">
		</div>
		<div class="detail_header_info">
			<div>
				<span>앨범</span>
				<span><a href="AlbumC?albumId=${music.alId}">${music.alName}</a></span>
			</div>
			<div>
				<span>발매일</span>
				<span>${music.date}</span>
			</div>
			<div>
				<span>장르</span>
				<span>${music.genre}</span>
			</div>
		</div>
	</div>
	<div class="detail_main">
		<div class="detail_main_link">
			<h1>유튜브 링크</h1>
			<c:choose>
				<c:when test="${music.link eq 'none'}">
					<span>등록된 링크가 없습니다.</span>
				</c:when>
				<c:otherwise>
					<iframe width="560" height="315" src="${music.link}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="detail_main_info">
			<h1>가사</h1>
			<c:choose>
				<c:when test="${music.lyrics eq 'none'}">
					<span>등록된 가사 정보가 없습니다.</span>
				</c:when>
				<c:otherwise>
					<span>${music.lyrics}</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
  	<div class="music_comment_container">
		<jsp:include page="${commentLoginCheck}"></jsp:include>
	</div>
	<script type="text/javascript" src="js/main/detail.js"></script>
</body>
</html>