<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="content-view-wrap">
	<div class="title">
		<h2>게시판 글 작성</h2>
	</div>
	<form method="post" name="detailForm" enctype="multipart/form-data">
		<table> 
			<colgroup>
            	<col width="20%">
            	<col width="80%">
            </colgroup>
			<tr>
				<td>게시판 선택</td>
				<td colspan="1" class="input-box-noborder">
					<select id="board_no" name="board_no">
						<option value="">선택</option>
						<c:forEach var="b" items="${boardlist}">
							<option value="${b.board_no}">${b.title}</option>
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
					<input type="file" name="filename">
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
		writeBoardSubmit(detailForm);
	});
	
	
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