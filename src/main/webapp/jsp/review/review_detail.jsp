<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/review/review.js"></script>
</head>
<body>
<div id="reviewReg_table">
	<div class="review_table">
		<div class="review_table_title">상&nbsp&nbsp&nbsp&nbsp&nbsp세&nbsp&nbsp&nbsp&nbsp&nbsp보&nbsp&nbsp&nbsp&nbsp&nbsp기<br><span style="color:red">${alert }</span></div>
		<div class="review_table_btns"><a href="ReviewC">목록</a></div>
	</div>
	<div class="reviewReg_table_row">
		<div class="reviewReg_row_index">Title</div>
		<div style="width: 300px">${review.name }</div>
		<div id="reviewDetail_row_viewsLike">
			<div class="reviewDetail_viewsLike_items"><i class="fa-solid fa-eye"></i></div>
			<c:choose>
				<c:when test="${param.wr ne null }">
					<div class="reviewDetail_viewsLike_items">${review.view }</div>
				</c:when>
				<c:otherwise>
					<div class="reviewDetail_viewsLike_items">${review.view +1 }</div>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${(sessionScope.account.au_id ne null) and (like.au_id ne null) and (sessionScope.account.au_id eq like.au_id)}">
	 				<div class="reviewDetail_viewsLike_items"><button id="reviewDetail_items_likeBtn" onclick="location.href='ReviewLikeCancelC?no=${review.id}&wr=${review.au_id }'"><i class="fas fa-heart"></i></button></div>
				</c:when>
				<c:otherwise>
				<div class="reviewDetail_viewsLike_items"><button id="reviewDetail_items_likeBtn" onclick="like(${review.id}, '${review.au_id }', '${a.au_id }')"><i class="far fa-heart"></i></button></div>
				</c:otherwise>
			</c:choose>
			<div class="reviewDetail_viewsLike_items" id="likeNumber">${review.like }</div>
		</div>
	</div>
	<div class="reviewReg_table_row">
		<div class="reviewReg_row_index">Writer</div>
		<div class="reviewDetail_row_200">
		${review.au_id }
		</div>
		<div class="reviewReg_row_index">Date</div>
		<div class="reviewDetail_row_100">
		<fmt:formatDate value="${review.date }" type="both" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>
		</div>
	</div>
	<div class="reviewReg_table_row">
		<div class="reviewReg_row_index">Photo</div>
		<div class="reviewDetail_row_400"><img src="${review.img }" id="reviewDetail_row_img"><a href="${review.img }" target="blank"><i class="fa-solid fa-magnifying-glass-plus"></i></a></div>
	</div>
	<div class="reviewReg_table_row">
		<div class="reviewReg_row_index">Content</div>
		<div class="reviewReg_row_txt">${review.text }</div>
	</div>
	<div id="reviewDetail_bttmBtns">
		<button onclick="update(${review.id}, '${review.au_id }', '${a.au_id }')">수정하기</button>
		<button onclick="deleteReview(${review.id}, '${review.au_id }', '${a.au_id }')">삭제하기</button>
	</div>
</div>
	<div id="reviewDetail_comment">
		<form action="ReviewCommentC" >
			<span>${r }</span>
			<textarea name="contents"></textarea>
			<button>전송</button>
		</form>
	</div>
<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>