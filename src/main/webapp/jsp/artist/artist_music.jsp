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
		<c:forEach var="am" items="${artistMusic}">
			<div class="artist_popular">
				<span>${am.rank}</span>
				<a href="MusicC?musicId=${am.id}">${am.name}</a>
			</div>
		</c:forEach>
		<c:forEach var="in" items="${indexs}">
			<a href="ArtistMusicC?artistId=${param.artistId}&muIndex=${in.value}">${in.number}</a>
		</c:forEach>
	</div>
</body>
</html>