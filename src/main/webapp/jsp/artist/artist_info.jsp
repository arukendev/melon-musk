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
			<c:choose>
				<c:when test="${sessionScope.account.au_id eq null}">
					<div class="detail_top_like">
						<form action="LoginC">
						<button>
							<i class="far fa-heart"></i>
							<span>${likeCount}</span>
						</button>
						</form>
					</div>
				</c:when>
				<c:when test="${sessionScope.account.au_id ne likeAuth}">
					<div class="detail_top_like">
						<form action="ArtistLikeAddC" method="post">
							<input hidden name="artistId" value="${artist.id}">
							<button>
								<i class="far fa-heart"></i>
								<span>${likeCount}</span>
							</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="detail_top_like">
						<form action="ArtistLikeDelC" method="post">
							<input hidden name="artistId" value="${artist.id}">
							<button>
								<i class="fas fa-heart"></i>
								<span>${likeCount}</span>
							</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${sessionScope.account.au_id ne null}">
				<div class="detail_top_edit">
					<form action="">
						<button>
							<i class="fas fa-pencil-alt"></i>
							<span>편집하기</span>
						</button>
					</form>
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