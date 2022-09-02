<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<c:choose>
			<c:when test="${param.board_no == 1}">
				<h2>공지사항 수정</h2>
			</c:when>
			<c:when test="${param.board_no == 2}">
				<h2>faq 수정</h2>
			</c:when>
			<c:when test="${param.board_no == 5}">
				<h2>컨텐츠 수정</h2>
			</c:when>
		</c:choose>
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
				<td>게시판이름</td>
				<td class="txt_item">
					<span id="write_date">${bdTitle}</span>
				</td>
				<td>조회수</td>
				<td class="txt_item">
					<span>${dto.view_cnt}</span>
				</td>
			</tr>
			<tr class="outline">
				<td>작성일</td>
				<td class="txt_item">
					<span><fmt:formatDate value="${dto.write_date}" pattern="yyyy-MM-dd(E) HH:mm:ss"/></span>
				</td>
				<td>댓글수</td>
				<td class="txt_item">
					<span>${dto.cmt_count}</span>
				</td>
			</tr>
			<tr class="outline">
				<td>마지막 수정일</td>
				<td class="txt_item">
					<span><fmt:formatDate value="${dto.update_date}" pattern="yyyy-MM-dd(E) HH:mm:ss"/></span>
				</td>
				<td>공개여부</td>
				<td class="txt_item">
					<select name="show">
						<option value="y" <c:if test="${dto.show == y}">selected</c:if>>공개</option>
						<option value="n" <c:if test="${dto.show == n}">selected</c:if>>비공개</option>
					</select>
				</td>
			</tr>
			<tr class="outline">
				<td>썸네일수정</td>
				<td class="file-box">
					<input type="file" name="filename">
					<c:if test="${dto.filesize > 0}">
						<br><input type="checkbox" name="fileDel">썸네일 삭제 (${dto.filename})
					</c:if>
				</td>
				<td>미리보기</td>
				<c:choose>
					<c:when test="${dto.filesize > 0}">
						<td class="thumb"><img src="${path}/resources/static/upload/${dto.filename}"></td>
					</c:when>
					<c:otherwise>
						<td class="thumb"><img src="${path}/resources/static/images/no_thumb.png"></td>
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
					<input type="hidden" name="board_no" value="${dto.board_no}">
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
		deleteBoardSubmit(detailForm);
	});
	
	$("#btnSave").click(function(){
		editBoardSubmit(detailForm);
	})

	$("#content").summernote({
		height : 300,
		minHeight : null,
		maxHeight : null,
		tabsize: 2,
		focus : true,
		lang : "ko-KR",
		toolbar: [
		    // [groupName, [list of button]]
		    ['fontname', ['fontname']],
		    ['fontsize', ['fontsize']],
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    ['color', ['forecolor','color']],
		    ['table', ['table']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['insert', ['link']],
		    ['view', ['codeview']]
		  ],
		fontNames: ['맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36']
	});
})
</script>