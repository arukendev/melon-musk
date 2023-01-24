<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="detail_main">
		<div class="artist_musics">
			<div class="artist_musics_title">
				<h1>인기 곡</h1>
				<a href="ArtistMusicC?artistId=${artist.id}&page=1&muIndex=1">더 보기</a>
			</div>
			<c:forEach var="am" items="${artistMusic}">
				<div class="artist_music" onclick="location.href='MusicC?musicId=${am.id}'">
					<div class="artist_music_num">
						<span>${am.rank}</span>
					</div>
					<c:choose>
						<c:when test="${am.like ne 1}">
							<div class="music_like">
								<form action="MusicLikeAddC" method="post">
									<input hidden name="musicId" value="${am.id}">
									<button>
										<i class="far fa-heart"></i>
									</button>
								</form>
							</div>
						</c:when>
						<c:otherwise>
							<div class="music_like">
								<form action="MusicLikeDelC" method="post">
									<input hidden name="musicId" value="${am.id}">
									<button>
										<i class="fas fa-heart"></i>
									</button>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="music_add">
						<form action="AddPlChartMusicC">
							<input hidden name="musicId" value="${am.id}">
							<button>
								<i class="fas fa-plus"></i>
							</button>
						</form>
					</div>
					<div class="artist_music_info">
						<a href="MusicC?musicId=${am.id}">${am.name}</a>
						<span>${am.artist}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="artist_albums">
			<div class="artist_album_title">
				<h1>최신 앨범</h1>
				<a href="ArtistAlbumC?artistId=${artist.id}&page=1&alIndex=1">더 보기</a>
			</div>
			<div class="artist_album">
				<c:forEach var="aa" items="${artistAlbum}">
					<div class="artist_album_info">
						<a href="AlbumC?albumId=${aa.id}">
							<img src="${aa.img}">
						</a>
						<span>${aa.type}</span>
						<a href="AlbumC?albumId=${aa.id}">
							${aa.name}
						</a>
						<span>${aa.date}</span>
						<span>${aa.num}</span>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="detail_main_info">
			<h1>아티스트 정보</h1>
			<c:choose>
				<c:when test="${artist.info eq 'none'}">
					<p>등록된 가수 정보가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<p>${artist.info}</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="comment_container">
		<h1>댓글</h1>
		<div class="comment_input">
			<c:choose>
				<c:when test="${sessionScope.account.au_id ne null}">
					<form action="ArtistCommentC">
						<input hidden name="artistId" value="${artist.id}">
						<textarea name="txt"></textarea>
						<button>등록</button>
					</form>
				</c:when>
				<c:otherwise>
					<p>로그인 이용자만 댓글을 달 수 있습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="comment_comments">
			<c:forEach var="c" items="${comments}">
				<div class="comment_comment">
					<div class="comment_profileimg">
						<img src="files/auth/${c.img}">
					</div>
					<div class="comment_contents">
						<div class="comment_top">
							<div class="comment_auth">
								${c.name}
							</div>
							<div class="comment_text">
								${c.txt}
							</div>
						</div>
						<div class="comment_bottom">
							<div class="comment_date">
								<fmt:formatDate value="${c.date}" pattern="yyyy.MM.dd kk:mm:ss"/>
							</div>
							<c:if test="${c.authId eq sessionScope.account.au_id}">
								<div>
									<a href="ArtistCommentC?artistId=${artist.id}&commentId=${c.commentId}">
										<i class="fas fa-trash-alt"></i>
									</a>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>