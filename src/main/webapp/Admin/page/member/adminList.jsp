<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="contents-wrap">
	<div class="main-wrap">
		<div class="main-top">
			<div class="nav-bar">
				<span>회원관리 > 관리자목록</span>
			</div>
			<div class="title-search">
				<div class="title">
					<h2>관리자목록<span>(${count})</span></h2>
				</div>
				<div class="search-order">
					<div class="order-wrap">
						<form method="get" id="orderForm" name="orderForm">
							<select name="order" id="order" onchange="orderSelect(orderForm, '${order}','${op}', '${keyword}')">
								<option value="name"
									<c:if test="${order=='name'}">selected</c:if>>이름순</option>
								<option value="join_date"
									<c:if test="${order=='join_date'}">selected</c:if>>가입일순</option>
							</select>
						</form>
					</div>
					<div class="search-wrap">
						<form method="post" id="searchForm" name="searchForm" action="${url}">
							<select name="op" id="op">
								<option value="userid" <c:if test="${op=='userid'}">selected</c:if>>아이디</option>
								<option value="name" <c:if test="${op=='name'}">selected</c:if>>이름</option>
								<option value="email" <c:if test="${op=='email'}">selected</c:if>>이메일</option>
								<option value="phone" <c:if test="${op=='phone'}">selected</c:if>>연락처</option>
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
						<c:when test="${count > 0}">
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>${dto.m_idx}</td>
									<td><a href="${path}/admin/member/editMember?m_idx=${dto.m_idx}">${dto.userid}</a></td>
									<td>${dto.name}</td>
									<td>${dto.email}</td>
									<td>${dto.phone}</td>
									<td>${dto.join_date}</td>
									<td>${dto.authority}</td>
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
		<div class="pagination">
			<ul>
				<!-- 이전 -->
				<c:if test="${page.prevPage > 0}">
					<li><a href="#"
						onclick="pagination('${page.prevPage}','${order}','${op}','${keyword}')"><span><i class="fas fa-angle-left"></i></span></a></li>
				</c:if>
				<!-- 번호 -->
				<c:forEach var="num" begin="${page.blockStart}"
					end="${page.blockEnd}">
					<c:choose>
						<c:when test="${num == page.curPage }">
							<li class="now-page"><span>${num}</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="#" onclick="pagination('${num}','${order}','${op}','${keyword}')"><span>${num}</span></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 다음 -->
				<c:if test="${page.nextPage < page.totPage}">
					<li><a href="#"
						onclick="pagination('${page.nextPage}','${order}','${op}','${keyword}')"><span><i class="fas fa-angle-right"></i></span></a></li>
				</c:if>
			</ul>
		</div>
		
		<!-- 버튼 -->
		<div class="footer-btn-wrap">
			<a href="${path}/admin/member/addMember">
				<img src="${path}/Admin/resources/asset/images/btn_add.png" alt="추가하기">
			</a>
		</div>
	</div>
</div>