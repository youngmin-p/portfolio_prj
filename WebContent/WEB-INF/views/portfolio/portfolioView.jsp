<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="포트폴리오 게시글 상세보기" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
					<!-- header -->
					<div class="modal-header" id="modalHeader">
						<!-- header title -->
						<div class="modalHeader-wrap">
							<div class="modalHeader-title"></div>
							<div class="modalHeader-body">
								<span id="modalHeader-id"></span>
								<span class="modalHeader-wide">•</span>
								<span id="modalHeader-dt"></span>
							</div>
						</div>
						<!-- 닫기 버튼 -->
						<button type="button" class="close" data-dismiss="modal">
							<span style="color: #FFF;">×</span>
						</button>
					</div>
					<!-- body -->
					<div class="modal-body" id="modalBody">
						<!-- 자기소개 -->
						<div class="modal-body-aboutMe">
							<div class="container-fluid">
								<div class="modal-body-title">자기소개</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto modal-body-aboutMe-image">
										<img src="" name="am_upload_img" id="am_upload_img" class="img-thumbnail"/>
									</div>
								</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto">
										<input type="text" value="" name="am_title" id="am_title" class="modal-body-aboutMe-title form-control" disabled="disabled"/>
									</div>
								</div>
								<div class="row modal-body-row">	
									<div class="col-8 mx-auto">
										<textarea name="am_contents" id="am_contents" class="modal-body-aboutMe-contents form-control" rows="6" disabled="disabled"></textarea>
									</div>
								</div>
							</div>
						</div>
						<!-- 기술 스택 -->
						<div class="modal-body-techStacks">
							<div class="modal-body-title">기술 스택</div>
							<div class="row modal-body-row">
								<div class="col modal-body-techStacks-tech">
									<!-- value에 따른 이미지 보여주기 -->
								</div>
							</div>
						</div>
						<!-- 관련 경험 -->
						<div class="modal-body-experience-edu">
							<div class="container-fluid">
								<div class="modal-body-title">교육사항</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto modal-body-experience-edu-image">
										<img src="" name="edu_upload_img" id="edu_upload_img" class="img-thumbnail" style="width: 100%; height: auto;"/>
									</div>
								</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto">
										<input type="text" value="" name="edu_title" id="edu_title" class="modal-body-experience-edu-title form-control" disabled="disabled"/>
									</div>
								</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto">
										<textarea name="edu_contents" id="edu_contents" class="modal-body-experience-edu-contents form-control" rows="6" disabled="disabled"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-body-experience-prj">
							<div class="container-fluid">
								<div class="modal-body-title">프로젝트</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto modal-body-experience-prj-image">
										<img src="" name="prj_upload_img" id="prj_upload_img" class="img-thumbnail"/>
									</div>
								</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto">
										<input type="text" value="" name="prj_title" id="prj_title" class="modal-body-experience-prj-title form-control" disabled="disabled"/>									
									</div>
								</div>
								<div class="row modal-body-row">
									<div class="col-8 mx-auto">
										<textarea name="prj_contents" id="prj_contents" class="modal-body-experience-prj-contents form-control" rows="6" disabled="disabled"></textarea>									
									</div>
								</div>
							</div>
						</div>
						<div class="modal-body-tellMe">
							<div class="container-fluid">
								<div class="modal-body-title">연락처</div>
								<!-- 핸드폰 번호 -->
								<div class="row modal-body-row">
									<div class="col-3" style="text-align: right; padding-right: 25px;">
										<img src="http://localhost:8080/propofol_prj/common/images/smartphone-call.png" style="width: 100px; height: 100px;"/>
									</div>
									<div class="col-3 mr-auto">
										<label for="phone_no">핸드폰 번호</label>
										<input type="text" value="" name="tm_phone_no" id="tm_phone_no" class="modal-body-tellMe-phoneNo form-control" disabled="disabled"/>
									</div>
								</div>
								<!-- 이메일 주소 -->
								<div class="row modal-body-row">
									<div class="col-3" style="text-align: right;">
										<img src="http://localhost:8080/propofol_prj/common/images/email.png" style="width: 100px; height: 100px;"/>
									</div>
									<div class="col-6 mr-auto">
										<div class="row">
											<div class="col-4">
												<label for="tm_email">이메일</label>
												<input type="text" value="" name="tm_email" id="tm_email" class="modal-body-tellMe-email form-control" disabled="disabled"/>
											</div>
											<div class="col-1" style="padding-top: 29px; padding-right: 32px;">
												<span style="font-size: 20px;">@</span>
											</div>
											<div class="col-4 mr-auto" style="padding-top: 29px;">
												<input type="text" value="" name="tm_domain" id="tm_domain" class="modal-body-tellMe-domain form-control" disabled="disabled"/>
											</div>
										</div>
									</div>
								</div>
								<!-- 블로그 주소 -->
								<div class="row modal-body-row">
									<div class="col-3" style="text-align: right; padding-right: 25px;">
										<img src="http://localhost:8080/propofol_prj/common/images/blogger-logotype.png" style="width: 90px; height: 90px;"/>
									</div>
									<div class="col-7 mr-auto">
										<label for="tm_blog">블로그</label>
										<input type="text" value="" name="tm_blog" id="tm_blog" class="modal-body-tellMe-blog form-control" disabled="disabled"/>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Footer -->
					<div class="modal-footer" id="modalFooter">
						<button type="button" class="btn btn-primary" data-dismiss="modal">닫기</button>
					</div>
