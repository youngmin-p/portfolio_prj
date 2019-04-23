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
	    <div class="container">
			<div id="section-head">
				<h3><span style="float: left;"><a href="./myPortfolio.do">내 포트폴리오 관리</a></span></h3>
				<ul>
					<li><a href="./aboutMeForm.do">자기소개</a></li>
					<li><a href="./techStacksForm.do">기술 스택</a></li>
					<li><a href="#">관련 경험</a></li>
					<li><a href="#">연락처</a></li>
				</ul>
				<div style="clear: both;"></div>
			</div>
			<!-- section-main -->
	    	<!-- 자기소개 -->
			<div id="section-main">
				<div style="color: #FF0000;">
					<span style="font-weight: bold;">*포트폴리오 등록을 위해 다음의 정보를 입력해주세요. (최초 1회 입력)</span>
				</div>
				<form name="regFrm">
					<div class="form-group row" style="padding-left: 9px; padding-top: 100px;">
						<div class="col-offset-1 col-5">
							<label for="title">포트폴리오 제목</label>
							<input type="text" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요." maxlength="20"/>
							<!-- 포트폴리오 제목에 대한 에러 메시지 출력 -->
							<div style="color: #FF0000; height: 38px;">
								<div id="warnTitle" style="visibility: hidden;"></div>
							</div>
						</div>
						<div class="col-6"></div>
					</div>
					<!-- ajax 이용해서 전송 : button 클릭으로 $("#thumbnail").click() -->
					<div class="form-group row" style="padding-left: 9px; padding-top: 100px;">
						<div class="col-offset-1 col-5">
							<input type="file" name="uploadImg" style="display:none;"/>
							<label for="thumbnail" style="margin-right: 10px;">섬네일 이미지 등록</label>
							<input type="button" value="업로드" name="thumbnail" id="thumbnail" class="btn btn-secondary"/>
							<!-- 업로드 가능 형식에 대한 에러 메시지 출력 -->
							<div style="color: #FF0000; height: 38px;">
								<div id="warnThumbnail" style="visibility: hidden;"></div>
							</div>
						</div>
						<div class="col-6"></div>
					</div>
				</form>
			</div>
			<!-- section-footer -->
			<div id="section-footer">
				<input type="button" value="메인으로" name="btnMain" id="btnMain" class="btn btn-dark" style="margin-right: 15px;"/>
				<input type="button" value="다음으로" name="btnNext" id="btnNext" class="btn btn-primary"/>
			</div>
		</div>
	</section>
	<footer class="page-footer font-small bg-dark">
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/footer.jsp"/>
	</footer>
</body>
</html>