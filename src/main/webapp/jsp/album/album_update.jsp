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
	<form class="detail_form" action="AlbumUpdateC" method="post">
	<div class="detail_header">
		<div class="detail_top">
			<div class="detail_top_edit">
				<input hidden name="albumId" value="${album.id}">
				<button>
					<i class="fas fa-check"></i>
					<span>편집완료</span>
				</button>
			</div>
		</div>
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${album.name}</h1>
			</div>
			<div class="detail_titles_subtitles">
				<c:forEach var="ar" items="${artists}">
					<span>${ar.name}</span>
				</c:forEach>
			</div>
		</div>
		<div class="detail_header_img">
			<img src="${album.img}">
		</div>
		<div class="detail_header_info">
			<div>
				<span>앨범유형</span>
				<span>${album.type}</span>
			</div>
			<div>
				<span>발매일</span>
				<span>${album.date}</span>
			</div>
			<div>
				<span>장르</span>
				<span>${album.genre}</span>
			</div>
			<div>
				<span>발매사</span>
				<span>${album.publisher}</span>
			</div>
			<div>
				<span>기획사</span>
				<span>${album.agency}</span>
			</div>
		</div>
	</div>
	<div class="detail_main">
		<div class="detail_main_info">
			<h1>앨범 정보</h1>
			<c:choose>
				<c:when test="${album.info eq 'none'}">
					<textarea name="info">정보를 입력해주세요.</textarea>
				</c:when>
				<c:otherwise>
					<textarea name="info">${album.info}</textarea>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="js/main/detail.js"></script>
	<script type="text/javascript" src="js/album/albumEdit.js"></script>
	<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>