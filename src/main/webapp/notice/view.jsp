<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/header.jsp"%>
<%@ include file="../include/top.jsp"%>

<c:if test="${sessionScope.userid != dto.userid}">
	<script>
		
	</script>
</c:if>

<div id="contents-wrap" class="screen">
	<div id="contents-view-wrap">
		<div class="subject-wrap">
			<h1 class="title">${dto.title}</h1>
		</div>

		<div class="info">
			<span class="writer">${dto.userid}</span> 
			<span class="separator">·</span>
			<span class="writer-date"> 
			<fmt:formatDate	value="${dto.write_date}" pattern="yyyy년 M월 d일(E) HH:mm:ss" />
			</span>
		</div>

		<article class="contents">
		${dto.content}
		</article>

		<div class="other">
			<button onclick="location.href='${path}/board/notice'">목록보기</button>
		</div>
		

	</div>
	<div class="btn-back_to_top">
		<img src="${path}/resources/asset/images/btn_up.png">
	</div>
</div>
</body>

</html>