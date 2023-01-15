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
			<div class="reviewDetail_viewsLike_items">${review.view }</div>
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
		<fmt:formatDate value="${review.date }" type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
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
	<div class="comment_container">
	<div id="reviewDetail_comment">
		<h1>댓글</h1>
		<div class="comment_input">
			<jsp:include page="${commentLoginCheck}"></jsp:include>
		</div>
		<div class="comment_comments">
			<c:forEach var="c" items="${comments}">
				<div class="comment_comment">
					<div class="comment_profileimg">
						<img src="${c.img}">
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
									<a href="javascript:commentDel(${review.id}, ${c.commentId})">
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
	</div>
<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>