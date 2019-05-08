<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="내 포트폴리오 관리 페이지 - 기술 스택 (보유 기술)" %>
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
		$("#btnAdd").click(function() {
			if (confirm("기술 스택을 등록하시겠습니까?")) {
				$("#tsFrm").attr("action", "./aboutMeAdd.do");
				
				$("#tsFrm").submit();
			} // end if
		}); // click
		
		$("#btnModify").click(function() {
			if (confirm("포트폴리오를 수정하시겠습니까?")) {
				$("#tsFrm").attr("action", "./aboutMeModify.do");
				
				$("#tsFrm").submit();				
			} // end if
		}); // click
		
		$("#btnReset").click(function() {
			if (confirm("포트폴리오를 정말 초기화하시겠습니까?\n초기화된 데이터는 복구되지 않습니다.")) {
				$("#tsFrm").attr("action", "./aboutMeReset.do");
				
				$("#tsFrm").submit();
			} // end if
		}); // click
	}); // ready
	
	$(window).load(function() {
		$("[name='selected_technique']").click(function() {
			$("input:checkbox[name='selected_technique']:checked").each(function() {
				var techniques = $(this).val();
				
				alert(techniques);
			}); // each
		}); // click
	}); // load
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
	    	<!-- 기술 스택 -->
		    <div id="section-main">
				<div style="color: #000000;">
					<span style="font-weight: bold;">*기술 스택 선택이 가능합니다. (복수 선택 가능)</span>
				</div>
				<form name="tsFrm">
					<div class="form-group row" style="padding-left: 9px; padding-top: 100px;">
						<div class="col-12" style="margin-bottom: 60px; text-align: center;">
							<h2><span style="font-weight: bold;">Technical Stacks</span></h2>
						</div>
						<div class="col-12" style="text-align: left;">
						<!-- forEach 이용 (현재는 테스트) -->
							<div style="margin-bottom: 60px;">
								<table style="margin: 1px auto;">
									<tr>
										<td style="width: 40px;"><input type="checkbox" value="Java" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="Java"/></td>
										<td style="width: 40px;"><input type="checkbox" value="HTML" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="HTML"/></td>
										<td style="width: 40px;"><input type="checkbox" value="CSS" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="CSS"/></td>
										<td style="width: 40px;"><input type="checkbox" value="JavaScript" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="JavaScript"/></td>
										<td style="width: 40px;"><input type="checkbox" value="jQuery" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="jQuery"/></td>
									</tr>
								</table>
							</div>
							<div style="margin-bottom: 60px;">
								<table style="margin: 1px auto;">
									<tr>
										<td style="width: 40px;"><input type="checkbox" value="Ajax" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="Ajax"/></td>
										<td style="width: 40px;"><input type="checkbox" value="Servlet" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="Servlet"/></td>
										<td style="width: 40px;"><input type="checkbox" value="JSP" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="JSP"/></td>
										<td style="width: 40px;"><input type="checkbox" value="Oracle" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="Oracle"/></td>
										<td style="width: 40px;"><input type="checkbox" value="MySQL" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="MySQL"/></td>
									</tr>
								</table>
							</div>
							<div style="margin-bottom: 60px;">
								<table style="margin: 1px auto;">
									<tr>
										<td style="width: 40px;"><input type="checkbox" value="Spring" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="Spring"/></td>
										<td style="width: 40px;"><input type="checkbox" value="MyBatis" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="MyBatis"/></td>
										<td style="width: 40px;"><input type="checkbox" value="Bootstrap" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="Bootstrap"/></td>
										<td style="width: 40px;"><input type="checkbox" value="Vue" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="Vue"/></td>
										<td style="width: 40px;"><input type="checkbox" value="React" name="selected_technique"/></td>
										<td style="width: 100px;"><c:out value="React"/></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!-- section-footer -->
			<div id="section-footer">
				<input type="button" value="초기화" name="btnReset" id="btnReset" class="btn btn-dark" style="margin-right: 15px;"/>
				<c:choose>
				<c:when test="${ requestScope.isExist }">
				<input type="button" value="기술 스택 수정" name="btnModify" id="btnModify" class="btn btn-primary"/>
				</c:when>
				<c:otherwise>
				<input type="button" value="기술 스택 등록" name="btnAdd" id="btnAdd" class="btn btn-primary"/>
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