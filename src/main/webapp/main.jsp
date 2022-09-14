<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="./include/header.jsp"%>
<%@ include file="./include/top.jsp"%>
<c:if test="${param.msg != null}">
	<script>
		alert('${param.msg}');
	</script>
</c:if>

<!--contents-->
<div id="contents-wrap" class="screen">
	<nav id="nav">
		<div class="option-wrap">
			<span class="title"> 
				<a href="${path}/main.do" class="selected"> 
					<i class="fas fa-chart-line"></i> 트렌딩
				</a>
			</span> 
			<span> 
				<a href="${path}/recent" class="noselected"> 
					<i class="far fa-clock"></i> 최신
				</a>
			</span> 
		</div>
	</nav>

	<section id="list-wrap">
		<c:forEach var="dto" items="${list}">
			<div class="items">
				<a href="${path}/board/view?c_idx=${dto.c_idx}">
					<div class="thumb_img">
						<c:choose>
							<c:when test="${dto.filesize > 0}">
								<img src="${path}/upload/content/${dto.filename}">
							</c:when>
							<c:otherwise>
								<img src="${path}/resources/asset/images/no_thumb.png">
							</c:otherwise>
						</c:choose>
					</div>
				</a>
				<div class="text-wrap">
					<a href="${path}/board/view?c_idx=${dto.c_idx}">
						<div class="contents">
							<h3>${dto.subject}</h3>
							<!-- <div class="desc">${dto.content}</div> -->
						</div>
					</a>
					<div class="sub-info">
						<span>
						<fmt:formatDate value="${dto.write_date}" pattern="yyyy년 M월 d일"/>
						</span> 
						<span> · ${dto.cmt_count}개의 	댓글</span>
					</div>
					<div class="user-info">
						<div class="profile">
							<c:choose>
								<c:when test="${dto.profile_img != '-'}">
									<img src="${path}/upload/profile/${dto.profile_img}">
								</c:when>
								<c:otherwise>
									<img src="${path}/resources/asset/images/no_profile.png">
								</c:otherwise>
							</c:choose>
							by.<span>${dto.userid}</span>
						</div>
						<div class="view-count">
							<i class="fas fa-eye"></i><span>${dto.view_cnt}</span>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</section>
	
	<%@ include file="../include/page.jsp" %>
</div>

</body>
</html>
