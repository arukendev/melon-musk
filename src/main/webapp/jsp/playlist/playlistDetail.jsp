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
			<div class="playlist_title_title">${playlistmusics[0].pl_name }님의 플레이리스트</div>
		</div>
		 <div id="playlist_content">
			<c:forEach var="plms" items="${playlistmusics }">
				<div class="playlist_content_playlists">
					<div class="playlist_content_playlistitem" >
						<a href="PlDetailC?pl_id=${plms.pl_id}">
						 <img src="${plms.al_img }"> </a>
						</div>
					<div class="playlist_content_playlistitem">${plms.mu_name} </div>
					<div class="playlist_content_playlistitem">${plms.ar_name }</div>
					<div class="playlist_content_playlistitem">${plms.mu_lyrics }</div>
				</div>
			</c:forEach>
		</div>

		


	</div>
</body>
</html>