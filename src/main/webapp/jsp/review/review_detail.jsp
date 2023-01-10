<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<div class="review_table_title">Review Detail</div>
		<div class="review_table_btns"><a href="ReviewC">목록</a></div>
	</div>
	<div class="reviewReg_table_row">
		<div class="reviewReg_row_index">Title</div>
		<div class="reviewDetail_row_200">${review.name }</div>
		<div id="reviewDetail_row_viewsLike">
			<div class="reviewDetail_viewsLike_items"><i class="fa-solid fa-eye"></i></div>
			<div class="reviewDetail_viewsLike_items">${review.view +1 }</div>
			<div class="reviewDetail_viewsLike_items"><button id="reviewDetail_items_likeBtn" onclick="pushLike('${a.au_id}',${review.id })"><i class="fa-regular fa-thumbs-up"></i></button></div>
			<div class="reviewDetail_viewsLike_items">${review.like }</div>
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
		<button onclick="location.href='ReviewUpdateC?no=${review.id}'">수정하기</button>
		<button onclick="location.href='ReviewDelC?no=${review.id}'">삭제하기</button>
	</div>
	<div id="reviewDetail_comment">
	<form action="ReviewCommentC"  >
	<span>${r }</span>
		<textarea name="contents"></textarea>
		<button>전송</button>
		</form>
	</div>
</div>
<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>

</body>
</html>