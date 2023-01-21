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
	<div class="search_album_container">
		<div class="result_num">
			<h1>검색 결과 <span>${number}</span>건</h1>
		</div>
		<div class="search_album_list">
			<c:forEach var="sals" items="${searchAlbums}">
				<div class="search_album_box">
					<div class="search_album_img">
						<a href="AlbumC?albumId=${sals.id}">
							<img src="${sals.img}">
						</a>
					</div>
					<div class="search_album_info">
						<span>
							${sals.type}
						</span>
						<a href="AlbumC?albumId=${sals.id}">
							${sals.name}
						</a>
						<span>
							${sals.artist}
						</span>
						<span>
							${sals.date}
						</span>
						<span>
							${sals.num}
						</span>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>