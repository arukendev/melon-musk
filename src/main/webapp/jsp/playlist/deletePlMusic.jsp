<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/playlist/playlist.css">
<script type="text/javascript" src="js/playlist/playlist.js"></script>
</head>
<body>

	<form action="DeletePlMusicC" method="post" >
	<div class="playlist_contanier">
		<div id="playlist_title">
			<div class="playlist_title_title">${playlistmusics[0].pl_name }님의 음원 삭제하기</div>
			<button>삭제하기</button>
		</div>
		 <div id="playlist_content">
			<input name="pl_id" value="${param.pl_id}" type="hidden" >
			<c:forEach var="plms" items="${playlistmusics }">
				<div class="playlist_content_playlists">
					<div class="playlist_content_playlistitem" >
						<a href="PlDetailC?pl_id=${plms.pl_id}">
						 <img src="${plms.al_img }"> </a>
						</div>
					<div class="playlist_content_playlistitem">${plms.mu_name} </div>
					<div class="playlist_content_playlistitem">${plms.ar_name }</div>
					<div class="playlist_content_playlistitem">가사 보기 미구현</div>
					<div class="playlist_content_playlistitem">
							<label><input type="checkbox" name="mu_id" 
								value="${plms.mu_id }">추가 </label>
						</div>
				</div>
			</c:forEach>
			<div class="playlist_addMusic">
		</div>
		</div>
	</div>
	
	
	
	</form>
</body>
</html>