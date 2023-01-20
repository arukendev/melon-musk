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
	<form class="detail_form" action="MusicUpdateC" method="post">
	<div class="detail_header">
		<div class="detail_top">
			<div class="detail_top_edit">
				<input hidden name="musicId" value="${music.id}">
				<button>
					<i class="fas fa-check"></i>
					<span>편집완료</span>
				</button>
			</div>
		</div>
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${music.name}</h1>
			</div>
			<div class="detail_titles_subtitles">
				<c:forEach var="ar" items="${artists}">
					<span>${ar.name}</span>
				</c:forEach>
			</div>
		</div>
		<div class="detail_header_img">
			<img src="${music.alImg}">
		</div>
		<div class="detail_header_info">
			<div>
				<span>앨범</span>
				<span>${music.alName}</span>
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
					<input name="link">
				</c:when>
				<c:otherwise>
					<input name="link" value="${music.link}">
				</c:otherwise>
			</c:choose>
		</div>
		<div class="detail_main_info">
			<h1>가사</h1>
			<c:choose>
				<c:when test="${music.lyrics eq 'none'}">
					<textarea name="lyrics">등록된 가사 정보가 없습니다.</textarea>
				</c:when>
				<c:otherwise>
					<textarea name="lyrics">${music.lyrics}</textarea>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="js/music/musicEdit.js"></script>
	<script type="text/javascript" src="js/main/detail.js"></script>
</body>
</html>