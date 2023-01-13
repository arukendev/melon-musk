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
<div class="review_table">
	<div class="review_table_title">리&nbsp&nbsp&nbsp&nbsp&nbsp뷰&nbsp&nbsp&nbsp&nbsp&nbsp게&nbsp&nbsp&nbsp&nbsp&nbsp시&nbsp&nbsp&nbsp&nbsp&nbsp판<span style="color: red">${result }</span></div>
	<div class="review_table_btns"><a href="javascript:write('${sessionScope.account.au_id }')">글쓰기</a></div>
</div>
<div id="review_columnsWrap">
<div id="review_columnsWrap_label">
	<div class="review_label_no">글번호</div>
	<div class="review_label_title">제목</div>
	<div class="review_label_writer">작성자</div>
	<div class="review_label_date">작성일</div>
	<div class="review_label_viewLike"><i class="fa-solid fa-eye"></i></div>
	<div class="review_label_viewLike"><i class="fas fa-heart"></i></div>
</div>
</div>
<div id="review_table2">
<c:forEach var="review" items="${reviews }" varStatus="status">
	<a href="ReviewDetailC?no=${review.id }">
		<div class="review_table2_row">
			<div class="review_label_no">${status.count }</div>
			<div class="review_label_title">${review.name }	[코멘트수??]</div>
			<div class="review_label_writer">${review.au_id }</div>
			<div class="review_label_date">${review.date }</div>
			<div class="review_label_viewLike">${review.view }</div>
			<div class="review_label_viewLike">${review.like }</div>
		</div>
	</a>
</c:forEach>

<div class="review_pageNum">
	<a href="ReviewPageC?p=1">[맨 처음]</a>
	<c:forEach var="i" begin="1" end="${pageCount }">
		<a href="ReviewPageC?p=${i }">[${i }]</a>
	</c:forEach>
	<a href="ReviewPageC?p=${pageCount }">[맨 뒤]</a>
</div>


</div>


</body>
</html>