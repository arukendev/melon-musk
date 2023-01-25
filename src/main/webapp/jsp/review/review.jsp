<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Semi - Reviews</title>
<script type="text/javascript" src="js/review/review.js"></script>
</head>
<body>
<div class="list_title">
		<h1>리뷰게시판</h1>
</div>
<div class="list_subtitle">
	<c:choose>
		<c:when test="${sessionScope.account.au_id eq 'admin' }">
			<h6><a href="javascript:write('${sessionScope.account.au_id }')">공지작성</a></h6>
		</c:when>
		<c:otherwise>
			<h6><a class="review_write" href="javascript:write('${sessionScope.account.au_id }')">글쓰기</a></h6>
		</c:otherwise>
	</c:choose>
</div>
<div id="review_columnsWrap_label">
	<div class="review_label_no">글번호</div>
	<div class="review_label_title">제목</div>
	<div class="review_label_writer">작성자</div>
	<div class="review_label_date">작성일</div>
	<div class="review_label_viewLike"><i class="fa-solid fa-eye"></i></div>
	<div class="review_label_viewLike"><i class="fas fa-heart"></i></div>
</div>
<div id="review_table2">
<c:forEach var="notice" items="${notices }">
		<a href="ReviewDetailC?no=${notice.id }">
			<div class="review_table2_notice">
				<div class="review_label_no">공지</div>
				<div class="review_label_title">
					<div>${notice.name }</div>
					<%-- <div class="review_title_commentN">[${notice.comment }]</div> --%>
				</div>
				<div class="review_label_writer">${notice.au_id }</div>
				<div class="review_label_date">${notice.date }</div>
				<div class="review_label_viewLike">${notice.view }</div>
				<div class="review_label_viewLike">${notice.like }</div>
			</div>
		</a>
</c:forEach>
<c:forEach var="review" items="${reviews }" varStatus="status">
	<a href="ReviewDetailC?no=${review.id }">
		<div class="review_table2_row">
			<c:choose>
			<c:when test="${param.p eq null}">
				<div class="review_label_no">${status.count}</div>
			</c:when>
			<c:otherwise>
				<div class="review_label_no">${status.count + 10*(param.p-1) }</div>
			</c:otherwise>
			</c:choose>
			<div class="review_label_title">
				<div>${review.name }</div>
				<%-- <div class="review_title_commentN">[${review.comment }]</div> --%>
			</div>
			<div class="review_label_writer">${review.au_id }</div>
			<div class="review_label_date">${review.date }</div>
			<div class="review_label_viewLike">${review.view }</div>
			<div class="review_label_viewLike">${review.like }</div>
		</div>
	</a>
</c:forEach>

<div class="review_bottom">
	<div class="review_bottom_btn">
		<c:choose>
			<c:when test="${sessionScope.account.au_id eq 'admin' }">
				<a href="ReviewReportedC"><i class="fa-solid fa-circle-exclamation"></i>신고글</a >
			</c:when>
			<c:otherwise>
				<a href="ReviewBestC"><i class="fa-solid fa-crown"></i>추천글</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<a href="ReviewPageC?p=1">[맨 처음]</a>
		<c:forEach var="i" begin="1" end="${pageCount }">
			<a href="ReviewPageC?p=${i }">[${i }]</a>
		</c:forEach>
		<a href="ReviewPageC?p=${pageCount }">[맨 뒤]</a>
	</div>
	<div class="review_bottom_btn">
		<c:if test="${sessionScope.account.au_id ne null }">
			<a href='MyReviewsC'">내가 쓴 글</a>
		</c:if>
	</div>
</div>


</div>


</body>
</html>