<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<h2>1:1 문의 답글쓰기</h2>
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
				<td>답변여부</td>
				<td class="txt_item">
					<select name="complete">
						<option value="n" <c:if test="${dto.complete == 'n'}">selected</c:if>>답변전</option>
						<option value="y" <c:if test="${dto.complete == 'y'}">selected</c:if>>답변완료</option>
					</select>
				</td>
			</tr>
			<tr class="outline">
				<td>공개여부</td>
				<td class="txt_item">
					<select name="show">
						<option value="y" <c:if test="${dto.show == 'y'}">selected</c:if>>공개</option>
						<option value="n" <c:if test="${dto.show == 'n'}">selected</c:if>>비공개</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3" class="input-box-underline">
					<input id="title" name="title" value="RE : ${dto.title}">
				</td>
			</tr>
			<tr class="outline">
				<td colspan="4">
					<textarea id="content" name="content">${dto.content}</textarea>
				</td>
			</tr>
			<tr >
				<td colspan="4">
					<input type="hidden" name="i_idx" value="${dto.i_idx}">
					<input type="hidden" name="userid" value="${sessionScope.userid}">
					<input type="button" id="btnWrtieReply" value="답변작성">		
				</td>
			</tr>	
		</table>
	</form>
</div>

<script type="text/javascript">
$(function(){

	$("#btnWrtieReply").click(function(){
		writeReplyInquirySubmit(detailForm);
	})

})
</script>