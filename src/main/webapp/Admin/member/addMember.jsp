<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="view-wrap">
	<div class="title">
		<c:choose>
			<c:when test="${dto.authority == '사용자'}">
				<h2>회원 추가</h2>
			</c:when>
			<c:when test="${dto.authority == '관리자'}">
				<h2>관리자 추가</h2>
			</c:when>
		</c:choose>
	</div>
	<form method="post" name="detailForm" enctype="multipart/form-data">
		<table> 
			<tr>
				<td colspan="2" class="thumb"><img src="${path}/Admin/resources/asset/images/no_profile.png"></td>
			</tr>
			<tr>
				<td>권한*</td>
				<td colspan="1" class="input-box-noborder">
					<input type="radio" name="authority" value="사용자">사용자 
					<input type="radio" name="authority" value="관리자">관리자
				</td>
			</tr>		
			<tr>
				<td>아이디*</td>
				<td class="input-box-underline">
					<input id="userid" name="userid" placeholder="아이디를 입력해주세요." autocomplete="off" onkeyup="useridDuplicateCheck()">
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="error-msg userid"></td>
			</tr>
			<tr>
				<td>비밀번호*</td>
				<td class="input-box-underline">
					<input type="password" id="passwd" name="passwd" placeholder="비밀번호를 입력해주세요." autocomplete="off">
				</td>
			</tr>
			<tr>
				<td>비밀번호 체크*</td>
				<td class="input-box-underline">
					<input type="password" id="passwd2" placeholder="비밀번호를 다시 입력해주세요."  autoComplete="off" onkeyup="passwdDuplicateCheck()">
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="error-msg passwd"></td>
			</tr>
			<tr>
				<td>이름*</td>
				<td class="input-box-underline">
					<input id="name" name="name" placeholder="이름을 입력해주세요" >
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="error-msg name"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td class="input-box-underline-email">
					<input id="email1" name="email1" placeholder="앞자리">
					<span class="link">@</span>
					<input id="email2" name="email2" placeholder="직접입력">
					<select name="emailSelect" onchange="e_select(this)">
						<option value="">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="google.com">google.com</option>
						<option value="daum.net">daum.net</option>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="error-msg email"></td>
			</tr>
			<tr>
				<td>연락처</td>
				<td class="input-box-underline-phone">
					<select id="phone1" name="phone1">
						<option value="">선택</option>
						<option value="010">010</option>
						<option value="011">011</option>
					</select>
					<span class="link">-</span>
					<input id="phone2" name="phone2" maxlength="4" placeholder="중간번호">
					<span class="link">-</span>
					<input id="phone3" name="phone3" maxlength="4" placeholder="끝번호">
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="error-msg phone"></td>
			</tr>
			<tr>
				<td>썸네일등록</td>
				<td class="input-box-noborder">
					<input type="file" id="filename" name="profile_img" onchange="uploadFileCheck()">
				</td>
			</tr>	
			<tr>
				<td colspan="2">
					<input type="hidden" name="consent" value="Y">
					<input type="hidden" name="privacy" value="Y">
					<input type="button" id="btnAdd" value="멤버추가">			
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
$(function(){
	$("#btnAdd").click(function(){
		memberJoinSubmit(detailForm);
	});	
})

//이메일 셀렉
function e_select(obj){
	$("[name=email2]").val(obj.value);	
}

</script>