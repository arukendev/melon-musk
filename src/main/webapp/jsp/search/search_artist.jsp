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
	<c:forEach var="sars" items="${searchArtists}">
		<div>
			<div>
				<img src="${sars.img}">
			</div>
			<div>
				<a href="ArtistC?artistId=${sars.id}">
					${sars.name}
				</a>
			</div>
			<div>
				<span>
					${sars.info}
				</span>
			</div>
		</div>
	</c:forEach>
</body>
</html>