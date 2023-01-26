<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="home_container">
		<div class="home_title">
			<h1>${sessionScope.account.au_name}(${sessionScope.account.au_id})님 반가워요</h1>
		</div>
		<div class="home_pl">
			<div class="home_1_title">
				<h1>내 플레이리스트</h1>
			</div>
			<div class="home_1_wrapper">
			<c:if test="${empty mpls}">
				<div class="home_1_none">생성한 플레이 리스트가 없습니다</div>
			</c:if>
			<c:forEach var="mpl" items="${mpls}">
				<div class="home_1_content">
					<a class="home_1_img" href="PlDetailC?pl_id=${mpl.id}">
						<img src="files/playlist/${mpl.img}">
					</a>
					<a class="home_1_txt" href="PlDetailC?pl_id=${mpl.id}">
						${mpl.name}
					</a>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="home_artist">
			<div class="home_1_title">
				<h1>좋아요한 가수</h1>
			</div>
			<div class="home_1_wrapper">
			<c:if test="${empty marls}">
				<div class="home_1_none">좋아요한 아티스트가 없습니다</div>
			</c:if>
			<c:forEach var="marl" items="${marls}">
				<div class="home_1_content">
					<a class="home_1_img" href="ArtistC?artistId=${marl.id}">
					<c:choose>
						<c:when test="${marl.img eq 'none'}">
							<img src="files/main/mark.png">
						</c:when>
						<c:otherwise>
							<img src="${marl.img}">
						</c:otherwise>
					</c:choose>
					</a>
					<a class="home_1_txt" href="ArtistC?artistId=${marl.id}">
						${marl.name}
					</a>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="home_album">
			<div class="home_1_title">
				<h1>좋아요한 앨범</h1>
			</div>
			<div class="home_1_wrapper">
			<c:if test="${empty malls}">
				<div class="home_1_none">좋아요한 앨범이 없습니다</div>
			</c:if>
			<c:forEach var="mall" items="${malls}">
				<div class="home_1_content">
					<a class="home_1_img" href="AlbumC?albumId=${mall.id}">
						<img src="${mall.img}">
					</a>
					<a class="home_1_txt" href="AlbumC?albumId=${mall.id}">
						${mall.name}
					</a>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="home_music">
			<div class="home_1_title">
				<h1>좋아요한 노래</h1>
			</div>
			<div class="home_2_wrapper">
			<c:if test="${empty mmls}">
				<div class="home_1_none">좋아요한 노래가 없습니다</div>
			</c:if>
			<c:forEach var="mml" items="${mmls}">
				<div class="home_2_content">
					<a class="home_2_img" href="MusicC?musicId=${mml.id}">
						<img src="${mml.img}">
					</a>
					<div class="home_2_txt">
						<a class="home_2_name" href="MusicC?musicId=${mml.id}">
							${mml.name}
						</a>
						<span class="home_2_artist">${mml.artist}</span>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>