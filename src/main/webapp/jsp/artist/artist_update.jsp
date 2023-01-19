<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form class="detail_form" action="ArtistUpdateC" method="post" enctype="multipart/form-data">
	<div class="detail_header">
		<div class="detail_top">
			<c:if test="${sessionScope.account.au_id ne null}">
				<div class="detail_top_edit">
					<input hidden name="artistId" value="${artist.id}">
					<button>
						<i class="fas fa-check"></i>
						<span>편집완료</span>
					</button>
				</div>
			</c:if>
		</div>
		<div class="detail_titles">
			<div class="detail_titles_title">
				<h1>${artist.name}</h1>
			</div>
			<div class="detail_titles_subtitles">
				<c:forEach var="m" items="${members}">
					<c:if test="${m.name != 'none'}">
						<span>${m.name}</span>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="detail_header_img">
			<img src="${artist.img}">
			<input type="file" name="img">
		</div>
		<div class="detail_header_info">
			<div>
				<span>활동유형</span>
				<span>${artist.type}</span>
			</div>
			<c:choose>
				<c:when test="${artist.company eq 'none'}">
					<div>
						<span>소속사</span>
						<input name="company">
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<span>소속사</span>
						<input name="company" value="${artist.company}">
					</div>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${artist.debut eq 'none'}">
					<div>
						<span>데뷔</span>
						<input name="debut">
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<span>데뷔</span>
						<input name="debut" value="${artist.debut}">
					</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${artist.type ne '그룹'}">
				<c:choose>
					<c:when test="${artist.birth eq 'none'}">
						<div>
							<span>생일</span>
							<input type="date" name="birth">
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<input hidden class="birth_value" value="${artist.birth}">
							<span>생일</span>
							<input type="date" name="birth">
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</div>
	<div class="detail_main">
		<div class="detail_main_info">
			<h1>아티스트 정보</h1>
			<c:choose>
				<c:when test="${artist.info eq 'none'}">
					<textarea name="info"></textarea>
				</c:when>
				<c:otherwise>
					<textarea name="info">${artist.info}</textarea>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="js/artist/artistEdit.js"></script>
	<script type="text/javascript" src="js/main/detail.js"></script>
	<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>