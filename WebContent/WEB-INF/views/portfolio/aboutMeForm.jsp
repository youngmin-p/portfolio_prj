<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="내 포트폴리오 관리 페이지 - 자기소개 (제목, 내용, 섬네일)" %>
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
					<li><a href="./experienceForm.do">관련 경험</a></li>
					<li><a href="./tellMeForm.do">연락처</a></li>
				</ul>
				<div style="clear: both;"></div>
			</div>
			<!-- section-main -->
	    	<!-- 자기소개 -->
		    <div id="section-main">
				<div style="color: #000000;">
					<span style="font-weight: bold;">*자기소개 등록이 가능합니다.</span>
				</div>
				<form name="amFrm">
					<div class="form-group row" style="padding-left: 9px; padding-top: 100px;">
						<div class="col-offset-1 col-5" style="text-align: center;">
							<img src="http://localhost:8080/propofol_prj/common/images/no_image.png" class="img-thumbnail" style="width: 300px; height: 300px;"/>
							<!-- ajax 이용해서 전송 : button 클릭으로 $("#thumbnail").click() -->
							<div style="margin-top: 10px;">
								<input type="file" name="uploadImg" style="display:none;"/>
								<label for="thumbnail" style="margin-right: 10px;">대표 이미지 등록</label>
								<input type="button" value="업로드" name="thumbnail" id="thumbnail" class="btn btn-secondary"/>
							</div>
						</div>
						<div class="col-6">
							<label for="title">제목</label>
							<input type="text" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요." maxlength="20"/>
							<!-- 제목에 대한 에러 메시지 출력 -->
							<!-- <div style="color: #FF0000; height: 38px;"> -->
							<div style="margin-top: 8px; height: 30px; color: #FF0000;">
								<div id="warnTitle" style="visibility: hidden;"></div>
							</div>
							<label for="contents">내용</label>
							<textarea class="form-control" id="contents" rows="6" placeholder="내용을 입력해주세요."></textarea>
							<!-- 내용에 대한 에러 메시지 출력 (글자수 제한 ?) -->
							<div style="margin-top: 8px; height: 30px; color: #FF0000;">
								<div id="warnContents" style="visibility: hidden;"></div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!-- section-footer -->
			<div id="section-footer">
				<input type="button" value="초기화" name="btnReset" id="btnReset" class="btn btn-dark" style="margin-right: 15px;"/>
				<input type="button" value="적용" name="btnRevise" id="btnRevise" class="btn btn-primary"/>
			</div>
		</div>
	</section>
	<footer class="page-footer font-small bg-dark">
		<c:import url="http://localhost:8080/propofol_prj/common/jsp/footer.jsp"/>
	</footer>
</body>
</html>