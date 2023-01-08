<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Melon Musk</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/review/review.css">
<link rel="stylesheet" href="css/playlist/playlist.css">
<link rel="stylesheet" href="css/auth/loginStyle.css">
</head>
<body>
	<div id="index_hWrapper">
		<header>
			<div id="index_header_hLeft">
				<div id="index_hleft_title">
					<a href="HomeC">
						<h1 id="index_title_h1">Melon Musk</h1>
					</a>
				</div>
			</div>
			<div id="index_header_hCenter">
				<div id="index_hCenter_search" class="hidden">
					<form action="SearchC">
						<input name="result" />
						<button>Search</button>
					</form>
					<ul id="index_search_ul">
					</ul>
				</div>
				<div id="index_hCenter_nav">
					<div class="index_nav_content">
						<a href="ChartC">Charts</a>
					</div>
					<div class="index_nav_content">
						<a href="NewMusicC">New Musics</a>
					</div>
					<div class="index_nav_content">
						<a href="ReviewC">Reviews</a>
					</div>
					<div class="index_nav_content">
						<a href="PlaylistC">Library</a>
					</div>
					<div class="index_nav_content">
						<button>Search</button>
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
		<main>
			<jsp:include page="${contentPage}"></jsp:include>
		</main>
	</div>
	<div id="index_fWrapper">
		<footer>
			<span onclick="">&copy;semi project</span>
		</footer>
	</div>
	<script type="text/javascript" src="js/index.js"></script>
</body>
</html>