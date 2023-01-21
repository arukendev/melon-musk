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
		<div class="list_title" >
		<h1>나의 플레이리스트</h1>
		</div>
		<form action="AddPlMusic?pl_id=${playlists[0].pl_id}">
		<div class="list_subtitle">
		<h6>
		<button>
		플리에 추가하기
		</button>
		</h6>
		<img src="https://cdn-icons-png.flaticon.com/512/175/175655.png?w=826&t=st=1674040400~exp=1674041000~hmac=946371d3e4114268e432fdc8e78818c271f2ddb2c006aa9adc7ddd3a14a1cecc">
	</div>
		
	<div class="list_container">
		<div id="playlist_content">
			
			<c:forEach var="playlists" items="${playlists }">
			<label for="chartmusicId">
			<input type="checkbox" id="chartmusicId" name="musicId" value=${param.musicId } style="display: none"  >
				<div class="playlist_content_playlists">
					<div class="list_boxs_box" >
						<img style="width: 60px; height: 60px;"  src="files/playlist/${playlists.pl_img }">
						</div>
					<div class="playlist_content_playlistitem" >
						${playlists.pl_name }
						</div>
					<div class="playlist_content_playlistitem">${playlists.pl_view } </div>
					<div class="playlist_content_playlistitem">${playlists.pl_like }</div>
					<div class="playlist_content_playlistitem">${playlists.pl_date }</div>
				</div>
				</label>
			</c:forEach>
		</div>
<div>
		<a href="PlaylistPageC?p=1">[맨 처음]</a>
		<c:forEach var="i" begin="1" end="${pageCount }">
			<a href="PlaylistPageC?p=${i }">[${i }]</a>
		</c:forEach>
		<a href="PlaylistPageC?p=${pageCount } }">[맨 뒤]</a>
	</div>
		

	</div>
	
	</form>
</body>
</html>