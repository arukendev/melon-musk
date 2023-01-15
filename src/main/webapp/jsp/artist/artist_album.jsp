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
		<c:forEach var="aa" items="${artistAlbum}">
			<div>
				<a href="AlbumC?albumId=${aa.id}">
					<img src="${aa.img}">
				</a>
				<a href="AlbumC?albumId=${aa.id}">
					${aa.name}
				</a>
				<span>${aa.date}</span>
				<span>${aa.num}</span>
			</div>
		</c:forEach>
		<c:forEach var="in" items="${indexs}">
			<a href="ArtistAlbumC?artistId=${param.artistId}&alIndex=${in.value}">${in.number}</a>
		</c:forEach>
	</div>
</body>
</html>