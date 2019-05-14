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
		moveFixBar();
		
		moveTopScroll();
		
		imageCaptionHover();
		
		onTheBottomScroll();
		
		getPortfolioOneDetail();
		
		/* 이미지를 클릭했을 때 비동기 전송으로 json 형태로 데이터를 받아와서, modal 창으로 화면을 띄워준다. */
		
		// 스크롤이 하단에 위치하게 되면 ajax로 데이터를 요청해서 성공 시에 func를 반복 실행하도록 하면 될 듯
	}); // ready
	
	function moveFixBar() {
		$(window).scroll(function() {
			if ($(document).scrollTop() > 266) {
				$("#section-search").addClass("section-search-fixed");
			} else {
				$("#section-search").removeClass("section-search-fixed");
			} // end else
		}); // scroll
	} // moveFixBar
	
	function moveTopScroll() {
		$("#topbutton").click(function() {
			$('html, body').animate({
				scrollTop: 0
			}, 500); // animate
			
			return false;
		}); // click
	} // moveTopScroll
	
	function imageCaptionHover() {
		/* 이미지 캡션 a:hover */
		$(".portfolio-list-caption-contents").hover(
				 function() {
					 $(this).find(".portfolio-list-caption-title").css("z-index", "99");
				 }, 
				 function() {
			 		$(this).find(".portfolio-list-caption-title").css("z-index", "-1");
				 });
	} // imageCaptionHover
	
	function onTheBottomScroll() {
		$(window).scroll(function() {
			if ($(this).scrollTop() >= $(document).height() - $(window).height()) {
				/* 문서의 끝에 도달하면 게시글 목록 호출 */
				setTimeout(function() {
					getPortfolioList();
				}, 500);
			} // end if
		}); // scroll
	} // onTheBottomScroll
	
	function getPortfolioList() {
		/* 게시판 리스트가 호출되면 ajax로 데이터를 가져온다. <ul>의 자식 <li> 마지막 요소에 <li>를 추가한다. */
		var idx = $(".portfolio-list-caption > li").last().find(".portfolio-list-caption-data").data("num");
		
		$.ajax({
			url: "./portfolioListCall.do",
			type: "get",
			data: "idx=" + idx,
			dataType: "json",
			success: function(json_obj) {
				var json_arr = json_obj.plsList;
				
				$.each(json_arr, function(i, json) {
					var addContents = "";
					
					addContents = 
						"	    			<li class='portfolio-list-caption-contents'>" + 
						"    					<div class='portfolio-list-caption-data' data-num='" + json.num + "' data-user_id='" + json.user_id + "'>" + 
						"	    					<div class='portfolio-list-caption-title'>" + 
						"	    						<div class='portfolio-list-caption-title-height'>" + 
						"	    							<span class='portfolio-list-caption-title-left'><c:out value='" + json.title + "' escapeXml='false'/></span>" + 
						"	    							<span class='portfolio-list-caption-title-right'><c:out value='" + json.write_dt + "' escapeXml='false'/></span>" + 
						"	    						</div>" + 
						"	    						<div class='clear-both'></div>" + 
						"	    						<div class='portfolio-list-caption-title-height'>" + 
						"	    							<span class='portfolio-list-caption-title-left'><c:out value='" + json.user_id + "' escapeXml='false'/></span>" + 
						"	    							<span class='portfolio-list-caption-title-right'><c:out value='" + json.hits + "' escapeXml='false'/></span>" + 
						"	    						</div>" + 
						"	    						<div class='clear-both'></div>" + 
						"	    					</div>" + 
						"	    					<div class='portfolio-list-caption-image'>" + 
						"	    						<a href='#' data-target='#modalView'>" + 
						"									<img src='http://localhost:8080/propofol_prj/upload/" + json.thumbnail_img + "' name='showImg' id='showImg' class='img-thumbnail' style='width: 330px; height: 320px;'/>" + 
						"	    						</a>" + 
						"	    					</div>" + 
						"    					</div>" + 
						"					</li>";
					
					$(".portfolio-list-caption").last().append(addContents);
				}) // each
				
				imageCaptionHover();
				
				getPortfolioOneDetail();
			}, // success
			error: function(xhr) {
				alert(xhr.status + "\n" + xhr.statusText);
			} // error
		}); // ajax
	} // getPortfolioList
	
	function getPortfolioOneDetail() {
		$(".portfolio-list-caption-contents").click(function() {
			// data가 "undefined"인 경우에는 error page로 이동
			//alert($(this).find(".portfolio-list-caption-data").data("user_id"));
			
			var target_id = $(this).find(".portfolio-list-caption-data").data("user_id");
			
			$.ajax({
				url: "./portfolioView.do",
				type: "get",
				data: "target_id=" + target_id,
				dataType: "json",
				success: function(json_obj) {
					$(".modalHeader-title").empty();
					$("#modalHeader-id").empty();
					$("#modalHeader-dt").empty();
					
					$("#am_title").attr("value", "");
					$("#am_contents").empty();
					$("#am_upload_img").attr("src", "");
					
					$(".modal-body-techStacks-tech").empty();
					
					$(".modalHeader-title").html(json_obj.title);
					$("#modalHeader-id").html(json_obj.user_id);
					$("#modalHeader-dt").html(json_obj.write_dt);
					
					$("#edu_title").attr("value", "");
					$("#edu_contents").empty();
					$("#edu_upload_img").attr("src", "");
					
					$("#prj_title").attr("value", "");
					$("#prj_contents").empty();
					$("#prj_upload_img").attr("src", "");
					
					$("#tm_phone_no").attr("value", "");
					$("#tm_email").attr("value", "");
					$("#tm_domain").attr("value", "");
					$("#tm_blog").attr("value", "");
					
					if (json_obj.am_result) {
						$(".modal-body-aboutMe").show();
						
						$("#am_title").attr("value", json_obj.am_title);
						$("#am_contents").html(json_obj.am_contents);
						$("#am_upload_img").attr("src", "http://localhost:8080/propofol_prj/upload/" + json_obj.am_upload_img);						
					} else {
						$(".modal-body-aboutMe").hide();
					} // end else
					
					if (json_obj.ts_result) {
						$(".modal-body-techStacks").show();
						
						var addContents = "";
						
						addContents += "<ul class='modal-body-techStacks-list'>";
						
						$.each(json_obj.ts_selected_technique, function(i, tech) {
							addContents += "	<li>";
							addContents += "		<img src='http://localhost:8080/propofol_prj/common/images/icon_" + tech.technique + ".png' width='120px' height='120px'/>";
							addContents += "	</li>";
						}) // each
						
						addContents += "</ul>";
						
						$(".modal-body-techStacks-tech").html(addContents);
					} else {
						$(".modal-body-techStacks").hide();
					} // end else
					
					if (json_obj.edu_result) {
						$(".modal-body-experience-edu").show();
						
						$.each(json_obj.expList, function(i, exp) {
							if ("Edu" == exp.exp_cd) {
								$("#edu_title").attr("value", exp.exp_title);
								$("#edu_contents").html(exp.exp_contents);
								$("#edu_upload_img").attr("src", "http://localhost:8080/propofol_prj/upload/" + exp.exp_upload_img);
							} // end if
						}) // each
					} else {
						$(".modal-body-experience-edu").hide();
					} // end else
					
					if (json_obj.prj_result) {
						$(".modal-body-experience-prj").show();
						
						$.each(json_obj.expList, function(i, exp) {
							if ("Prj" == exp.exp_cd) {
								$("#prj_title").attr("value", exp.exp_title);
								$("#prj_contents").html(exp.exp_contents);
								$("#prj_upload_img").attr("src", "http://localhost:8080/propofol_prj/upload/" + exp.exp_upload_img);
							} // end if
						}) // each
					} else {
						$(".modal-body-experience-prj").hide();
					} // end else
					
					if (json_obj.tm_result) {
						$(".modal-body-tellMe").show();
						
						$("#tm_phone_no").attr("value", json_obj.tm_phone_no);
						$("#tm_email").attr("value", json_obj.tm_email);
						$("#tm_domain").attr("value", json_obj.tm_domain);
						$("#tm_blog").attr("value", json_obj.tm_blog);
					} else {
						$(".modal-body-tellMe").hide();
					} // end else
				}, // success
				error: function(xhr) {
					alert(xhr.status + "\n" + xhr.statusText);
				} // error
			}); // ajax
			
			$("#modalView").modal();
			
			return false;
		}); // click
	} // getPortfolioOneDetail
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
		<!-- searchFrm에서 검색 발생 시 어떤 방식으로 데이터를 가져올 것인지? -->
		<div id="searchbar">
			<form name="searchFrm" id="searchFrm" method="get">
				<div id="section-search">
					<div class="h-100">
						<!-- topbutton -->
						<div class="d-flex justify-content-center h-100" id="topbutton">
							<a href="#">
								<img src="http://localhost:8080/propofol_prj/common/images/topbutton.png" width="38px" height="38px"/>
							</a>
						</div>
						<!-- searchbar -->
						<div class="d-flex justify-content-center h-100" id="section-search-sb">
							<div class="searchbar">
								<input type="text" name="search" id="search" class="search_input" placeholder="검색어를 입력해주세요...">
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
	    	<!-- 모달 로딩 -->
	    	<div class="modal fade" id="modalView">
	    		<div class="modal-dialog" id="modalDialog">
	    			<div class="modal-content">
	    				<!-- 모달 내용 삽입 -->
	    				<c:import url="./portfolioView.jsp"/>
	    			</div>
	    		</div>
	    	</div>
	    	<!-- 게시판 로딩 -->
	    	<div class="portfolio-list">
	    		<!-- image caption -->
	    		<ul class="portfolio-list-caption">
	    			<!-- forEach로 조회된 게시판을 호출하는 부분 -->
	    			<c:if test="${ not empty plsList }">
	    			<c:forEach var="pls" items="${ plsList }">
	    			<li class="portfolio-list-caption-contents">
	    				<div class="portfolio-list-caption-data" data-num="${ pls.num }" data-user_id="${ pls.user_id }">
		    				<div class="portfolio-list-caption-title">
		    					<div class="portfolio-list-caption-title-height">
		    						<span class="portfolio-list-caption-title-left"><c:out value="${ pls.title }"/></span>
		    						<span class="portfolio-list-caption-title-right"><c:out value="${ pls.write_dt }"/></span>
		    					</div>
		    					<div class="clear-both"></div>
		    					<div class="portfolio-list-caption-title-height">
		    						<span class="portfolio-list-caption-title-left"><c:out value="${ pls.user_id }"/></span>
		    						<span class="portfolio-list-caption-title-right"><c:out value="${ pls.hits }"/></span>
		    					</div>
		    					<div class="clear-both"></div>
		    				</div>
		    				<div class="portfolio-list-caption-image">
		    					<a href="#" data-target="#modalView">
									<img src="http://localhost:8080/propofol_prj/upload/${ pls.thumbnail_img }" name="showImg" id="showImg" class="img-thumbnail" style="width: 330px; height: 320px;"/>
		    					</a>
		    				</div>
	    				</div>
    				</li>
    				</c:forEach>
    				</c:if>
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