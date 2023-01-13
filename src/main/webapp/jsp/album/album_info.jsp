<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="detail_header">
		<div class="detail_top">
			<div class="detail_top_like">
				<i class="fas fa-heart"></i>
				<span>9,999,999</span>
			</div>
			<div class="detail_top_edit">
				<i class="fas fa-pencil"></i>
				<span>편집하기</span>
			</div>
		</div>
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${album.name}</h1>
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
					<p>등록된 앨범 정보가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<p>${album.info}</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="comment_container">
		<h1>댓글</h1>
		<div class="comment_input">
			<jsp:include page="${commentLoginCheck}"></jsp:include>
		</div>
		<div class="comment_content">
			<c:forEach var="c" items="${comments}">
				<div class="comment_profileimg">
					${c.img}
				</div>
				<div class="comment_auth">
					${c.name}
				</div>
				<div class="comment_date">
					<fmt:formatDate value="${c.date}" type="both" dateStyle="short" timeStyle="short"/>
				</div>
				<div class="comment_text">
					${c.txt}
				</div>
				<c:if test="${c.authId eq sessionScope.account.au_id}">
					<div>
						<a href="AlbumCommentC?albumId=${album.id}&commentId=${c.commentId}">삭제</a>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript" src="js/main/detail.js"></script>
</body>
</html>