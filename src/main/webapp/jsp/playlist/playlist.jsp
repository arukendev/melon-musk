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
	<div class="pl_container">
		<div class="pl_titles">
			<div class="pl_title" >
				<h1>플레이리스트</h1>
			</div>
			<div class="pl_subtitle">
				<button onclick="location.href='RegPlaylistC'">
					<p>플리만들기</p>
					<i class="fas fa-plus"></i>
				</button>
			</div>
		</div>
		<div id="playlist_content">
			<div class="playlist_first">
				<div>이미지</div>
				<div>제목</div>
				<div>조회수</div>
				<div>등록일</div>
			</div>
			<c:forEach var="playlists" items="${playlists }">
				<div class="playlist_content_playlists" onclick="location.href='PlDetailC?pl_id=${playlists.pl_id}'">
					<div class="playlist_content_img" >
						<img src="files/playlist/${playlists.pl_img }">
					</div>
					<div class="playlist_content_playlistitem" >
						${playlists.pl_name }
					</div>
					<div class="playlist_content_playlistitem">
						<i class="fa-solid fa-eye"></i>${playlists.pl_view }
					</div>
					<div class="playlist_content_playlistitem">
						${playlists.pl_date }
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>