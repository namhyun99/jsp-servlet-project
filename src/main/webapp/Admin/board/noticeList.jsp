<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="contents-wrap">
	<div class="main-wrap">
		<div class="main-top">
			<div class="nav-bar">
				<span>게시판관리 > 공지사항 관리</span>
			</div>
			<div class="title-search">
				<div class="title">
					<h2>공지사항 목록<span>(${count})</span></h2>
				</div>
				<div class="search-order">
					<div class="order-wrap">
						<form method="get" id="orderForm" name="orderForm">
							<select name="order" id="order" onchange="orderSelect(orderForm, '${order}','${searchkey}', '${keyword}')">
								<option value="write_date"
									<c:if test="${order=='write_date'}">selected</c:if>>작성일순</option>
								<option value="view_cnt"
									<c:if test="${order=='view_cnt'}">selected</c:if>>조회수높은순</option>
							</select>
						</form>
					</div>
					<div class="search-wrap">
						<form method="post" id="searchForm" name="searchForm" action="${url}">
							<select name="searchkey" id="searchkey">
								<option value="title" <c:if test="${searchkey=='title'}">selected</c:if>>제목</option>
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
						<th style="width: 50%">제목</th>
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
									<td>${dto.rn}</td>
									<td><a href="${path}/admin/board/editNotice?f_idx=${dto.f_idx}">${dto.title}</a></td>
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
			<a href="${path}/admin/board/addNotice">
				<img src="${path}/Admin/resources/asset/images/btn_add.png" alt="추가하기">
			</a>
		</div>
		
		<!-- 페이징 -->
		<%@ include file="../page.jsp" %>
		
	</div>
</div>