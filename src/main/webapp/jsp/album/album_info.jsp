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
				<h1>${album.type}</h1>
				<h1>${album.name}</h1>
			</div>
			<div class="artist_members">
				<h1>
					<c:forEach var="ar" items="${artists}">
						<a href="ArtistC?artistId=${ar.id}">${ar.name}</a>
					</c:forEach>
				</h1>
			</div>
		</div>
		<div class="detail_header_img">
			<img src="${album.img}">
		</div>
		<div class="detail_header_info">
			<div>
				<span>발매일</span> <i class="fas fa-caret-right"></i> <span>${album.date}</span>
			</div>
			<c:if test="${artist.company != 'none'}">
				<div>
					<span>장르</span> <i class="fas fa-caret-right"></i> <span>${album.genre}</span>
				</div>
			</c:if>
			<c:if test="${artist.debut != 'none'}">
				<div>
					<span>발매사</span> <i class="fas fa-caret-right"></i> <span>${album.publisher}</span>
				</div>
			</c:if>
			<c:if test="${artist.birth != 'none'}">
				<div>
					<span>기획사</span> <i class="fas fa-caret-right"></i> <span>${album.agency}</span>
				</div>
			</c:if>
		</div>
	</div>
	<div class="detail_main">
		<div class="detail_main_info">
			<h1>앨범 정보</h1>
			<c:choose>
				<c:when test="${album.info eq 'none'}">
					<h1>등록된 앨범 정보가 없습니다.</h1>
				</c:when>
				<c:otherwise>
					<h1>${album.info}</h1>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript" src="js/main/detail.js"></script>
	<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>