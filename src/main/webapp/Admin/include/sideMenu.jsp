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
				<span>게시판 관리</span>
			</div>
			<ul class="link">
				<c:forEach var="dto" items="${sessionScope.boardList}">
					<li><a href="${path}/admin/board?board_no=${dto.board_no}" class="txt-name <c:if test="${not fn:contains(url, '/admin/board?board_no=${dto.board_no}')}">no</c:if>selected">${dto.title}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div class="box">
			<div class="txt-cate">
				<span>설정</span>
			</div>
			<ul class="link">
				<li><a href="#" class="txt-name <c:if test="${not fn:contains(url, '/admin/userList')}">no</c:if>selected">게시판 추가/삭제</a></li>
				<li><a href="#" class="txt-name <c:if test="${not fn:contains(url, '/admin/adminList')}">no</c:if>selected">카테고리 관리</a></li>
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