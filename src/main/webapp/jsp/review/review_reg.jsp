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
<div class="review_list_title">
		<h1>리뷰작성</h1>
</div>
<div class="review_list_subtitle">
			<h6><a class="reviewReg_golist" href="ReviewC">목록</a></h6>
</div>
<form class="reviewReg_form" action="ReviewRegC" method="post" enctype="multipart/form-data" onsubmit="return reviewCall()">
	<div class="reviewReg_table">
		<div class="reviewReg_table_row">
			<input id="reviewReg_row_nameInput" class="reviewReg_row_input" name="name" placeholder="제목을 입력하세요."/>
		</div>
		<div class="reviewReg_table_row">
			<div class="reviewReg_row_index">사진 첨부</div>
			<div><input id="reviewReg_row_imgInput" class="reviewReg_row_input" name="img" type="file" accept="image/*"/></div>
		</div>
		<div class="reviewReg_table_row">
			<textarea placeholder="1000자 이내" id="reviewReg_row_textInput" name="text" maxlength="1000" onkeydown="calcChars()" onkeyup="calcChars()" onkeypress="calcChars()"></textarea>
		</div>
		<div style="display:flex; width:85px; text-align:center;"><input style="width: 40px; text-align: right; border:none; background-color:var(--white)" type="number" id="chars" value="0" readonly><div>/1000</div></div>
		<button id="reviewReg_bottum_button">등록</button>
	</div>
</form>
</body>
</html>