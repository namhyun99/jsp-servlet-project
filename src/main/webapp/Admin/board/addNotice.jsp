<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<h2>공지사항 글 작성</h2>
	</div>
	<form method="post" name="detailForm">
		<table> 
			<colgroup>
            	<col width="20%">
            	<col width="80%">
            </colgroup>
			
			<tr>
				<td>제목</td>
				<td class="input-box-underline">
					<input id="title" name="title" placeholder="제목을 입력하세요">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea id="content" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" name="userid" value="${sessionScope.userid}">
					<input type="button" id="btnAdd" value="게시글 작성">			
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
$(function(){
	$("#btnAdd").click(function(){
		writeNoticeSubmit(detailForm);
	});
})
</script>