<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Propofol Site</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/carousel/">

<!-- Custom styles for this template -->
<link rel="stylesheet" href="carousel.css">

<link rel="stylesheet" href="http://211.63.89.132:8080/propofol_prj/common/css/myPortfolio.css">

<style>
	.bd-placeholder-img {
	  font-size: 1.125rem;
	  text-anchor: middle;
	  -webkit-user-select: none;
	  -moz-user-select: none;
	  -ms-user-select: none;
	  user-select: none;
	}

	@media (min-width: 768px) {
	  .bd-placeholder-img-lg {
	    font-size: 3.5rem;
	  }
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		
	}); // ready
</script>
</head>
<body>
	<!-- header -->
	<header id="page-header">
		<!-- navbar -->
		<c:import url="http://211.63.89.132:8080/propofol_prj/common/jsp/navbar.jsp"/>
	</header>
	<!-- section -->
	<!-- 
		EL 하나의 JSP를 받아서 보여준다.
		
		ex) myPortfolio.jsp?page=aboutMe&param=...
	 -->
	<section id="page-section">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
				<div class="container">
					<div class="carousel-caption text-left">
						<h1>최강의 포트폴리오 사이트</h1>
						<p>아직도 포트폴리오 고민하십니까?</p>
						<p><a class="btn btn-lg btn-primary" href="#" role="button">지금 가입하기</a></p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
				<div class="container">
					<div class="carousel-caption">
						<h1>당신이 최고가 될 수 있습니다.</h1>
						<p>더 이상 망설이지 마십시오.</p>
						<p><a class="btn btn-lg btn-primary" href="#" role="button">망설이지 않고 가입하기</a></p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
				<div class="container">
					<div class="carousel-caption text-right">
						<h1>포트폴리오는 역시 Propofol!</h1>
						<p>...</p>
						<p><a class="btn btn-lg btn-primary" href="#" role="button">들어오시오</a></p>
					</div>
				</div>
			</div>
		</div>
			<a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</section>
	<footer class="page-footer font-small bg-dark">
		<c:import url="http://211.63.89.132:8080/propofol_prj/common/jsp/footer.jsp"/>
	</footer>
</body>
</html>