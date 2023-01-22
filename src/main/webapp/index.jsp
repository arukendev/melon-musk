<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MelonMusk</title>
<link rel="shortcut icon" href="files/favi/favicon.ico" type="image/x-icon">
<link rel="icon" href="files/favi/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/review/review.css">
<link rel="stylesheet" href="css/playlist/playlist.css">
<link rel="stylesheet" href="css/auth/loginStyle.css">
<link rel="stylesheet" href="css/auth/auth.css">
</head>
<body>
	<div id="index_hWrapper">
		<header>
			<div id="index_header_hLeft">
				<div id="index_hleft_title">
					<h1 id="index_title_h1">
						<a href="HomeC">
							<img src="files/main/logo.png">
						</a>
					</h1>
				</div>
			</div>
			<div id="index_header_hCenter">
				<div id="index_hCenter_search" class="hidden">
					<form action="SearchC">
						<i class="fas fa-times fa-lg"></i>
						<select name="sel">
							<option value="ar">가수</option>
							<option value="al">앨범</option>
							<option value="mu">음악</option>
						</select>
						<input name="result">
						<input hidden name="page" value="1">
						<input hidden name="index" value="1">
						<button class="index_button">
							<i class="fas fa-search"></i>
						</button>
					</form>
				</div>
				<div id="index_hCenter_nav">
					<div class="index_nav_content">
						<a href="ChartC">인기차트</a>
					</div>
					<div class="index_nav_content">
						<a href="NewMusicC">최신곡</a>
					</div>
					<div class="index_nav_content">
						<a href="ReviewC">게시판</a>
					</div>
					<div class="index_nav_content">
						<a href="PlaylistC">플레이리스트</a>
					</div>
					<div class="index_nav_content">
						<button class="index_button"><i class="fas fa-search"></i></button>
					</div>
				</div>
			</div>
			<div id="index_header_hRight">
				<div id="index_hRight_sign">
					<jsp:include page="${loginPage}"></jsp:include>
				<div id="result_txt">${r}</div>
				</div>
			</div>
		</header>
	</div>
	<div id="index_mWrapper">
		<div id="detail_background">
		</div>
		<main>
			<jsp:include page="${contentPage}"></jsp:include>
		</main>
	</div>
	<div id="index_fWrapper">
		<footer>
			<span onclick="">&copy;melonmusk</span>
		</footer>
	</div>
	<script type="text/javascript" src="js/index.js"></script>
	<script src="https://kit.fontawesome.com/772d40e343.js" crossorigin="anonymous"></script>
</body>
</html>