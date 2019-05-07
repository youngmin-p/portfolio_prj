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
			/* scroll 수정 필요 #searchbar는 필요없을 듯? */
			if ($(document).scrollTop() > 322) {
				$("#section-search").addClass("section-search-fixed");
			} else {
				$("#section-search").removeClass("section-search-fixed");
			} // end else
		}); // scroll
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
		<!-- topbutton 추가하면 좋을 듯 -->
		<!-- searchbar -->
		<div id="searchbar">
			<form name="searchFrm" id="searchFrm" method="get">
				<div id="section-search">
					<div class="h-100">
						<div class="d-flex justify-content-center h-100" id="section-search-sb">
							<div class="searchbar">
								<input class="search_input" type="text" name="" id="" placeholder="검색어를 입력해주세요...">
								<a href="#" class="search_icon"><i class="fas fa-search"></i></a>
							</div>
						</div>
			    	</div>
		    	</div>
	    	</form>
    	</div>
		<!-- section-main -->
	    <div id="section-main">
	    	<!-- ajax로 게시판을 불러오는 형태. 게시글은 16개씩 -->
			<form method="POST" enctype="multipart/form-data" name="plFrm" id="plFrm">
				<div style="color: #000000;">
					<span style="float: left; font-weight: bold;">*포트폴리오 등록 및 수정이 가능합니다.</span>
					<span style="float: right; width: 150px;">
						<label for="permit_st">관리자 승인 여부</label>
						<input type="text" name="permit_st" id="permit_st" class="form-control text-center" readonly="readonly" style="margin-bottom: 10px; width: 150px; height: 38px; font-weigth: bold;"/>
						<label for="public_st">공개 여부 설정</label>
						<select name="public_st" id="public_st" class="custom-select" style="text-align-last: center;">
							<option value="Y"${ mp_search.public_st eq 'Y' ? "selected='selected'" : "''" }>공개</option>
							<option value="N"${ mp_search.public_st eq 'N' ? "selected='selected'" : "''" }>비공개</option>
						</select>
					</span>
					<div style="clear: both;"></div>
				</div>
				<div class="form-group row" style="padding-left: 9px;">
					<div class="col-3"></div>
					<div class="col-6" style="text-align: center; margin-top: -60px;">
					<!-- 임시 isExist. 현재 항상 true 상태  -->
					<c:choose>
					<c:when test="${ requestScope.isExist }">
						<img src="http://localhost:8080/propofol_prj/upload/${ mp_search.thumbnail_img }" name="showImg" id="showImg" class="img-thumbnail" style="width: 300px; height: 300px;"/>
					</c:when>
					<c:otherwise>
						<img src="http://localhost:8080/propofol_prj/common/images/no_image.png" name="showImg" id="showImg" class="img-thumbnail" style="width: 300px; height: 300px;"/>
					</c:otherwise>
					</c:choose>
						<div style="margin-top: 10px;">
							<input type="file" name="thumbnail_img" id="thumbnail_img" style="display:none;"/>
							<label for="thumbnail" style="margin-right: 10px;">섬네일 이미지 등록</label>
							<input type="button" value="업로드" name="thumbnail" id="thumbnail" class="btn btn-secondary"/>
						</div>
					</div>
					<div class="col-3"></div>
				</div>
				<div class="form-group row" style="padding-left: 9px; margin-top: 27px;">
					<div class="col-3"></div>
					<div class="col-6">
						<label for="title">제목</label>
						<input type="text" value="${ mp_search.title }" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요. (20자 이내)" maxlength="20"/>
						<!-- 제목에 대한 에러 메시지 출력 -->
						<div style="margin-top: 8px; height: 30px; color: #FF0000;">
							<div id="warnTitle" style="visibility: hidden;"></div>
						</div>
					</div>
					<div class="col-3"></div>
				</div>
			</form>
		</div>
		<!-- section-footer -->
		<div id="section-footer">
			<!-- footer 부분의 필요성은 없다고 생각하는데... -->
		</div>
	</section>
	<footer class="page-footer font-small bg-dark">
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/footer.jsp"/>
	</footer>
</body>
</html>