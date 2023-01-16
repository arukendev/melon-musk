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
			<c:if test="${sessionScope.account.au_id ne null}">
				<div class="detail_top_edit">
					<i class="fas fa-pencil"></i>
					<span>편집하기</span>
				</div>
			</c:if>
		</div>
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${artist.name}</h1>
			</div>
			<div class="detail_titles_subtitles">
				<c:forEach var="m" items="${members}">
					<c:if test="${m.name != 'none'}">
						<a href="ArtistC?artistId=${m.id}">${m.name}</a>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="detail_header_img">
			<img src="${artist.img}">
		</div>
		<div class="detail_header_info">
			<div>
				<span>활동유형</span>
				<span>${artist.type}</span>
			</div>
			<c:if test="${artist.company != 'none'}">
				<div>
					<span>소속사</span>
					<span>${artist.company}</span>
				</div>
			</c:if>
			<c:if test="${artist.debut != 'none'}">
				<div>
					<span>데뷔</span>
					<span>${artist.debut}</span>
				</div>
			</c:if>
			<c:if test="${artist.birth != 'none'}">
				<div>
					<span>생일</span>
					<span>${artist.birth}</span>
				</div>
			</c:if>
		</div>
	</div>
	<jsp:include page="${detailContentPage}"></jsp:include>
	<script type="text/javascript" src="js/main/detail.js"></script>
	<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>