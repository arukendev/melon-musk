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
<div class="list_title">
		<h1>리뷰작성</h1>
</div>
<div class="list_subtitle">
			<h6><a href="ReviewC">목록</a></h6>
</div>
<form action="ReviewRegC" method="post" enctype="multipart/form-data" onsubmit="return reviewCall()">
	<div id="reviewReg_table">
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">제목</div>
			<div><input id="nameInput" class="reviewReg_row_input" name="name"/></div>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">내용</div>
				<textarea id="textInput" class="reviewReg_row_txt" name="text" maxlength="1000" onkeydown="calcChars()" onkeyup="calcChars()" onkeypress="calcChars()"></textarea>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">사진 첨부</div>
			<div><input id="imgInput" class="reviewReg_row_input" name="img" type="file" accept="image/*"/></div>
		</div>
		<div style="width:inherit; display:flex; justify-content:flex-end; align-items:center;">
			<button>등록</button>
			<div style="display:flex; width:85px; text-align:center; margin-left:125px;"><input style="width: 40px; text-align: right; border:none; background-color:var(--white)" type="number" id="chars" value="0" readonly><div>/1000</div></div>
		</div>
	</div>
	
</form>
</body>
</html>