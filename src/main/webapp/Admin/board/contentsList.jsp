<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="contents-wrap">
	<div class="main-wrap">
		<div class="main-top">
			<div class="nav-bar">
				<span>게시판관리 > 컨텐츠 목록</span>
			</div>
			<div class="title-search">
				<div class="title">
					<h2>컨텐츠 목록<span>(${count})</span></h2>
				</div>
				<div class="search-order">
					<div class="order-wrap">
						<form method="get" id="orderForm" name="orderForm">
							<select name="order" id="order" onchange="orderSelect(orderForm, '${order}','${op}', '${keyword}')">
								<option value="p_idx"
									<c:if test="${order=='p_idx'}">selected</c:if>>번호순</option>
								<option value="view_cnt"
									<c:if test="${order=='view_cnt'}">selected</c:if>>조회순</option>
								<option value="write_date"
									<c:if test="${order=='write_date'}">selected</c:if>>작성일순</option>
							</select>
						</form>
					</div>
					<div class="search-wrap">
						<form method="post" id="searchForm" name="searchForm" action="${url}">
							<select name="op" id="op">
								<option value="subject" <c:if test="${op=='subject'}">selected</c:if>>제목</option>
								<option value="m_idx" <c:if test="${op=='m_idx'}">selected</c:if>>작성자</option>
							</select>
							<div class="search-input">
								<input name="keyword" id="keyword" onkeypress="javascript:if(event.keyCode==13) listSearch(searchForm)">
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
						<th>썸네일</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일자</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${count > 0}">
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>${dto.p_idx}</td>
									<td><img src="${path}/upload/${filename}"></td>
									<td><a href="#">${dto.subject}</a></td>
									<td>${dto.userid}</td>
									<td>${dto.view_cnt}</td>
									<td><fmt:formatDate value="${dto.write_date}" pattern="yyyy-MM-dd"/></td>
								</tr>
								
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="7">
								<span class="keyword">'${keyword}'</span> 에 대한 검색 결과가 없습니다
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<!-- 버튼 -->
		<div class="footer-btn-wrap">
			<a href="#">
				<img src="${path}/Admin/resources/asset/images/btn_add.png" alt="추가하기">
			</a>
		</div>
		
		<!-- 페이징 -->
		<%@ include file="../page.jsp" %>
		
	</div>
</div>