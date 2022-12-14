<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<h2>컨텐츠 글 작성</h2>
	</div>
	<form method="post" name="detailForm" enctype="multipart/form-data">
		<table> 
			<colgroup>
            	<col width="20%">
            	<col width="80%">
            </colgroup>
			<tr>
				<td>카테고리 선택</td>
				<td colspan="1" class="input-box-noborder">
					<select id="cate_no" name="cate_no">
						<option value="">선택</option>
						<c:forEach var="c" items="${cateList}">
							<option value="${c.cate_no}">${c.cate_name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td class="input-box-underline">
					<input id="subject" name="subject" placeholder="제목을 입력하세요">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea id="content" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td>썸네일등록</td>
				<td class="input-box-noborder">
					<input type="file" id="filename" name="filename" onchange="uploadFileCheck()">
				</td>
			</tr>	
			<tr>
				<td>게시글 공개여부</td>
				<td class="input-box-noborder">
					<input type="radio" id="show" name="show" value="y">공개 
					<input type="radio" id="show" name="show" value="n">비공개
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
		writeContentsSubmit(detailForm);
	});
})
</script>