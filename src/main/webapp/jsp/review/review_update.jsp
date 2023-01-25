<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/review/review.js"></script>
</head>
<body onload="calcChars2()">
<div class="review_list_title">
		<h1>리뷰수정</h1>
</div>
<div class="review_list_subtitle">
			<h6><a class="reviewReg_golist" href="ReviewC">목록</a></h6>
</div>
<form class="reviewReg_form" action="ReviewUpdateC?no=${review.id }" method="post">
	<div class="reviewReg_table">
		<div class="reviewReg_table_row">
			<input id="reviewReg_row_nameInput" value="${review.name }" name="name"/>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">사진 첨부</div>
			<div>
				<c:choose>
					<c:when test="${fn:contains(review.img, '*file^')  }">
						<input class="reviewReg_row_input" value="${fn:replace(review.img,'*file^','') }" name="img" type="file"/>
					</c:when>
					<c:otherwise>
						<input class="reviewReg_row_input" value="${review.img }" name="img" type="file"/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="reviewReg_table_row">
			<textarea id="reviewUpdate_row_textInput" name="text" maxlength="1000" onclick="calcChars2()" onkeydown="calcChars2()" onkeyup="calcChars2()" onkeypress="calcChars2()">${fn:replace(review.text,'<br>','
')}</textarea>
		</div>
		<div style="display:flex; width:85px; text-align:center;"><input style="width: 40px; text-align: right; border:none; background-color:var(--white)" type="number" id="chars" value="0" readonly><div>/1000</div></div>
		<button id="reviewReg_bottum_button">수정</button>
	</div>
</form>
</body>
</html>