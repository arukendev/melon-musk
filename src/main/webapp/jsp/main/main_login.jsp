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
			<div class="home_pl_title">
				<h1>내 플레이리스트</h1>
			</div>
			<div class="home_pl_wrapper">
			<c:if test="${empty mpls}">
				<div class="home_pl_none">생성한 플레이 리스트가 없습니다</div>
			</c:if>
			<c:forEach var="mpl" items="${mpls}">
				<div class="home_pl_content">
					<a class="home_pl_img" href="PlDetailC?pl_id=${mpl.id}">
						<img src="files/playlist/${mpl.img}">
					</a>
					<a class="home_pl_txt" href="PlDetailC?pl_id=${mpl.id}">
						${mpl.name}
					</a>
				</div>
			</c:forEach>
			</div>
		</div>
		<div>
			<div>
				<h1>좋아요한 가수</h1>
			</div>
			<div>
			<c:if test="${empty marls}">
				<div>좋아요한 아티스트가 없습니다</div>
			</c:if>
			<c:forEach var="marl" items="${marls}">
				<div>
					${marl.id}
					${marl.name}
					<img src="${marl.img}">
				</div>
			</c:forEach>
			</div>
		</div>
		<div>
			<div>
				<h1>좋아요한 앨범</h1>
			</div>
			<div>
			<c:if test="${empty malls}">
				<div>좋아요한 앨범이 없습니다</div>
			</c:if>
			<c:forEach var="mall" items="${malls}">
				<div>
					${mall.id}
					${mall.name}
					<img src="${mall.img}">
				</div>
			</c:forEach>
			</div>
		</div>
		<div>
			<div>
				<h1>좋아요한 노래</h1>
			</div>
			<div>
			<c:if test="${empty mmls}">
				<div>좋아요한 노래가 없습니다</div>
			</c:if>
			<c:forEach var="mml" items="${mmls}">
				<div>
					${mml.id}
					${mml.name}
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>