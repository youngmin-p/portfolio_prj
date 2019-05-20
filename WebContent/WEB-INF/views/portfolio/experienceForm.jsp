<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="내 포트폴리오 관리 페이지 - 관련 경험 (사진, 제목, 내용, 분류 코드)" %>
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
		if (${ not empty exp_search.exp_cd }) {
			$("#exp_cd").val("${ exp_search.exp_cd }").prop("selected", true);	
		} // end if
		
		if (${ not empty requestScope.nexp_cd }) {
			$("#exp_cd").val("${ requestScope.nexp_cd }").prop("selected", true);	
		} // end if
		
		var exp_cd = $("#exp_cd").val();
		
		$("#thumbnail").click(function() {
			$("#upload_img").click();
		}); // clcick
		
		$("#upload_img").change(function() {
			fileUpload(this);
		}); // change
		
		$("#btnAdd").click(function() {
			if (confirm(chkWord(exp_cd) + " 등록하시겠습니까?")) {
				$("#expFrm").attr("action", "./experienceAdd.do");
				
				$("#expFrm").submit();
			} // end if
		}); // click
		
		$("#btnModify").click(function() {
			if (confirm(chkWord(exp_cd) + " 수정하시겠습니까?")) {
				$("#expFrm").attr("action", "./experienceModify.do");
				
				$("#expFrm").submit();				
			} // end if
		}); // click
		
		$("#btnReset").click(function() {
			if (confirm(chkWord(exp_cd) + " 정말 초기화하시겠습니까?\n초기화된 데이터는 복구되지 않습니다.")) {
				$("#expFrm").attr("action", "./experienceReset.do");
				
				$("#expFrm").submit();
			} // end if
		}); // click
	}); // ready
	
	function fileUpload(evt) {
		var upload_img = $("#upload_img").val();
		
		if (upload_img == "") {
			alert("이미지를 선택해주세요.");
			
			return;
		} // end if
		
		// 사용 가능한 이미지 확장자 검증 (.png, .jpg)
		var isPossibleNames = ["png", "jpg"];
		var isPossible = false;
		
		var fileName = upload_img.substring(upload_img.lastIndexOf(".") + 1).toLowerCase();
		
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
	
	function chkWord(exp_cd) {
		var word = "";
		
		if (exp_cd == "Edu") {
			word = "교육사항을";
		} else {
			word = "프로젝트를";
		} // end else
		
		return word;
	} // chkWord
	
	$(window).load(function() {
		if ("${ requestScope.msg }" != "") {
			alert("${ requestScope.msg }");
		} // end if
		
		$("#exp_cd").change(function() {
			var exp_cd = $("#exp_cd").val();
			
			// select된 값을 변수에 담아서, change가 발생했을 때 값을 비교
			// 변경된 값이 있다면 confirm을 띄운다.
			
			location.href = "./experienceForm.do?exp_cd=" + exp_cd;
		}); // change
	}); // load
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
	    	<!-- 관련 경험 -->
		    <div id="section-main">
				<!-- import -->
				<c:if test="${ not empty exp_search }">
				<c:import url="./experience${ exp_search.exp_cd }Slice.jsp"/>
				</c:if>
				<c:if test="${ not empty requestScope.nexp_cd }">
				<c:import url="./experience${ requestScope.nexp_cd }Slice.jsp"/>
				</c:if>
			</div>
			<!-- section-footer -->
			<div id="section-footer">
				<c:choose>
				<c:when test="${ requestScope.isExist }">
				<input type="button" value="초기화" name="btnReset" id="btnReset" class="btn btn-dark" style="margin-right: 15px;"/>
				<input type="button" value="관련 경험 수정" name="btnModify" id="btnModify" class="btn btn-primary"/>
				</c:when>
				<c:otherwise>
				<input type="button" value="관련 경험 등록" name="btnAdd" id="btnAdd" class="btn btn-primary"/>
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