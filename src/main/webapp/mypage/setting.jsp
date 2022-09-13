<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="../include/header.jsp"%>
<%@ include file="../include/no_session.jsp"%>
<%@ include file="../include/top.jsp"%>

<div id="contents-wrap" class="screen">
	<div id="contents-write-wrap">
		<form method="post" name="detailForm" enctype="multipart/form-data">
			<table>
				<tr>
					<c:choose>
						<c:when test="${dto.profile_img == '-'}">
							<td colspan="2" class="thumb" align="center">
							<img src="${path}/Admin/resources/asset/images/no_profile.png"></td>
						</c:when>
						<c:otherwise>
							<td colspan="2" class="thumb" align="center">
							<img src="${path}/upload/profile/${dto.profile_img}"></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td>아이디</td>
					<td class="input-box"><input id="userid" name="userid"
						value="${dto.userid}" disabled></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td class="input-box"><input type="password" id="passwd"
						name="passwd" value="${dto.passwd}" autocomplete="off"></td>
				</tr>
				<tr>
					<td></td>
					<td class="error-msg passwd"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td class="input-box"><input id="name" name="name"
						value="${dto.name}"></td>
				</tr>
				<tr>
					<td></td>
					<td class="error-msg name"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td class="input-box"><input id="email" name="email"
						value="${dto.email}"></td>
				</tr>
				<tr>
					<td></td>
					<td class="error-msg email"></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td class="input-box"><input id="phone" name="phone"
						value="${dto.phone}"></td>
				</tr>
				<tr>
					<td></td>
					<td class="error-msg phone"></td>
				</tr>
				<tr>
					<td>가입일자</td>
					<td class="input-box"><input id="join_date" name="join_date"
						value="${dto.join_date}" disabled></td>
				</tr>
				<tr>
					<td>프로필사진수정</td>
					<td class="file-box"><input type="file" id="filename"
						name="profile_img" value="${dto.profile_img}"
						onchange="uploadFileCheck()"> <c:if
							test="${dto.profile_img != '-'}">
							<input type="checkbox" name="fileDel">프로필사진 삭제
					</c:if></td>
				</tr>

				<tr>
					<td colspan="2">
					<input type="hidden" name="m_idx" value="${dto.m_idx}"> 
					<input type="hidden" name="userid" value="${dto.userid}">
					<input type="button" id="btnSave" value="회원정보수정"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="btnDelete"
						value="회원탈퇴"></td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		$(function() {
			$("#btnDelete").click(function() {
				memberDeleteSubmit(detailForm);
			});

			$("#btnSave").click(function() {
				memberUpdateSubmit(detailForm);
			})
		})
	</script>
</div>

</body>
</html>




