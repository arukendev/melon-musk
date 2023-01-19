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

	<form action="AddPlMusicC"  >
	<div class="pd_contanier">
		<div class="pd_title">
		<div class="pd_title_item">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		플레이리스트 :  ${playlistmusics[0].pl_name}</div>
		</div>
		 <div class="pd_info">
			<input name="pl_id" value="${param.pl_id}" type="hidden" >
			<div id="pd_info_item1"> <img src="files/playlist/${playlistmusics[0].pl_img}"> </div>
			<div class="pd_info_items"> <p> 작성자&nbsp :&nbsp ${playlistmusics[0].pl_au_id}</p> 등록일&nbsp :&nbsp ${playlistmusics[0].pl_date}</div>
			<div id="pd_info_text"> ${playlistmusics[0].pl_text} </div>
		<div ></div>
			<!--  
			<div class="">
			<button value="${plms.pl_id}">음원추가하기</button>
			<button type="button" onclick="deleteplmusic(${param.pl_id})">음악삭제하기</button>
			<button type="button" onclick="deleteplaylist(${param.pl_id})">플리삭제하기</button>
			
			
			<c:forEach var="plms" items="${playlistmusics }">
				<div class="">
					<div class="" >
						<a href="PlDetailC?pl_id=${plms.pl_id}">
						 <img src="${plms.al_img }"> </a>
						</div>
					<div class="">${plms.mu_name} </div>
					<div class="">${plms.ar_name }</div>
					<div class="">가사 보기 미구현</div>
				</div>
			</c:forEach>
		</div>-->
		</div>
			<div class="pd_music">
		<div class="pd_music_title">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		노래
		</div>	
		<div class="pd_music_sub">
		<button value="${plms.pl_id}">음원추가하기</button>
			<button type="button" onclick="deleteplmusic(${param.pl_id})">음악삭제하기</button>
			<button type="button" onclick="deleteplaylist(${param.pl_id})">플리삭제하기</button>
		</div>
		<div class="pd_content_playlists1">
		<div class="pd_content_playlistitem1" >이미지</div>
		<div class="pd_content_playlistitem1" >곡</div>
		<div class="pd_content_playlistitem1" >앨범</div>
		<div class="pd_content_playlistitem1" >가수</div>
		<div class="pd_content_playlistitem1" >더보기</div>
		</div>	
		
		
			
		<c:forEach var="plms" items="${playlistmusics }">
		<div class="pd_content_playlists">
					<div class="list_boxs_box" >
						<img style="width: 60px; height: 60px;"  src="${plms.al_img }">
						</div>
					<div class="pd_content_playlistitem" >
						${plms.mu_name }
						</div>
					<div class="pd_content_playlistitem" >
						${plms.mu_al_name }
						</div>
					<div class="pd_content_playlistitem">${plms.ar_name } </div>
					<div class="pd_content_playlistitem">상세보기</div>
				</div>
		</c:forEach>
	</div>
	</form>
</body>
</html>