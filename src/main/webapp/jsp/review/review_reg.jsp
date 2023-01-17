<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/review/review.js"></script>
</head>
<body>
<form action="ReviewRegC" method="post" onsubmit="return call()">
	<div id="reviewReg_table">
		<div class="review_table">
			<div class="review_table_title">리&nbsp&nbsp&nbsp&nbsp&nbsp뷰&nbsp&nbsp&nbsp&nbsp&nbsp작&nbsp&nbsp&nbsp&nbsp&nbsp성</div>
			<div class="review_table_btns"><a href="ReviewC">목록</a></div>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">제목</div>
			<div><input id="nameInput" class="reviewReg_row_input" name="name"/></div>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">작성자</div>
			<div><div class="reviewReg_row_writer" name="auth">${a.au_id }</div></div>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">사진 첨부</div>
			<div><input id="imgInput" class="reviewReg_row_input" name="img"/></div>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">내용</div>
			<div><textarea id="textInput" class="reviewReg_row_txt" name="text" maxlength="1000"></textarea></div>
		</div>
		
		<button>등록</button>
	</div>
	
</form>
</body>
</html>