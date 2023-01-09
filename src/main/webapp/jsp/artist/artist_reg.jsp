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
	<h1>${artist.name}</h1>
	<c:forEach var="m" items="${members}">
		<c:if test="${m.name != 'none'}">
			<a href="ArtistC?artistId=${m.id}">${m.name}</a>
		</c:if>
	</c:forEach>
	<img src="${artist.img}">
	<h1>활동유형 : ${artist.type}</h1>
	<c:if test="${artist.company != 'none'}">
		<h1>소속사 : ${artist.company}</h1>
	</c:if>
	<c:if test="${artist.debut != 'none'}">
		<h1>데뷔 : ${artist.debut}</h1>
	</c:if>
	<c:if test="${artist.birth != 'none'}">
		<h1>생일 : ${artist.birth}</h1>
	</c:if>
	<h1>아티스트 정보</h1>
	<c:choose>
		<c:when test="${artist.info eq 'none'}">
			<h1>등록된 가수 정보가 없습니다.</h1>
		</c:when>
		<c:otherwise>
			<h1>${artist.info}</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>