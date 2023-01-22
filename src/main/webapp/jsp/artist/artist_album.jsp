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
		<div class="artist_albums">
			<div class="artist_album_title">
				<h1>전체 앨범</h1>
			</div>
			<div class="artist_album">
				<c:forEach var="aa" items="${artistAlbum}">
					<div class="artist_album_info">
						<a href="AlbumC?albumId=${aa.id}">
							<img src="${aa.img}">
						</a>
						<span>${aa.type}</span>
						<a href="AlbumC?albumId=${aa.id}">
							${aa.name}
						</a>
						<span>${aa.date}</span>
						<span>${aa.num}</span>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="search_paging">
			<c:choose>
				<c:when test="${curPageNo == 1}">
				</c:when>
				<c:otherwise>
					<a href="ArtistAlbumC?artistId=${param.artistId}&alIndex=${500 * curPageNo - 999}&page=${curPageNo - 1}">
						<i class="fas fa-chevron-left"></i>
					</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="in" items="${indexes}">
				<a href="ArtistAlbumC?artistId=${param.artistId}&alIndex=${in.value}&page=${curPageNo}">${in.number}</a>
			</c:forEach>
			<c:choose>
				<c:when test="${curPageNo == pageCount}">
				</c:when>
				<c:otherwise>
					<a href="ArtistAlbumC?artistId=${param.artistId}&alIndex=${500 * curPageNo + 1}&page=${curPageNo + 1}">
						<i class="fas fa-chevron-right"></i>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>