<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div id="contents-wrap" class="screen">
	<div class="btn-back-icon">
		<img src="${path}/resources/asset/images/btn_exit.png" onclick="history.back()">
	</div>
	<div id="contents-edit-wrap">
		<div class="title" style="display:none;">
			<h2>글 수정</h2>
		</div>
		<form method="post" name="detailForm" enctype="multipart/form-data">
			<table>
				<colgroup>
	            	<col width="20%">
	            	<col width="30%">
	            	<col width="20%">
	            	<col width="30%">
            	</colgroup>
				<tr>
					<td>카테고리</td>
					<td colspan="1" class="input-box-noborder">
						<select name="cate_no">
					    	<c:forEach var="c" items="${cateList}">
					    	<option value="${c.cate_no}" <c:if test="${c.cate_no == dto.cate_no}">selected</c:if>>${c.cate_name}</option>
					    	</c:forEach>
				    	</select>
					</td>
					<td>공개여부</td>
					<td>
						<select name="show">
							<option value="y" <c:if test="${dto.show == 'y'}">selected</c:if>>공개</option>
							<option value="n" <c:if test="${dto.show == 'n'}">selected</c:if>>비공개</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3" class="input-box-underline">
						<input id="subject"	name="subject" value="${dto.subject}">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea id="content" name="content">${dto.content}</textarea>
					</td>
				</tr>
				<tr>
					<td>썸네일등록</td>
					<td class="input-box-noborder">
						<input type="file" id="filename" name="filename" onchange="uploadFileCheck()">
					</td>
					<c:choose>
						<c:when test="${dto.filesize > 0}">
							<td colspan="2" class="thumb">
								<img src="${path}/upload/content/${dto.filename}">
								<input type="checkbox" name="fileDel">썸네일 삭제
							</td>
						</c:when>
						<c:otherwise>
							<td colspan="2" class="thumb">
								<img src="${path}/resources/asset/images/no_thumb.png">
								업로드된 썸네일 없음
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="4">
						<input type="hidden" name="userid" value="${sessionScope.userid}"> 
						<input type="hidden" name="c_idx" value="${dto.c_idx}">
						<input type="button" id="btnEdit" value="게시글 수정" onclick="updateContentsSubmit(detailForm)">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<script>
	$(function() {
		//서머노트 구현
		$("#content").summernote(
		{
			width:1200,
			height : 500,
			minHeight : null,
			maxHeight : null,
			tabsize : 2,
			focus : true,
			lang : "ko-KR",
			toolbar : [
					// [groupName, [list of button]]
					[ 'fontname', [ 'fontname' ] ],
					[ 'fontsize', [ 'fontsize' ] ],
					[ 'style',	[ 'bold', 'italic', 'underline', 'strikethrough', 'clear' ] ],
					[ 'color', [ 'forecolor', 'color' ] ],
					[ 'table', [ 'table' ] ],
					[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
					[ 'height', [ 'height' ] ],
					[ 'insert', [ 'link' ] ],
					[ 'view', [ 'codeview' ] ] ],
			fontNames : [ '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체' ],
			fontSizes : [ '8', '9', '10', '11', '12', '14', '16', '18',
						'20', '22', '24', '28', '30', '36' ]
		});
	})
</script>


</body>
</html>