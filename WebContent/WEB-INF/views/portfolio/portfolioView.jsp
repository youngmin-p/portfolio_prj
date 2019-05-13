<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    info="포트폴리오 게시글 상세보기" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
					<!-- header -->
					<div class="modal-header" id="modalHeader">
						<div class="modalHeader-wrap">
							<div class="modalHeader-title">포트폴리오 제목</div>
							<div class="modalHeader-id">
								<span>타깃 아이디</span>
								<span class="modalHeader-wide">•</span>
								<span>조회수</span>
							</div>
						</div>
						<!-- 닫기 버튼 -->
						<button type="button" class="close" data-dismiss="modal">
							<span style="color: #FFF;">×</span>
						</button>
						<!-- header title -->
						<h3 class="modal-title">${ pls.title }</h3>
					</div>
					<!-- body -->
					<div class="modal-body" id="modalBody">
						Body
					</div>
					<!-- Footer -->
					<div class="modal-footer" id="modalFooter">
						<button type="button" class="btn btn-primary" data-dismiss="modal">닫기</button>
					</div>
