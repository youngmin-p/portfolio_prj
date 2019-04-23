<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="내 포트폴리오 관리 페이지 - 관련 경험 (제목, 내용, 섬네일)" %>
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
	    	<!-- 연락처 -->
		    <div id="section-main">
   				<div style="color: #000000;">
					<span style="font-weight: bold;">*연락처 등록이 가능합니다.</span>
				</div>
				<form name="tmFrm">
					<!-- 핸드폰 번호 -->
					<div class="form-group row" style="padding-left: 9px; padding-top: 80px;">
						<div class="col-3" style="text-align: right; padding-right: 25px;">
							<img src="http://localhost:8080/propofol_prj/common/images/smartphone-call.png" style="width: 100px; height: 100px;"/>
						</div>
						<div class="col-3 mr-auto">
							<label for="phone_no">핸드폰 번호</label>
							<input type="text" name="phone_no" id="phone_no" class="form-control" maxlength="11" placeholder="- 제외 입력"/>
							<!-- 핸드폰 번호에 대한 에러 메시지 출력 -->
							<div style="margin-top: 8px; height: 30px; color: #FF0000;">
								<div id="warnPhoneNo" style="visibility: hidden;"></div>
							</div>
						</div>
					</div>
					<!-- 이메일 주소 -->
					<div class="form-group row" style="padding-left: 9px; padding-top: 20px;">
						<div class="col-3" style="text-align: right;">
							<img src="http://localhost:8080/propofol_prj/common/images/email.png" style="width: 100px; height: 100px;"/>
						</div>
						<div class="col-6 mr-auto">
							<div class="row">
								<div class="col-4">
									<label for="email">이메일</label>
									<input type="text" name="email" id="email" class="form-control"/>
								</div>
								<div class="col-1" style="padding-top: 32px;">
									<span style="font-size: 20px;">@</span>
								</div>
								<div class="col-4 mr-auto" style="padding-top: 32px;">
									<select name="domain" class="custom-select">
										<option value="naver.com">naver.com</option>
										<option value="gmail.com">gmail.com</option>
									</select>
									<!-- 이메일 주소에 대한 에러 메시지 출력 -->
									<div style="margin-top: 8px; height: 30px; color: #FF0000;">
										<div id="warnPhoneNo" style="visibility: hidden;"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 블로그 주소 -->
					<!-- 여기부터 작업 시작 -->
					<div class="form-group row" style="padding-left: 9px; padding-top: 20px;">
						<div class="col-3" style="text-align: right; padding-right: 25px;">
							<img src="http://localhost:8080/propofol_prj/common/images/blogger-logotype.png" style="width: 90px; height: 90px;"/>
						</div>
						<div class="col-7 mr-auto">
							<label for="blog">블로그</label>
							<input type="text" name="blog" id="blog" class="form-control"/>
							<!-- 블로그 주소에 대한 에러 메시지 출력 -->
							<div style="margin-top: 8px; height: 30px; color: #FF0000;">
								<div id="warnPhoneNo" style="visibility: hidden;"></div>
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