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
						<form action="MusicLikeAddC" method="post">
							<input hidden name="musicId" value="${music.id}">
							<button>
								<i class="far fa-heart"></i>
								<span>${likeCount}</span>
							</button>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="detail_top_like">
						<form action="MusicLikeDelC" method="post">
							<input hidden name="musicId" value="${music.id}">
							<button>
								<i class="fas fa-heart"></i>
								<span>${likeCount}</span>
							</button>
						</form>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="detail_top_add">
				<form action="AddPlChartMusicC">
					<input hidden name="musicId" value="${music.id}">
					<button>
						<i class="fas fa-plus"></i>
						<span>리스트에 추가</span>
					</button>
				</form>
			</div>
			<c:if test="${sessionScope.account.au_id ne null}">
				<div class="detail_top_edit">
					<form action="MusicUpdateC">
						<input hidden name="musicId" value="${music.id}">
						<button>
							<i class="fas fa-pencil-alt"></i>
							<span>편집하기</span>
						</button>
					</form>
				</div>
			</c:if>
		</div>
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${music.name}</h1>
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
			<img src="${music.alImg}">
		</div>
		<div class="detail_header_info">
			<div>
				<span>앨범</span>
				<span><a href="AlbumC?albumId=${music.alId}">${music.alName}</a></span>
			</div>
			<div>
				<span>발매일</span>
				<span>${music.date}</span>
			</div>
			<div>
				<span>장르</span>
				<span>${music.genre}</span>
			</div>
		</div>
	</div>
	<div class="detail_main">
		<div class="detail_main_link">
			<h1>유튜브 링크</h1>
			<c:choose>
				<c:when test="${music.link eq 'none'}">
					<p>등록된 링크가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<iframe width="560" height="315" src="https://www.youtube.com/embed/${music.link}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="detail_main_info">
			<h1>가사</h1>
			<c:choose>
				<c:when test="${music.lyrics eq 'none'}">
					<p>등록된 가사 정보가 없습니다.</p>
				</c:when>
				<c:otherwise>
					<p>${music.lyrics}</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="comment_container">
		<h1>댓글</h1>
		<div class="comment_input">
			<c:choose>
				<c:when test="${sessionScope.account.au_id ne null}">
					<form action="MusicCommentC">
						<input hidden name="musicId" value="${music.id}">
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
									<a href="MusicCommentC?musicId=${music.id}&commentId=${c.commentId}">
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
</body>
</html>