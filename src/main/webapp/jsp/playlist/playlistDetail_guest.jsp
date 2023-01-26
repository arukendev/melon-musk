<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/playlist/playlist.js"></script>
</head>
<body>
	<form action="AddPlMusicC" class="pd_form">
		<div class="pd_contanier">
			<div class="pd_title">
				<div class="pd_title_item">
					플레이리스트 : ${playlistmusics[0].pl_name}
				</div>
			</div>
			<div class="pd_info">
				<input name="pl_id" value="${param.pl_id}" type="hidden">
				<div id="pd_info_img">
					<img src="files/playlist/${playlistmusics[0].pl_img}">
				</div>
				<div class="pd_info_items">
					<p>작성자 : ${playlistmusics[0].pl_au_id}</p>
					<p>등록일 : ${playlistmusics[0].pl_date}</p>
					<p>${playlistmusics[0].pl_text}</p>
				</div>
			</div>
			<div class="pd_music">
				<div class="pd_music_h1">노래 
					<div class="pd_music_button">
						<c:if test="${deleteOK}">
							<button value="${plms.pl_id}">음원추가하기</button>
							<button type="button" onclick="deleteplmusic(${param.pl_id})">음악삭제하기</button>
							<button type="button" onclick="deleteplaylist(${param.pl_id})">플리삭제하기</button>
						</c:if>
					</div>
				</div>
				<div class="pd_music_title">
					<div class="pd_content_playlistitem1">이미지</div>
					<div class="pd_content_playlistitem1">곡</div>
					<div class="pd_content_playlistitem1">앨범</div>
					<div class="pd_content_playlistitem1">가수</div>
					<div class="pd_content_playlistitem1">더보기</div>
				</div>
				<c:forEach var="plms" items="${playlistmusics}">
					<div class="pd_content_playlists">
						<div class="list_boxs_box">
<<<<<<< HEAD
							
								<img src="${plms.al_img}">
							
=======
							<img src="${plms.al_img}">
>>>>>>> f454888acfde4adb3d00a4f882524ad791598a95
						</div>
						<div class="pd_content_playlistitem">${plms.mu_name }</div>
						<div class="pd_content_playlistitem">${plms.mu_al_name }</div>
						<div class="pd_content_playlistitem">${plms.ar_name }</div>
						<div class="pd_content_playlistitem" onclick="location.href='MusicC?musicId=${plms.mu_id}'">상세보기</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</form>
</body>
</html>