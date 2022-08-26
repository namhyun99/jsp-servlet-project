<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="contents-wrap">
	<div class="main-wrap">
		<div class="main-top">
			<div class="nav-bar">
				<span>회원관리 > 회원목록</span>
			</div>
			<div class="title-search">
				<div class="title">
					<h2>
						회원목록<span>(${count})</span>
					</h2>
				</div>
				<div class="search-order">
					<div class="order-wrap">
						<form id="orderForm" name="orderForm" action="${url}">
							<select name="order" id="order" onchange="orderSelect(orderForm)">
								<option value="name"
									<c:if test="${order=='name'}">selected</c:if>>이름순</option>
								<option value="join_date"
									<c:if test="${order=='join_date'}">selected</c:if>>가입일순</option>
							</select>
						</form>
					</div>
					<div class="search-wrap">
						<form id="searchForm" name="searchForm" action="${url}">
							<select name="searchkey" id="searchkey">
								<option value="">선택</option>
								<option value="userid">아이디</option>
								<option value="name">이름</option>
								<option value="email">이메일</option>
								<option value="phone">연락처</option>
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
<%-- 		<h2>${list}</h2> --%>
		<div class="main-content">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>이메일</th>
						<th>연락처</th>
						<th>가입일자</th>
						<th>권한</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${list == ' '}">
							<tr>
								<td>검색 결과가 없습니다</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="dto" items="${list}">
								<tr id="resutl">
									<td>${dto.m_idx}</td>
									<td>${dto.userid}</td>
									<td>${dto.name}</td>
									<td>${dto.email}</td>
									<td>${dto.phone}</td>
									<td>${dto.join_date}</td>
									<td>${dto.authority}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</tbody>
			</table>
		</div>

		<div class="pagination">
			<ul>
				<c:if test="${page.curPage > 1}">
					<li><a href="#" onclick="pagination('1','${order}','${searchkey}','${keyword}')"><span>[처음]</span></a></li>
				</c:if>
				<c:if test="${page.curBlock > 1}">
					<li><a href="#"
						onclick="pagination('${page.prevPage}','${order}','${searchkey}','${keyword}')"><span>[이전]</span></a></li>
				</c:if>
				<c:forEach var="num" begin="${page.blockStart}"
					end="${page.blockEnd}">
					<c:choose>
						<c:when test="${num == page.curPage }">
							<span class="now-page">[${num}]</span>
						</c:when>
						<c:otherwise>
							<li><a href="#" onclick="pagination('${num}','${order}','${searchkey}','${keyword}')"><span>[${num}]</span></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${page.curBlock < page.totBlock}">
					<li><a href="#"
						onclick="pagination('${page.nextPage}','${order}','${searchkey}','${keyword}')"><span>[다음]</span></a></li>
				</c:if>
				<c:if test="${page.curPage < page.totPage}">
					<li><a href="#"
						onclick="pagination('${page.totPage}','${order}','${searchkey}','${keyword}')"><span>[끝]</span></a></li>
				</c:if>
			</ul>
		</div>

	</div>
</div>