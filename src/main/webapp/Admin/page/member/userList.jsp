<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="container">
	<div class="left"></div>
	<div class="main-wrap">
		<div class="main-top">
			<div class="title">
				<h2>회원관리 > 회원리스트</h2>
			</div>
			<div class="search-wrap">
				<div class="select-option">
					<select name="searchkey">
						<option value="">선택</option>
						<option value="">아이디</option>
						<option value="">이름</option>
						<option value="">이메일</option>
						<option value="">연락처</option>
					</select>
				</div>
				<div class="search-input">
					<input type="search" name="search">
				</div>
				<div class="search-btn">
					<input type="button" id="btnSearch" value="검색">
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
					<tr>
						<c:forEach var="dto" items="${list}">
							<td>${dto.m_idx}</td>
							<td>${dto.userid}</td>
							<td>${dto.name}</td>
							<td>${dto.email}</td>
							<td>${dto.phone}</td>
							<td>${dto.join_date}</td>
							<td>${dto.authority}</td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>