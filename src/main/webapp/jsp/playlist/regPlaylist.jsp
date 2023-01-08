<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/playlist/playlist.css">
</head>
<body>
	<div class="playlist_contanier">
		<div id="playlist_title">
			<div class="playlist_title_title">나만의 플레이리스트 만들기</div>
			<div class="playlist_title_title">
				<a href="RegPlaylistC">[미정]</a>
			</div>
		</div>
		<div id="playlist_content">
			<c:forEach var="mu" items="${musics }">
				<div class="playlist_content_playlists">
					<div class="playlist_content_playlistitem" >
						<a href="PlDetailC?pl_id=${mu.id}">
						 <img src="${mu.link }"> </a>
						</div>
					<div class="playlist_content_playlistitem">${mu.name} </div>
					<div class="playlist_content_playlistitem">${mu.arName }</div>
					<div class="playlist_content_playlistitem">${mu.lyrics }</div>
				</div>
			</c:forEach>
		</div>

		

		</div>
</body>
</html>