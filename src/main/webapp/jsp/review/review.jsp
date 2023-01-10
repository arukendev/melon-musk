<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Semi - Reviews</title>
</head>
<body>
<div class="review_table">
	<div class="review_table_title">Reviews Page<span style="color: red">${result }</span></div>
	<div class="review_table_btns"><a href="ReviewRegC">Write</a></div>
</div>
<div id="review_columnsWrap">
<div id="review_columnsWrap_columns">
<div>No</div>
<div>Title</div>
<div>date</div>
<div>views</div>
</div>
</div>
<div id="review_table2">
<c:forEach var="review" items="${reviews }" varStatus="status">
	<div class="review_table2_row">
		<div style="width:100px; text-align: center;">${status.count }</div>
		<a href="ReviewDetailC?no=${review.id }"><div>${review.name }	[코멘트수??]</div></a>
		<div>${review.date }</div>
		<div style="width:50px; text-align: center">${review.view }</div>
	</div>
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