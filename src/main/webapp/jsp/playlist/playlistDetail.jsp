<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/playlist/playlist.css">
<script type="text/javascript">
//heart 좋아요 클릭시! 하트 뿅
$(function(){
    var $likeBtn =$('.icon.heart');

        $likeBtn.click(function(){
        $likeBtn.toggleClass('active');

        if($likeBtn.hasClass('active')){          
           $(this).find('img').attr({
              'src': 'https://cdn-icons-png.flaticon.com/512/803/803087.png',
               alt:'찜하기 완료'
                });
          
          
         }else{
            $(this).find('i').removeClass('fas').addClass('far')
           $(this).find('img').attr({
              'src': 'https://cdn-icons-png.flaticon.com/512/812/812327.png',
              alt:"찜하기"
           })
         }
     })
});</script>
</head>
<body>

	<form action="AddPlMusicC"  >
	<div class="playlist_contanier">
		<div id="playlist_title">
			<div class="playlist_title_title">${playlistmusics[0].pl_name }님의 플레이리스트</div>
			<div class="right_area">
  <a href="javascript:void(0)" onclick='like()' class="icon heart">
     <img src="https://cdn-icons-png.flaticon.com/512/812/812327.png" alt="찜하기">
  </a>
</div>
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
				</div>
			</c:forEach>
			<div class="playlist_addMusic">
			<button value="${plms.pl_id}">음원추가하기</button>
			<button type="button" onclick="deleteplmusic(${param.pl_id})">음악삭제하기</button>
			<button type="button" onclick="deleteplaylist(${param.pl_id})">플리삭제하기</button>
		</div>
		</div>
	</div>
	
	
	
	</form>
	<script type="text/javascript" src="js/playlist/playlist.js"></script>
</body>
</html>