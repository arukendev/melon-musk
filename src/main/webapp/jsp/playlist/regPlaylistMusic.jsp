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
	<form action="RegPlMusicC" method="post">
		<div class="playlist_contanier">
			<div id="playlist_title">
				<div class="playlist_title_title">플리에 노래추가하기</div>
				<div class="playlist_title_title">
					<button>[추가]</button>
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
					앨범이미지
				</div>
				<div class="playlist_content_playlistitem">
				노래제목
				</div>
				<div class="playlist_content_playlistitem">
				가수
				</div>
				<div class="playlist_content_playlistitem">
				가사
				</div>
				<div class="playlist_content_playlistitem">
				플레이리스트에 추가
				</div>
			</div>
			<div id="playlist_content">

				<c:forEach var="mu" items="${musics }">
					<div class="playlist_content_playlists">
						<div class="playlist_content_playlistitem">
							<a href="PlDetailC?pl_id=${mu.id}"> <img src="${mu.alImg }">
							</a>
						</div>
						<div class="playlist_content_playlistitem">${mu.name}</div>
						<div class="playlist_content_playlistitem">${mu.arName }</div>
						<div class="playlist_content_playlistitem">

							<div class="wrap">
								<a href="#pop_info_1" class="btn_open">가사 보기</a>


								<div id="pop_info_1" class="pop_wrap" style="display: none;">
									<div class="pop_inner">
										<p class="dsc">${mu.lyrics }</p>
										<button type="button" class="btn_close">닫기</button>
									</div>
								</div>
							</div>

							<script type="text/javascript">
								var target = document
										.querySelectorAll('.btn_open');
								var btnPopClose = document
										.querySelectorAll('.pop_wrap .btn_close');
								var targetID;

								// 팝업 열기
								for (var i = 0; i < target.length; i++) {
									target[i]
											.addEventListener(
													'click',
													function() {
														targetID = this
																.getAttribute('href');
														document
																.querySelector(targetID).style.display = 'block';
													});
								}

								// 팝업 닫기
								for (var j = 0; j < target.length; j++) {
									btnPopClose[j]
											.addEventListener(
													'click',
													function() {
														this.parentNode.parentNode.style.display = 'none';
													});
								}
							</script>



						</div>
						<div class="playlist_content_playlistitem">
							<label><input type="checkbox" name="mu_id" 
								value="${mu.id }">추가 </label>
						</div>


					</div>
				</c:forEach>
			</div>



		</div>
	</form>
</body>
</html>