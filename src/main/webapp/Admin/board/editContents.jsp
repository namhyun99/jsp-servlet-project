<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<h2>컨텐츠 글 수정</h2>
	</div>
	<form method="post" name="detailForm" enctype="multipart/form-data">
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
					<span>${dto.c_idx}</span>
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
				<td>카테고리</td>
				<td class="txt_item">
				    <select name="cate_no">
				    	<c:forEach var="c" items="${cateList}">
				    	<option value="${c.cate_no}" <c:if test="${c.cate_no == dto.cate_no}">selected</c:if>>${c.cate_name}</option>
				    	</c:forEach>
				    </select>
				</td>
				<td>공개여부</td>
				<td class="txt_item">
					<select name="show">
						<option value="y" <c:if test="${dto.show == 'y'}">selected</c:if>>공개</option>
						<option value="n" <c:if test="${dto.show == 'n'}">selected</c:if>>비공개</option>
					</select>
				</td>
			</tr>
			<tr class="outline">
				<td>썸네일수정</td>
				<td class="file-box">
					<input type="file" id="filename" name="filename" onchange="uploadFileCheck()">
				</td>
				<td>미리보기</td>
				<c:choose>
					<c:when test="${dto.filesize > 0}">
						<td class="thumb">
							<img src="/thumbnail/${dto.filename}">
							<input type="checkbox" name="fileDel">썸네일 삭제
						</td>
					</c:when>
					<c:otherwise>
						<td class="thumb">
							<img src="${path}/Admin/resources/asset/images/no_thumb.png">
							업로드된 썸네일 없음
						</td>
					</c:otherwise>
				</c:choose>
			</tr>	
			<tr>
				<td>제목</td>
				<td colspan="3" class="txt_item_input">
					<input id="subject" name="subject" value="${dto.subject}" placeholder="제목을 입력해주세요.">
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea id="content" name="content">${dto.content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" name="c_idx" value="${dto.c_idx}">
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
		deleteContentsSubmit(detailForm);
	});
	
	$("#btnSave").click(function(){
		editContentsSubmit(detailForm);
	})
})
</script>