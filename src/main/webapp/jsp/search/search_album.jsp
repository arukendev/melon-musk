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
	<c:forEach var="sals" items="${searchAlbums}">
		<div>
			<div>
				<img src="${sals.img}">
			</div>
			<div>
				<a href="AlbumC?albumId=${sals.id}">
					${sals.name}
				</a>
			</div>
			<div>
				<a href="ArtistC?artistId=${sals.artistId}">
					${sals.artistName}
				</a>
			</div>
			<div>
				<span>
					${sals.date}
				</span>
				<span>
					${sals.num}
				</span>
			</div>
		</div>
	</c:forEach>
</body>
</html>