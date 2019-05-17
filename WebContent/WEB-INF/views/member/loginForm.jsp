<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="로그인 폼" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">

<!--Bootsrap 4 CDN-->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<!--Fontawesome CDN-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel="stylesheet" href="http://localhost:8080/propofol_prj/common/css/login.css">

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script> -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
	$(function() {
		if ("${ requestScope.msg }" != "") {
			alert("${ requestScope.msg }");	
		} // end if
		
		$("#moveIndex").hover(
			function() {
				$(this).addClass("links-moveIndex");
			}, 
			function() {
				$(this).removeClass("links-moveIndex");
			}); // hover
		
		$("#loginBtn").click(function() {
			// 아이디, 비밀번호 유효성 검증 (regExp)
			var user_id = $("#user_id");
			var password = $("#password");
			
			if (user_id.val().trim() == "") {
				alert("아이디 입력은 필수입니다.");
				user_id.val("");
				user_id.focus();
				return;
			} // end if
			
			if (password.val().trim() == "") {
				alert("비밀번호 입력은 필수입니다.");
				password.val("");
				password.focus();
				return;
			} // end if
			
			$("#loginFrm").attr("action", "./loginProcess.do");
			$("#loginFrm").submit();
		}); // click
	}); // ready
</script>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3><a href="/propofol_prj/index.do" class="links" id="moveIndex">Propofol Login</a></h3>
				<div class="d-flex justify-content-end social_icon" style="visibility: hidden;">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div>
			</div>
			<div class="card-body">
				<form method="POST" name="loginFrm" id="loginFrm">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" name="user_id" id="user_id" class="form-control" placeholder="아이디" required>
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" name="password" id="password" class="form-control" placeholder="비밀번호">
					</div>
					<div class="row align-items-center remember">
						<input type="checkbox">로그인 유지
					</div>
					<div class="form-group">
						<input type="button" name="loginBtn" id="loginBtn" value="Login" class="btn float-right login_btn">
					</div>/
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					계정이 없으세요?<a href="#">가입하기</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="#">비밀번호를 잊어버리셨나요?</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>