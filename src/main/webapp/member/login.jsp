<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:if test="${param.msg != null}">
	<script>
		alert('${param.msg}');
	</script>
</c:if>

<div id="contents-wrap" class="screen">
	<div id="login-wrap">
		<div class="login-box">
			<div class="title-wrap">
				<div class="title">
					<span>로그인</span>
				</div>
			</div>

			<div class="input-wrap">
				<div class="input-box">
					<input name="userid" id="userid" placeholder="아이디를 입력해주세요"
						autocomplete="off">
				</div>
				<div class="input-box">
					<input type="password" name="passwd" id="passwd"
						placeholder="비밀번호를 입력해주세요">
				</div>
				<p id="loginError" class="error-msg help error"></p>
			</div>

			<div class="info-wrap">
				<div class="remind-box">
					<a href="${path}/member/remind"><span>로그인정보를 잊으셨나요?</span></a>
				</div>
			</div>

			<div class="button-wrap">
				<input type="button" onclick="loginAction()" value="로그인">
			</div>
			<div class="join-info">
				<p>
					아직 회원이 아니신가요? <a href="${path}/member/join">회원가입</a>
				</p>
			</div>

		</div>
	</div>
</div>
</body>
</html>
