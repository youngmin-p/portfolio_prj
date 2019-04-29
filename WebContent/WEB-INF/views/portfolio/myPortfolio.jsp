<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="내 포트폴리오 관리 페이지 (섬네일, 타이틀 수정)" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 포트폴리오 관리</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="http://localhost:8080/propofol_prj/common/css/myPortfolio.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#permit_st").val("${ mp_search.permit_st }");
		
		$("#thumbnail").click(function() {
			$("#thumbnail_img").click();
		}); // clcick
		
		$("#thumbnail_img").change(function() {
			fileUpload(this);
		}); // change
		
		$("#btnAdd").click(function() {
			if (confirm("포트폴리오를 등록하시겠습니까?")) {
				$("#mpFrm").attr("action", "./myPortfolioAdd.do");
				
				$("#mpFrm").submit();
			} // end if
		}); // click
		
		$("#btnModify").click(function() {
			if (confirm("포트폴리오를 수정하시겠습니까?")) {
				$("#mpFrm").attr("action", "./myPortfolioModify.do");
				
				$("#mpFrm").submit();				
			} // end if
		}); // click
		
		$("#btnRemove").click(function() {
			if (confirm("포트폴리오를 정말 삭제하시겠습니까?\n삭제된 데이터는 복구되지 않습니다.")) {
				$("#mpFrm").attr("action", "./myPortfolioRemove.do");
				
				$("#mpFrm").submit();
			} // end if
		}); // click
	}); // ready
	
	$(window).load(function() {
		if ("${ requestScope.msg }" != "") {
			alert("${ requestScope.msg }");
		} // end if
	}); // load
	
	function fileUpload(evt) {
		var thumbnail_img = $("#thumbnail_img").val();
		
		if (thumbnail_img == "") {
			alert("이미지를 선택해주세요.");
			
			return;
		} // end if
		
		// 사용 가능한 이미지 확장자 검증 (.png, .jpg)
		var isPossibleNames = ["png", "jpg"];
		var isPossible = false;
		
		var fileName = thumbnail_img.substring(thumbnail_img.lastIndexOf(".") + 1);
		
		for (var i = 0; i < isPossibleNames.length; i++) {
			if (fileName == isPossibleNames[i]) {
				isPossible = true;
			} // end if
		} // end for
		
		if (isPossible) {
			// 사용 가능한 형식일 때
			showFileImg(evt);
		} else {
			alert("사용 가능한 이미지 형식이 아닙니다. 다시 시도해주세요.");
		} // end else
	} // fileUpload
	
	function showFileImg(img) {
		// 이미지 프리뷰
	    if (img.files && img.files[0]) {
	    	var reader = new FileReader();
	    	
	        reader.onload = function(evt) {
	        	$("#showImg").attr("src", evt.target.result);
			} // onload
	        
	        reader.readAsDataURL(img.files[0]);
	    } // end if
	} // showFileImg
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
	    <div class="container">
			<div id="section-head">
				<h3><span style="float: left;"><a href="./myPortfolio.do">내 포트폴리오 관리</a></span></h3>
				<ul>
					<li><a href="./aboutMeForm.do">자기소개</a></li>
					<li><a href="./techStacksForm.do">기술 스택</a></li>
					<li><a href="./experienceForm.do">관련 경험</a></li>
					<li><a href="./tellMeForm.do">연락처</a></li>
				</ul>
				<div style="clear: both;"></div>
			</div>
			<!-- section-main -->
	    	<!-- 자기소개 -->
		    <div id="section-main">
				<form method="POST" enctype="multipart/form-data" name="mpFrm" id="mpFrm">
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
				<input type="button" value="포트폴리오 삭제" name="btnRemove" id="btnRemove" class="btn btn-danger" style="margin-right: 15px;"/>
				<!-- 포트폴리오 테이블 조회 시 아이디 검색 여부에 따라 다른 버튼을 보여준다. -->
				<c:choose>
				<c:when test="${ requestScope.isExist }">
				<input type="button" value="포트폴리오 수정" name="btnModify" id="btnModify" class="btn btn-primary"/>
				</c:when>
				<c:otherwise>
				<input type="button" value="포트폴리오 등록" name="btnAdd" id="btnAdd" class="btn btn-primary"/>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>
	<footer class="page-footer font-small bg-dark">
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/footer.jsp"/>
	</footer>
</body>
</html>