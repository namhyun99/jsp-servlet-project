<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="contents-wrap">
	<div class="main-wrap">
		<div class="main-top">
			<div class="nav-bar">
				<span>게시판관리 > 1:1문의관리</span>
			</div>
			<div class="title-search">
				<div class="title">
					<h1>${keyword}</h1>
					<h2>1:1문의 목록<span>(${count})</span></h2>
				</div>
				<div class="search-order">
				<div class="order-wrap">
						<form name="orderForm">
							<select name="order" id="order" 
							onchange="orderSelect(orderForm, '${order}','${searchkey}', '${keyword}')">
								<option value="all" selected> 전체 </option>
								<option value="n"
									<c:if test="${order=='n'}">selected</c:if>>답변전</option>
								<option value="y"
									<c:if test="${order=='y'}">selected</c:if>>답변완료</option>
							</select>
						</form>
					</div>
					<div class="search-wrap">
						<form name="searchForm">
							<div class="search-input">
								<input name="keyword" id="keyword" placeholder="검색어를 입력하세요"
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
									<td><a href="${path}/admin/board/viewInquiry?i_idx=${dto.i_idx}">${dto.title}</a></td>
									<td>${dto.userid}</td>
									<td>${dto.view_cnt}</td>
									<td><fmt:formatDate value="${dto.write_date}" pattern="yyyy-MM-dd"/></td>
									<td>
										<c:if test="${dto.complete == 'y'}">답변완료</c:if>
										<c:if test="${dto.complete == 'n'}">미답변</c:if>
									</td>
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
				
		<!-- 페이징 -->
		<%@ include file="../page.jsp" %>
		
	</div>
</div>