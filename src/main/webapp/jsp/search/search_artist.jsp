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
	<div class="search_artist_container">
		<div class="result_num">
			<h1>검색 결과 <span>${number}</span>건</h1>
		</div>
		<c:forEach var="sars" items="${searchArtists}">
			<div class="search_artist_box">
				<div class="search_artist_img">
					<a href="ArtistC?artistId=${sars.id}">
						<img src="${sars.img}">
					</a>
				</div>
				<div class="search_artist_info">
					<a href="ArtistC?artistId=${sars.id}">
						${sars.name}
					</a>
					<span>
						${sars.info}
					</span>
				</div>
			</div>
		</c:forEach>
		<div>
			<c:forEach var="in" items="${indexs}">
				<a href="SearchC?sel=ar&result=${param.result}&index=${in.value}">${in.number}</a>
			</c:forEach>
		</div>
	</div>
</body>
</html>