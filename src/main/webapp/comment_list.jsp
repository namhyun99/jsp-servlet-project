<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:forEach var="cmt" items="${list}">
	<div class="info-profile">
		<div class="profile">
			<c:choose>
				<c:when test="${cmt.profile_img != '-'}">
					<img src='/profile/${cmt.profile_img}'>
				</c:when>
				<c:otherwise>
					<img src="../resources/asset/images/no_profile.png">
				</c:otherwise>
			</c:choose>
			<div class="comment-info">
				<div class="userid">${cmt.writer}</div>
				<div class="date">
				<fmt:formatDate value="${cmt.write_date}" pattern="yyyy년 M월 d일"/>			
				</div>
			</div>
		</div>
	</div>
	<div class="comment-result">${cmt.content}</div>
	<div class="comment-control">
		<c:if test="${sessionScope.userid == cmt.writer}">
			<a onclick="deleteCommentSubmit('${cmt.cmt_idx}',${cmt.c_idx})">삭제</a>
		</c:if>
	</div>
	
</c:forEach>
