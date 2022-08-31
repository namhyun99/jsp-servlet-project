<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="pagination">
	<ul>
		<!-- 이전 -->
		<c:if test="${page.prevPage > 0}">
			<li><a href="#"
				onclick="pagination('${page.prevPage}','${order}','${op}','${keyword}')"><span><i
						class="fas fa-angle-left"></i></span></a></li>
		</c:if>
		<!-- 번호 -->
		<c:forEach var="num" begin="${page.blockStart}" end="${page.blockEnd}">
			<c:choose>
				<c:when test="${num == page.curPage }">
					<li class="now-page"><span>${num}</span></li>
				</c:when>
				<c:otherwise>
					<li><a href="#"
						onclick="pagination('${num}','${order}','${op}','${keyword}')"><span>${num}</span></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 다음 -->
		<c:if test="${page.nextPage < page.totPage}">
			<li><a href="#"
				onclick="pagination('${page.nextPage}','${order}','${op}','${keyword}')"><span><i
						class="fas fa-angle-right"></i></span></a></li>
		</c:if>
	</ul>
</div>