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
<!-- <link rel="stylesheet" href="carousel.css"> -->

<link rel="stylesheet" href="http://localhost:8080/propofol_prj/common/css/myPortfolio.css">

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

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
	
	.carousel-item {
		height: 300px !important;
	}
	
	#page-section {
		margin-top: 0px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#submit").click(function() {
			$("#contactFrm").attr("action", "contact.do");
			
			$("#contactFrm").submit();
		}); // click
	}); // ready
	
	$(window).load(function() {
		if ("${ requestScope.msg }" != "") {
			alert("${ requestScope.msg }");
		} // end if
	}) // load
</script>
</head>
<body>
	<!-- header -->
	<header id="page-header">
		<!-- navbar -->
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/navbar.jsp">
			<c:param name="user_id" value="${ user_id }"/>
		</c:import>
	</header>
	<!-- section -->
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
						<p>아직도 포트폴리오 작성 고민하세요?</p>
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
						<p><a class="btn btn-lg btn-primary" href="/propofol_prj/portfolio/myPortfolio.do" role="button">내 포트폴리오 관리</a></p>
					</div>
				</div>
			</div>
			<div class="carousel-item">
				<svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
				<div class="container">
					<div class="carousel-caption text-right">
						<h1>포트폴리오는 Propofol</h1>
						<p>다양한 포트폴리오가 당신을 기다립니다.</p>
						<p><a class="btn btn-lg btn-primary" href="/propofol_prj/portfolio/portfolioList.do" role="button">포트폴리오 둘러보기</a></p>
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
		<div class="container" style="margin-top: 100px;">
			<div class="row">
				<div class="col">
					<div class="card">
						<div class="card-header bg-primary text-white"><i class="fa fa-envelope"></i> Contact me.</div>
						<div class="card-body">
							<form method="POST" name="contactFrm" id="contactFrm">
								<div class="form-group">
									<label for="name">이름</label>
									<input type="text" class="form-control" name="name" id="name" aria-describedby="emailHelp" placeholder="Enter name" required>
		                        </div>
		                        <div class="form-group">
		                            <label for="email">이메일</label>
		                            <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email" required>
		                            <small id="emailHelp" class="form-text text-muted">내용 확인 후 메일은 파기됩니다.</small>
		                        </div>
		                        <div class="form-group">
		                            <label for="message">메시지</label>
		                            <textarea class="form-control" name="message" id="message" rows="6" required></textarea>
		                        </div>
		                        <div class="mx-auto text-right">
									<button type="submit" name="submit" id="submit" class="btn btn-primary">전송하기</button>
		                        </div>
		                    </form>
		                </div>
		            </div>
        		</div>
			</div>
		</div>
	</section>
	<footer class="page-footer font-small bg-dark">
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/footer.jsp"/>
	</footer>
</body>
</html>