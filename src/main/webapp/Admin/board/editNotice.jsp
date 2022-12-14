<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<h2>공지사항 글 수정</h2>
	</div>
	<form method="post" name="detailForm">
		<table>
			<colgroup>
            	<col width="15%">
            	<col width="35%">
            	<col width="15%">
            	<col width="35%">
            </colgroup>
			<tr class="outline">
				<td>글번호</td>
				<td class="txt_item">
					<span>${dto.f_idx}</span>
				</td>
				<td>작성자</td>
				<td class="txt_item">
					<span>${dto.userid}</span>
				</td>
			</tr>
			<tr class="outline">
				<td>작성일</td>
				<td class="txt_item">
					<span><fmt:formatDate value="${dto.write_date}" pattern="yyyy-MM-dd(E) HH:mm:ss"/></span>
				</td>
				<td>마지막 수정일</td>
				<td class="txt_item">
					<span><fmt:formatDate value="${dto.update_date}" pattern="yyyy-MM-dd(E) HH:mm:ss"/></span>
				</td>
			</tr>
			<tr class="outline">
				<td>조회수</td>
				<td class="txt_item">
					<span>${dto.view_cnt}</span>
				</td>
				
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3" class="txt_item_input">
					<input id="title" name="title" value="${dto.title}" placeholder="제목을 입력해주세요.">
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea id="content" name="content">${dto.content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" name="f_idx" value="${dto.f_idx}">
					<input type="button" id="btnSave" value="게시글 수정">		
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="button" id="btnDelete" value="게시글삭제">
				</td>
			</tr>		
		</table>
	</form>
</div>

<script type="text/javascript">
$(function(){
	$("#btnDelete").click(function(){
		deleteNoticeSubmit(detailForm);
	});
	
	$("#btnSave").click(function(){
		editNoticeSubmit(detailForm);
	})
})
</script>