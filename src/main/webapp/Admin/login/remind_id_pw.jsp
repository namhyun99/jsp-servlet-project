<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="load" style="display:none;">
	<img src="${path}/Admin/resources/asset/images/load_img.gif" alt="loading">
</div>

<div id="remind-wrap">
	<div class="tab-box">
		<ul class="tab-menu">
			<li class="on" data-tab="menu1">아이디</li>
			<li data-tab="menu2">비밀번호</li>
		</ul>

		<div id="menu1" class="menu-cont on">
			<div class="title-wrap">
				<p class="title">아이디 찾기</p>
				<p class="desc">가입시 입력한 이름과 연락처를 입력하세요.</p>
			</div>

			<div class="form-wrap">
				<div class="input-wrap">
					<div class="label">
						<span>이름</span>
					</div>
					<div class="input-box">
						<input name="name" id="name" placeholder="이름을 입력해주세요."
							autocomplete="off">
					</div>
				</div>
				<div class="input-wrap">
					<div class="label">
						<span>연락처</span>
					</div>
					<div class="select-input">
						<div class="select-box">
							<select id="phone1" name="phone1">
								<option value="010" selected>010</option>
								<option value="010">011</option>
							</select>
						</div>
						<div class="input-box">
							<input name="phone2" id="phone2" placeholder="나머지 연락처를 입력해주세요."
								autocomplete="off">
						</div>
					</div>
				</div>

				<div class="result-wrap">
					<span class="result-id"></span>
				</div>

				<div class="btn-wrap">
					<input type="button" id="btnRemindId" value="아이디 찾기"
						onclick="btnRemindIdClick()">
				</div>
			</div>
		</div>

		<div id="menu2" class="menu-cont">
			<div class="title-wrap">
				<p class="title">비밀번호 찾기</p>
				<p class="desc">가입시 입력한 아이디와 이메일을 입력하세요.</p>
			</div>

			<div class="form-wrap">
				<div class="input-wrap">
					<div class="label">
						<span>아이디</span>
					</div>
					<div class="input-box">
						<input name="userid" id="userid" placeholder="아이디를 입력해주세요"
							autocomplete="off">
					</div>
				</div>
				<div class="input-wrap">
					<div class="label">
						<span>이메일</span>
					</div>
					<div class="select-input">
						<div class="input-box">
							<input name="email1" id="email1" placeholder="앞자리"
								autocomplete="off">
						</div>
						<span> @ </span>
						<div class="input-box">
							<input name="email2" id="email2" placeholder="직접입력"
								autocomplete="off">
						</div>
					</div>
				</div>

				<div class="result-wrap">
					<span class="result-pwd"></span>
				</div>

				<div class="btn-wrap">
					<input type="button" id="btnRemindPw" value="임시비밀번호발급"
						onclick="btnRemindPwdClick();">
				</div>
			</div>
		</div>
	</div>
</div>