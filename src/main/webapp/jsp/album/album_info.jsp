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
	<div class="detail_header">
		<div class="detail_top">
			<c:choose>
				<c:when test="${(sessionScope.account.au_id ne likeAuth) or (sessionScope.account.au_id eq null)}">
					<div class="detail_top_like">
						<form action="AlbumLikeAddC" method="post">
							<input hidden name="albumId" value="${album.id}">
							<button>
								<i class="far fa-heart"></i>
								<span>${likeCount}</span>
							</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="detail_top_like">
						<form action="AlbumLikeDelC" method="post">
							<input hidden name="albumId" value="${album.id}">
							<button>
								<i class="fas fa-heart"></i>
								<span>${likeCount}</span>
							</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="detail_top_edit">
				<form action="AlbumUpdateC">
					<input hidden name="albumId" value="${album.id}">
					<button>
						<i class="fas fa-pencil-alt"></i>
						<span>편집하기</span>
					</button>
				</form>
			</div>
		</div>
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${album.name}</h1>
			</div>
			<div class="detail_titles_subtitles">
				<c:forEach var="ar" items="${artists}">
					<c:choose>
						<c:when test="${ar.id eq '0'}">
							<span>${ar.name}</span>
						</c:when>
						<c:otherwise>
							<a href="ArtistC?artistId=${ar.id}">${ar.name}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<div class="detail_header_img">
			<img src="${album.img}">
		</div>
		<div class="detail_header_info">
			<div>
				<span>앨범유형</span>
				<span>${album.type}</span>
			</div>
			<div>
				<span>발매일</span>
				<span>${album.date}</span>
			</div>
			<div>
				<span>장르</span>
				<span>${album.genre}</span>
			</div>
			<div>
				<span>발매사</span>
				<span>${album.publisher}</span>
			</div>
			<div>
				<span>기획사</span>
				<span>${album.agency}</span>
			</div>
		</div>
	</div>
	<div class="detail_main">
		<div class="album_tracks">
		<h1>트랙 리스트</h1>
			<c:choose>
				<c:when test="${cdIndex.size() == 0}">
					<c:forEach var="am" items="${albumMusics}">
						<div class="album_track" onclick="location.href='MusicC?musicId=${am.id}'">
							<div class="album_track_num">
								<span>${am.num}</span>
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
							<div class="album_track_music">
								<a href="MusicC?musicId=${am.id}">
									${am.name}
								</a>
								<span>${am.artist}</span>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="ci" items="${cdIndex}">
						<div class="album_track_cdnum">
							<span>CD${ci}</span>
						</div>
						<c:forEach var="am" items="${albumMusics}">
							<c:if test="${ci == am.cd}">
								<div class="album_track" onclick="location.href='MusicC?musicId=${am.id}'">
									<div class="album_track_num">
										<span>${am.num}</span>
									</div>
									<c:choose>
										<c:when test="${sessionScope.account.au_id eq null}">
											<div class="music_like">
												<form action="LoginC">
													<button>
														<i class="far fa-heart"></i>
													</button>
												</form>
											</div>
										</c:when>
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
									<div class="album_track_music">
										<a href="MusicC?musicId=${am.id}">
											${am.name}
										</a>
										<span>${am.artist}</span>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="detail_main_info">
			<h1>앨범 정보</h1>
			<c:choose>
				<c:when test="${album.info eq 'none'}">
					<p>등록된 앨범 정보가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<p>${album.info}</p>
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
									<a href="AlbumCommentC?albumId=${album.id}&commentId=${c.commentId}">
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
	<script type="text/javascript" src="js/main/detail.js"></script>
	<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>