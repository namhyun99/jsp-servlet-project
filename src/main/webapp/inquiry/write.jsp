<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/header.jsp"%>
<%@ include file="../include/no_session.jsp" %>

<div id="contents-wrap" class="screen">
	<div class="btn-back-icon">
		<img src="${path}/resources/asset/images/btn_exit.png" onclick="history.back()">
	</div>
	<div id="contents-write-wrap">
		<div class="title" style="display:none;">
			<h2>문의하기</h2>
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
						<input type="button" id="btnAdd" value="문의 작성" onclick="insertInquirySubmit(detailForm)">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" id="btnList" value="다른 문의 목록 보기" onclick="location.href='${path}/board/inquiry'">
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
					'20', '22', '24', '28', '30', '36' ],
					
		callbacks: {
			onImageUpload : function(files, editor, welEditable){
				sendFile(files[0], this);
			}
		}
	});
})
</script>


</body>
</html>