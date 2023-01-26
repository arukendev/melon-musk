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
	<form action="RegPlaylistC" method="post" enctype="multipart/form-data">
		<div class="playlist_contanier">
			<div id="playlist_title">
				<div class="playlist_title_title">나만의 플레이리스트 만들기</div>
				<div class="playlist_title_title">
					<button>[만들기]</button>
				</div>
			</div>
			<div class="playlist_content_playlists">
				<div class="playlist_content_playlistitem">
					플레이 리스트 이름 
				</div>
				<div class="playlist_content_playlistitem">
					<input name="pl_name" id="pl_name" type="text">
				</div>
			</div>
			<div class="playlist_content_playlists">
				<div class="playlist_content_playlistitem">
					플레이 리스트 소개글 
				</div>
				<div class="playlist_content_playlistitem">
					<textarea  name="pl_text" rows="5"></textarea>
				</div>
			</div>
			<div class="playlist_content_playlists">
				<div class="playlist_content_playlistitem">
					플레이 리스트 이미지
				</div>
				<div class="playlist_content_playlistitem">
					<input name="pl_img" id="pl_img" type="file">
				</div>
			</div>
		</div>
	</form>
</body>
</html>