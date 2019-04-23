<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="fixed navbar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
<style type="text/css">
	.navbar-nav > li > a { padding: 20px 20px; }
</style>
-->
		<nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
			<a class="navbar-brand" href="/propofol_prj/index.do">Propofol</a>
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="#">공지사항</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">문의하기</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">이용후기</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown" href="#">포트폴리오</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/propofol_prj/portfolio/myPortfolio.do">내 포트폴리오 관리</a>
						<a class="dropdown-item" href="/propofol_prj/portfolio/portfolioList.do">포트폴리오 둘러보기</a>
					</div>
				</li>
			</ul>
			<div class="navbar-nav ml-auto">
				<ul class="navbar-nav">
					<!-- 비 로그인 상태일 때 -->
					<c:if test="${ empty sessionScope.user_id }">
					<li class="nav-item">
						<a class="nav-link" href="#">로그인</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">회원가입</a>
					</li>
					</c:if>
					<!-- 로그인 상태일 때 -->
					<c:if test="${ not empty sessionScope.user_id }">
					<li class="nav-item">
						<a class="nav-link" href="#">마이페이지</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">로그아웃</a>
					</li>
					</c:if>
				</ul>
			</div>
		</nav>
		