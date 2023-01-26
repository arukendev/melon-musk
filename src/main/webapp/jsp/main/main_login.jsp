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
	<c:forEach var="mpl" items="${mpls}">
		<div>
			${mpl.id}
			${mpl.name}
			${mpl.img}
		</div>
	</c:forEach>
	<c:forEach var="marl" items="${marls}">
		<div>
			${marl.id}
			${marl.name}
			<img src="${marl.img}">
		</div>
	</c:forEach>
	<c:forEach var="mall" items="${malls}">
		<div>
			${mall.id}
			${mall.name}
			<img src="${mall.img}">
		</div>
	</c:forEach>
	<c:forEach var="mml" items="${mmls}">
		<div>
			${mml.id}
			${mml.name}
		</div>
	</c:forEach>
</body>
</html>