<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="side-menu-wrap">
	<div class="menu-box">

		<div class="box-userInfo">
			<span>${authority}님, 안녕하세요!</span>
		</div>

		<div class="box">
			<div class="txt-cate">
				<span>회원관리</span>
			</div>

			<ul class="link">
				<li><a href="${path}/admin/member/userList" class="txt-name <c:if test="${not fn:contains(url, '/admin/member/userList')}">no</c:if>selected">회원목록</a></li>
				<li><a href="${path}/admin/member/adminList" class="txt-name <c:if test="${not fn:contains(url, '/admin/member/adminList')}">no</c:if>selected">관리자목록</a></li>
			</ul>
		</div>

		<div class="box">
			<div class="txt-cate">
				<span>게시판 글 관리</span>
			</div>
			<ul class="link">
				<li><a href="${path}/admin/board/contents" class="txt-name <c:if test="${not fn:contains(url, '/admin/board/contents')}">no</c:if>selected">컨텐츠 관리</a></li>
				<li><a href="${path}/admin/board/notice" class="txt-name <c:if test="${not fn:contains(url, '/admin/board/notice')}">no</c:if>selected">공지사항 관리</a></li>
				<li><a href="${path}/admin/board/faq" class="txt-name <c:if test="${not fn:contains(url, '/admin/board/faq')}">no</c:if>selected">FAQ</a></li>
			</ul>
		</div>
			
		<div class="box">
			<div class="txt-cate">
				<a href="${path}/admin/login/logout.do">로그아웃 하기</a>
			</div>
			<!-- <div class="item_list">
                        <ul>
                            <li><a href="#">사용자</a></li>
                            <li><a href="#">관리자</a></li>
                            <li><a href="#">문의내역</a></li>
                        </ul>
                    </div> -->
		</div>
	</div>
</div>