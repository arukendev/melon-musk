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
					플레이리스트 : <c:choose>
									<c:when test="${playlistmusics[0].pl_name == null}">
										${playlist.pl_name} 
									</c:when>
									<c:otherwise>
										${playlistmusics[0].pl_name}
									</c:otherwise>
								</c:choose>
				</div>
			</div>
			<div class="pd_info">
				<input name="pl_id" value="${param.pl_id}" type="hidden">
				<div id="pd_info_img">
						<c:choose>
							<c:when test=" ${playlistmusics[0].pl_img == null} ">
								<img src="files/playlist/${playlist.pl_img} ">
							</c:when>
							<c:otherwise>
								<img src="files/playlist/${playlistmusics[0].pl_img}">
							</c:otherwise>
						</c:choose>
				</div>
				<div class="pd_info_items">
					<p>작성자 : 
						<c:choose>
							<c:when test="${playlistmusics[0].pl_au_id ==null}">
								${playlist.pl_au_id}
							</c:when>
							<c:otherwise>
								${playlistmusics[0].pl_au_id}
							</c:otherwise>
						</c:choose>
					</p>
					<p>등록일 : 
						<c:choose>
							<c:when test="${playlistmusics[0].pl_date ==null}">
								${playlist.pl_date}
							</c:when>
							<c:otherwise>
								 ${playlistmusics[0].pl_date}
							</c:otherwise>
						</c:choose>
					</p>
					<p>
						<c:choose>
							<c:when test="${playlistmusics[0].pl_text ==null}">
								${playlist.pl_text}
							</c:when>
							<c:otherwise>
								 ${playlistmusics[0].pl_text}
							</c:otherwise>
						</c:choose>
					</p>
				</div>
			</div>
			<div class="pd_music">
				<div class="pd_music_h1">노래 
					<div class="pd_music_button">
						<c:if test="${deleteOK}">
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
								<img src="${plms.al_img}">
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