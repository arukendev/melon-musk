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
		<c:forEach var="in" items="${indexs}">
			<a href="ArtistAlbumC?artistId=${param.artistId}&alIndex=${in.value}">${in.number}</a>
		</c:forEach>
	</div>
</body>
</html>