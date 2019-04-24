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
					<li><a href="./experienceForm.do">관련 경험</a></li>
					<li><a href="./tellMeForm.do">연락처</a></li>
				</ul>
				<div style="clear: both;"></div>
			</div>
			<!-- section-main -->
	    	<!-- 자기소개 -->
		    <div id="section-main">
				<div style="color: #000000;">
					<span style="float: left; font-weight: bold;">*포트폴리오 등록 및 수정이 가능합니다.</span>
					<span style="float: right; width: 150px;">
						<label for="permit_st">관리자 승인 여부</label>
						<input type="text" name="permit_st" id="permit_st" class="form-control text-center" readonly="readonly" style="margin-bottom: 10px; width: 150px; height: 38px; font-weigth: bold;"/>
						<label for="public_st">공개 여부 설정</label>
						<select name="public_st" id="public_st" class="custom-select" style="text-align-last: center;">
							<option value="public">공개</option>
							<option value="private">비공개</option>
						</select>
					</span>
					<div style="clear: both;"></div>
				</div>
				<form name="mpFrm">
					<div class="form-group row" style="padding-left: 9px;">
						<div class="col-3"></div>
						<div class="col-6" style="text-align: center; margin-top: -60px;">
							<img src="http://localhost:8080/propofol_prj/common/images/no_image.png" class="img-thumbnail" style="width: 300px; height: 300px;"/>
							<!-- ajax 이용해서 전송 : button 클릭으로 $("#thumbnail").click() -->
							<div style="margin-top: 10px;">
								<input type="file" name="uploadImg" style="display:none;"/>
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
				<input type="button" value="포트폴리오 삭제" name="btnRemove" class="btn btn-danger" style="margin-right: 15px;"/>
				<!-- 포트폴리오 테이블 조회 시 아이디 검색 여부에 따라 다른 버튼을 보여준다. -->
				<c:choose>
				<c:when test="${ empty requestScope.isExist }">
				<input type="button" value="포트폴리오 등록" name="btnAdd" id="btnAdd" class="btn btn-primary"/>
				</c:when>
				<c:otherwise>
				<input type="button" value="포트폴리오 수정" name="btnModify" id="btnModify" class="btn btn-primary"/>
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