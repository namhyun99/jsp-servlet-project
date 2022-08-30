<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet" type="text/css" href="../Admin/resources/asset/css/summernote-lite.css">

<script type="text/javascript" src="../Admin/resources/asset/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../Admin/resources/asset/js/summernote-lite.js"></script>
<script type="text/javascript" src="../Admin/resources/asset/js/summernote-ko-KR.js"></script>

<title>데이터 입력 테스트</title>

<script type="text/javascript">
$(function() {
	$("#summernote").summernote({
		height : 250,
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
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36']
	});
		
});


function joinCheck(f){
	var userid = $("#userid").val();
	
	$.ajax({
		type : "post",
		url : "${path}/pageTest/idCheck.do",
		data : {"userid" : userid},
		success : function(data){
			if(data == 'false'){
				alert("중복된 아이디 입니다.");
			} else { 
				f.submit();
			}
		}
	});
}
</script>

</head>
<body>

	<h2>회원등록</h2>
	<form method="post" id="joinForm" action="${path}/pageTest/join.do" enctype="multipart/form-data">
		아이디 <input id="userid" name="userid"> <br> 
		비밀번호 <input name="passwd"> <br> 
		이름 <input name="name"> <br> 
		이메일 <input name="email"><br> 
		전화번호 <input name="phone"><br>
		프로필이미지 <input type="file" name="profile_img"> <br> 
		<input type="checkbox" name="consent" value="Y">이용약관 동의 
		<input type="checkbox" name="privacy" value="Y">개인정보약관 동의 <br> 
		
		<input type="radio" name="authority" value="사용자">사용자 
		<input type="radio" name="authority" value="관리자">관리자 <br> 
		
		<br>
		<button type="button" onclick="joinCheck(joinForm)">가입하기</button>
	</form>

	<br>
	<hr>
	
	<h2>게시판 생성</h2>
	<form method="post" action="${path}/pageTest/insertBoard.do">
		게시판 이름 <input name="title"> <br> 
		게시판 설명 <input name="sub_title"><br><br>
		<button>저장</button>
	</form>


	<hr>
	<h2>컨텐츠 작성</h2>
	<form method="post" action="${path}/pageTest/writer.do"	enctype="multipart/form-data">
		카테고리 선택 <select name="cate">
			<option>선택하기</option>
			<option value="기부하기">기부하기</option>
			<option value="기부중">기부중</option>
		</select> 
		
		제목 <input name="subject"> <br> 
		내용
		<textarea name="content" id="summernote"></textarea>
		<input type="file" name="thumb"><br><br>
		<button>저장</button>
	</form>

	<br><hr>
	<br>
</body>
</html>

