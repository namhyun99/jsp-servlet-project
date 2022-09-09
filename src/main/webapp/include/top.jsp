<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
$(function(){
    $("#btnLogin").click(function () {
        location.href = "${path}/member/login";
    });
})

</script>

<body data-theme="light">

<div id="load">
	<img src="${path}/Admin/resources/asset/images/load_img.gif" alt="loading">
</div>
	<!--top-->
	<div class="screen">
		<header id="header">
			<div class="logo">
				<a href="${path}/main">
					<h1>Welog</h1>
				</a>
			</div>
			<div class="login-wrap">
				<div class="darklight">
					<span onclick="modeChange()">
						<i class="fas fa-moon"	id="dark"></i>
					</span>
				</div>
				<div class="search">
					<a href="${path}/search">
						<span><i class="fas fa-search"></i></span>
					</a>
				</div>
				<c:choose>
					<c:when test="${sessionScope.userid != null}">
						<button type="button" id="btnWrite">글쓰기</button>
						<div class="profile">
							<a href="#" onclick="myOptionList()"> 
							<c:choose>
								<c:when test="${dto.profile_img != '-'}">
									<img src="${path}/upload/profile/${profile_img}">
								</c:when>
								<c:otherwise>
									<img src="${path}/resources/asset/images/no_profile.png">
								</c:otherwise>
							</c:choose>
							<span><i class="fas fa-caret-down"></i></span>
							</a>
						</div>
						<div class="option-list">
							<ul>
								<li><a href="notice.html">공지사항</a></li>
								<li><a href="qna.html">1:1문의</a></li>
								<li><a href="mypage.html">설정</a></li>
								<li><a href="${path}/member/logout.do">로그아웃</a></li>
								<c:if test="${authority == '관리자'}">
									<li><a href="${path}/admin/member/userList">관리자 페이지</a>
								</c:if>
							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<button type="button" id="btnLogin">로그인</button>
					</c:otherwise>
				</c:choose>
			</div>
		</header>
	</div>