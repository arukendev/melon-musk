<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="ReviewUpdateC?no=${review.id }" method="post">
<div id="reviewReg_table">
	<div class="review_table">
	<div class="review_table_title">리&nbsp&nbsp&nbsp&nbsp&nbsp뷰&nbsp&nbsp&nbsp&nbsp&nbsp수&nbsp&nbsp&nbsp&nbsp&nbsp정</div>
	<div class="review_table_btns"><a href="ReviewC">목록</a></div>
	</div>
	<div class="reviewReg_table_row">
	<div class="reviewReg_row_index">제목</div>
	<div><input class="reviewReg_row_input" value="${review.name }" name="name"/></div>
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
	<div class="reviewReg_row_index">내용</div>
	<div><textarea class="reviewReg_row_txt" name="text" maxlength="2000">${review.text }</textarea></div>
	</div>
	
	<button>수정 완료</button>
</div>
</form>
</body>
</html>