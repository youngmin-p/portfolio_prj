<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<form name="expFrm">
					<div style="padding-left: 69px;">
						<h4><span style="font-weight: bold;">프로젝트</span></h4>
					</div>
					<div class="form-group row" style="padding-left: 9px; padding-top: 54px;">
						<div class="col-6 ml-auto">
							<label for="title">제목</label>
							<input type="text" value="${ exp_search.title }" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요." maxlength="20"/>
							<!-- 제목에 대한 에러 메시지 출력 -->
							<!-- <div style="color: #FF0000; height: 38px;"> -->
							<div style="margin-top: 8px; height: 30px; color: #FF0000;">
								<div id="warnTitle" style="visibility: hidden;"></div>
							</div>
							<label for="contents">내용</label>
							<textarea class="form-control" name="contents" id="contents" rows="6" placeholder="내용을 입력해주세요.">${ exp_search.contents }</textarea>
							<!-- 내용에 대한 에러 메시지 출력 (글자수 제한 ?) -->
							<div style="margin-top: 8px; height: 30px; color: #FF0000;">
								<div id="warnContents" style="visibility: hidden;"></div>
							</div>
						</div>
						<div class="col-5" style="text-align: center; margin-right: 30px;">
							<img src="http://localhost:8080/propofol_prj/common/images/no_image.png" class="img-thumbnail" style="width: 300px; height: 300px;"/>
							<!-- ajax 이용해서 전송 : button 클릭으로 $("#thumbnail").click() -->
							<div style="margin-top: 10px;">
								<input type="file" name="upload_img" id="upload_img" style="display:none;"/>
								<label for="thumbnail" style="margin-right: 10px;">대표 이미지 등록</label>
								<input type="button" value="업로드" name="thumbnail" id="thumbnail" class="btn btn-secondary"/>
							</div>
						</div>
					</div>
				</form>