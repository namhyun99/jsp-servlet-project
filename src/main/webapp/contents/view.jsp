<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/header.jsp"%>
<%@ include file="../include/top.jsp"%>

<script>
	$(function(){
		showCommentList('${dto.c_idx}');
	})
</script>

<div id="contents-wrap" class="screen">
	<div id="contents-view-wrap">
		<div class="subject-wrap">
			<h1 class="title">${dto.subject}</h1>
		</div>

		<div class="info">
			<span class="writer">${dto.userid}</span> 
			<span class="separator">·</span>
			<span class="cate-name">${dto.cate_name}</span>
			<span class="separator">·</span>
			<span class="writer-date"> 
			<fmt:formatDate	value="${dto.write_date}" pattern="yyyy년 M월 d일" />
			</span>
			<c:if test="${sessionScope.userid == dto.userid}">
				<span class="separator">·</span>
				<div class="control-btn">
					<a href="${path}/board/edit?c_idx=${dto.c_idx}">수정</a>
					<span>/</span>
					<a onclick="deleteContentsSubmit('${dto.c_idx}')">삭제</a>
				</div>
			</c:if>
		</div>

		<article class="contents">
			<c:if test="${dto.filesize > 0}">
				<img src="/thumbnail/${dto.filename}" width="100%">
			</c:if>
		${dto.content}
		</article>

		<div class="other">
			<div class="other-title">
				<h3>관심 있을 만한 포스트</h3>
			</div>
			<div class="items-list">
			<c:forEach var="otherDto" items="${list}">
				<a href="${path}/board/view?c_idx=${otherDto.c_idx}">
					<div class="items">
						<c:choose>
							<c:when test="${otherDto.filesize > 0}">
								<img src="/thumbnail/${otherDto.filename}">
							</c:when>
							<c:otherwise>
								<img src="../resources/asset/images/no_thumb.png">
							</c:otherwise>
						</c:choose>
						<p>${otherDto.subject}</p>
					</div>
				</a>
			</c:forEach>
			</div>
		</div>
		<div class="comment-wrap">
			<div class="comment-title">
				<h3>${dto.cmt_count}개의 댓글</h3>
			</div>
			<c:choose>
				<c:when test="${sessionScope.userid != null }">
				<div class="cmt-write">
					<textarea placeholder="댓글을 작성해주세요" class="txt-cmt" id="cmt_content" name="content"></textarea>
				</div>
				<div class="btn-cmt">
					<button type="button" onclick="addCommentSubmit('${dto.c_idx}')">댓글 작성</button>
				</div>
				</c:when>
				<c:otherwise>
				<div class="cmt-write">
					<textarea placeholder="댓글은 회원가입후 작성할 수 있습니다." class="txt-cmt" disabled></textarea>
				</div>
				<div class="btn-cmt">
					<button type="button" disabled>댓글 작성</button>
				</div>
				</c:otherwise>
			</c:choose>
			<input type="hidden" id="writer" value="${sessionScope.userid}">
		</div>

		<div class="comment-list">
		</div>
	</div>
	<div class="btn-back_to_top">
		<img src="../resources/asset/images/btn_up.png">
	</div>
</div>
</body>

</html>