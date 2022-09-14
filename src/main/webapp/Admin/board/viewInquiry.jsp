<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<h2>1:1 문의글 보기</h2>
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
					<span>${dto.i_idx}</span>
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
				<td>답변여부</td>
				<td>
					<c:if test="${dto.complete == 'n'}">답변전</c:if>
					<c:if test="${dto.complete == 'y'}">답변완료</c:if>
				</td>
			</tr>
			<tr class="outline">
				<td>제목</td>
				<td colspan="3">
					${dto.title}
				</td>
			</tr>
			<tr class="outline">
				<td>문의 내용 </td>
				<td colspan="3">
					${dto.content}
				</td>
			</tr>
			<tr >
				<td colspan="4">
  					<input type="hidden" name="i_idx" value="${dto.i_idx}">
				    <c:if test="${dto.complete == 'n' && dto.re_step == 1 }">
				    	<input type="button" id="btnReply" value="문의 답변">		
				    </c:if>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="button" id="btnDelete" value="문의 삭제">
				</td>
			</tr>		
		</table>
	</form>
</div>

<script type="text/javascript">
$(function(){
	$("#btnDelete").click(function(){
		deleteInquirySubmit(detailForm);
	});
	
	$("#btnReply").click(function(){
		replyInquirySubmit(detailForm);
	});
	
	$("#btnReplyEdit").click(function(){
		replyInquirySubmit(detailForm);
	})

})
</script>