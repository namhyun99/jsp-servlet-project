<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			<span class="writer">${dto.userid}</span> <span class="separator">·</span>
			<span class="writer-date"> <fmt:formatDate
					value="${dto.write_date}" pattern="yyyy년 M월 d일" />
			</span>
		</div>

		<article class="contents">
			<c:if test="${dto.filesize > 0}">
				<img src="${path}/upload/content/${dto.filename}" width="100%">
			</c:if>
		${dto.content}
		</article>

		<div class="other">
			<div class="other-title">
				<h2>관심 있을 만한 포스트</h2>
			</div>
			<div class="items-list">
				<div class="items">
					<img src="./resources/asset/images/thumb1.jpg">
					<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
						Ipsum, officiis.</p>
				</div>
				<div class="items">
					<img src="./resources/asset/images/thumb2.jpg">
					<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
						Ipsum, officiis.</p>
				</div>
				<div class="items">
					<img src="./resources/asset/images/thumb3.jpg">
					<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
						Ipsum, officiis.</p>
				</div>
				<div class="items">
					<img src="./resources/asset/images/thumb4.jpg">
					<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
						Ipsum, officiis.</p>
				</div>
				<div class="items">
					<img src="./resources/asset/images/thumb5.jpg">
					<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
						Ipsum, officiis.</p>
				</div>
				<div class="items">
					<img src="./resources/asset/images/thumb6.jpg">
					<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
						Ipsum, officiis.</p>
				</div>
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
		<img src="${path}/resources/asset/images/arrow_up.png">
	</div>

</div>
</body>

</html>