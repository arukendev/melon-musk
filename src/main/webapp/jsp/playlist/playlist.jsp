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
			<div class="playlist_title_title">플레이리스트 페이지</div>
			<div class="playlist_title_title">
				<a href="RegPlaylistC">플리 만들기!</a>
			</div>
		</div>
		<div id="playlist_content">
			<c:forEach var="playlists" items="${playlists }">
				<a href="PlDetailC?pl_id=${playlists.pl_id}">
				<div class="playlist_content_playlists">
					<div class="playlist_content_playlistitem" >
						${playlists.pl_name }
						</div>
					<div class="playlist_content_playlistitem">${playlists.pl_view } </div>
					<div class="playlist_content_playlistitem">${playlists.pl_like }</div>
					<div class="playlist_content_playlistitem">${playlists.pl_date }</div>
				</div>
					</a>
			</c:forEach>
		</div>

		<a href="ReviewPageC?p=1">[맨처음]</a>
		<c:forEach var="i" begin="1" end="${pageCount }">
			<a href="ReviewPageC?p=${i}"> [${i}] </a>
		</c:forEach>
		<a href="ReviewPageC?p=${pageCount }">[맨끝]</a>


	</div>
</body>
</html>