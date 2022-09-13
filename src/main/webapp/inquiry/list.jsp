<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/header.jsp"%>
<%@ include file="../include/no_session.jsp"%>
<%@ include file="../include/top.jsp"%>

<div id="contents-wrap" class="screen">
	<div id="board-list-wrap">
		<div class="main-top">
			<div class="title-search">
				<div class="title">
					<h2>
						문의목록<span>(${count})</span>
					</h2>
				</div>
				<div class="search-order">
					<div class="search-wrap">
						<form method="post" id="searchForm" name="searchForm"
							action="${url}">
							<select name="searchkey" id="searchkey">
								<option value="title"
									<c:if test="${searchkey=='title'}">selected</c:if>>제목</option>
							</select>
							<div class="search-input">
								<input name="keyword" id="keyword"
									onkeypress="javascript:if(event.keyCode==13) listSearch(searchForm)">
							</div>
							<div class="search-btn">
								<input type="button" value="검색" onclick="listSearch(searchForm)">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="main-content">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th style="width: 50%">제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일자</th>
						<th>답변여부</th>
					</tr>
				</thead>

				<tbody>
					<c:choose>
						<c:when test="${count > 0}">
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>${dto.rn}</td>
								    <td><a href="${path}/board/inquiry?i_idx=${dto.i_idx}">${dto.title}</a></td>
									<td>${dto.userid}</td>
									<td>${dto.view_cnt}</td>
									<td><fmt:formatDate value="${dto.write_date}"
											pattern="yyyy-MM-dd" /></td>
									<td>
										<c:if test="${dto.complete == 'y'}">답변완료</c:if>
										<c:if test="${dto.complete == 'n'}">미답변</c:if>
									</td>
								</tr>

							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="7"><span class="keyword">'${keyword}'</span> 에
									대한 검색 결과가 없습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<!-- 페이징 -->
		<%@ include file="../include/page.jsp"%>
	</div>
</div>
</body>
</html>
