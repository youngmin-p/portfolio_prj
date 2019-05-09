<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="포트폴리오 게시판" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포트폴리오 게시판</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="http://localhost:8080/propofol_prj/common/css/portfolioList.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script> -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<!-- search bar -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

<script type="text/javascript">
	$(function() {
		$(window).scroll(function() {
			if ($(document).scrollTop() > 266) {
				$("#section-search").addClass("section-search-fixed");
			} else {
				$("#section-search").removeClass("section-search-fixed");
			} // end else
		}); // scroll
		
		/* 이미지 캡션 a:hover */
		 $(".portfolio-list-caption-contents").hover(
				 function() {
					 $(this).find(".portfolio-list-caption-title").css("z-index", "99");
				 }, 
				 function() {
			 		$(this).find(".portfolio-list-caption-title").css("z-index", "-1");
				 });
		 
		/* 
			이미지를 클릭했을 때 비동기 전송으로 json 형태로 데이터를 받아와서, modal 창으로 화면을 띄워준다.
		*/
	}); // ready
</script>
</head>
<body>
	<!-- header -->
	<header id="page-header">
		<!-- navbar -->
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/navbar.jsp"/>
	</header>
	<!-- section -->
	<section id="page-section">
		<div id="section-title">
			<h1>SHOW YOUR PORTFOLIO!</h1>
		</div>
		<div id="searchbar">
			<form name="searchFrm" id="searchFrm" method="get">
				<div id="section-search">
					<div class="h-100">
						<!-- topbutton -->
						<div class="d-flex justify-content-center h-100" id="topbutton">
							<h6>topbutton img</h6>
						</div>
						<!-- searchbar -->
						<div class="d-flex justify-content-center h-100" id="section-search-sb">
							<div class="searchbar">
								<input class="search_input" type="text" name="" id="" placeholder="검색어를 입력해주세요...">
								<a href="#" class="search_icon"><i class="fas fa-search"></i></a>
							</div>
						</div>
						<div class="clear-both"></div>
			    	</div>
		    	</div>
	    	</form>
    	</div>
		<!-- section-main -->
	    <div id="section-main">
	    	<!-- 게시판 로딩 (16개) -->
	    	<div class="portfolio-list">
	    		<!-- image caption -->
	    		<ul class="portfolio-list-caption">
	    			<!-- forEach로 조회된 게시판을 호출하는 부분 -->
	    			<li class="portfolio-list-caption-contents">
	    				<div class="portfolio-list-caption-title">
	    					<div class="portfolio-list-caption-title-data">
	    						<span class="portfolio-list-caption-title-left">제목</span>
	    						<span class="portfolio-list-caption-title-right">작성일</span>
	    					</div>
	    					<div class="clear-both"></div>
	    					<div class="portfolio-list-caption-title-data">
	    						<span class="portfolio-list-caption-title-left">유저 아이디</span>
	    						<span class="portfolio-list-caption-title-right">조회수</span>
	    					</div>
	    					<div class="clear-both"></div>
	    				</div>
	    				<div class="portfolio-list-caption-image">
	    					<a href="#">
	    						<img src="http://localhost:8080/propofol_prj/common/images/city_1.png" name="showImg" id="showImg" class="img-thumbnail" style="width: 330px; height: 320px;"/>
    						</a>
	    				</div>
    				</li>
	    			<li class="portfolio-list-caption-contents">
	    				<div class="portfolio-list-caption-title">
	    					<span>image!</span>
	    				</div>
	    				<div class="portfolio-list-caption-image">
	    					<a href="#">
	    						<img src="http://localhost:8080/propofol_prj/common/images/no_image.png" name="showImg" id="showImg" class="img-thumbnail" style="width: 330px; height: 320px;"/>
    						</a>
	    				</div>
    				</li>
	    			<li class="portfolio-list-caption-contents"><img src="http://localhost:8080/propofol_prj/common/images/no_image.png" name="showImg" id="showImg" class="img-thumbnail" style="width: 330px; height: 320px;"/></li>
	    			<li class="portfolio-list-caption-contents"><img src="http://localhost:8080/propofol_prj/common/images/no_image.png" name="showImg" id="showImg" class="img-thumbnail" style="width: 330px; height: 320px;"/></li>
	    			<li class="portfolio-list-caption-contents"><img src="http://localhost:8080/propofol_prj/common/images/no_image.png" name="showImg" id="showImg" class="img-thumbnail" style="width: 330px; height: 320px;"/></li>
	    		</ul>
	    	</div>
		</div>
		<!-- section-footer -->
		<div id="section-footer">
			<!-- not need -->
		</div>
	</section>
	<footer class="page-footer font-small bg-dark">
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/footer.jsp"/>
	</footer>
</body>
</html>