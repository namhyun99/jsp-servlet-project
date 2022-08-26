<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(function() {
		//쿠키값 확인
		var cookie_userid = getCookie("userid");

		if (cookie_userid != "") {
			$("#userid").val(cookie_userid);
			$("#chkSave").attr("checked", 'checked');
		}

		//엔터키를 입력하면 
		$("#passwd").keydown(function(e) {
			if (e.keyCode == 13) {
				if (LoginCheck()) {
					ajaxLogin('${path}');
				}
			}
		})
	})
</script>

<div id="login-wrap">
	<div class="login-box">
		<div class="title-wrap">
			<div class="title">
				<span>LOG IN</span>
			</div>
			<div class="subtitle">
				<span>Administration</span>
			</div>
		</div>

		<div class="form-wrap">
			<div class="input-wrap">
				<div class="input-id-box">
					<input name="userid" id="userid" placeholder="아이디를 입력하세요"
						autocomplete="off">
				</div>
				<div class="input-pwd-box">
					<input type="password" name="passwd" id="passwd"
						placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			<div class="info-wrap">
				<div class="save-box">
					<div>
						<input type="checkbox" id="chkSave">
					</div>
					<div>
						<span>아이디저장</span>
					</div>
				</div>
				<div class="remind-box">
					<a href="#" id="remindIdPw"><span>ID/PW찾기</span></a>
				</div>
			</div>
			<div class="button-wrap">
				<input type="button" id="btnLogin" value="로그인">
			</div>
		</div>
	</div>
</div>

</body>
</html>