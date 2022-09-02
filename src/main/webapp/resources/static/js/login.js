
//ajax통신으로 비동기 로그인 하기
function loginAction() {
	var userid = $("#userid").val();
	var passwd = $("#passwd").val();
	var chkSave = $("#chkSave");

	if (chkSave.is(":checked")) {
		saveCookie(userid);
	} else {
		saveCookie("");
	}

	if (userid == "") {
		$("#userid").addClass("error");
		return false;
	}

	if (passwd == "") {
		$("#passwd").addClass("error");
		return false;
	}

	var PATH = getContextPath();

	$.ajax({
		type: "post",
		url: PATH + "/admin/login/actionLogin",
		data: { "userid": userid, "passwd": passwd },
		success: function(data) {
			if (data == 'false') {
				$("#loginError").text("아이디 또는 비밀번호가 일치하지 않습니다.");
				$("#loginError").show();
			} else {
				location.href = PATH + "/admin/main";
			}
		},
		beforeSend: function() {
 			$("#load").show();
		},
		complete: function() {
 			$("#load").hide();
		},
		error: function() {
			location.href = PATH + "/error/error.jsp";
		}
	});
}


//아이디 찾기 버튼
function btnRemindIdClick() {
	var name = $("#name").val();
	var phone1 = $("#phone1").val();
	var phone2 = $("#phone2").val();

	if (name == "") {
		$(".result-id").text("이름을 입력해주세요");
		$("#name").addClass("error");
		return false;
	} else {
		$("#name").removeClass("error");
	}

	if (phone2 == "") {
		$(".result-id").text("연락처를 입력해주세요.");
		$("#phone2").addClass("error");
		return false;
	} else if (phone2 != "") {
		if (!check["phone-exp"].test(phone2) || phone2.length < 7) {
			$(".result-id").text("연락처는 8개의 숫자로만 입력해주세요.");
			$("#phone2").addClass("error");
			return false;
		} else {
			$("#phone2").removeClass("error");
		}
	} else {
		$("#phone2").removeClass("error");
	}

	// 결과값 비동기화 처리
	var phone = phone1 + phone2;
	var PATH = getContextPath();

	$.ajax({
		type: "post",
		url: PATH + "/admin/login/actionID",
		data: { "name": name, "phone": phone },
		success: function(data) {
			console.log(data);
			if (data != "null") {
				$(".result-id").text("아이디는 [ " + data + " ] 입니다.");
				$("#name").val("");
				$("#phone2").val("");
			} else {
				$(".result-id").text("없는 아이디 입니다,");
			}
		},
		beforeSend: function() {
 			$("#load").show();
		},
		complete: function() {
 			$("#load").hide();
		},
		error: function() {
			location.href = PATH + "/error/error.jsp";
		}
	});

}

//비밀번호 찾기 버튼
function btnRemindPwdClick() {

	var userid = $("#userid").val();
	var email1 = $("#email1").val();
	var email2 = $("#email2").val();

	if (userid == "") {
		$(".result-pwd").text("이름을 입력해주세요");
		$("#userid").addClass('error');
		return false;
	} else {
		$("#userid").removeClass('error');
	}

	if (email1 == "") {
		$(".result-pwd").text("이메일 앞자리를 입력해주세요.");
		$("#email1").addClass("error");
		return false;
	} else {
		$("#email1").removeClass("error");
	}

	if (email2 == "") {
		$(".result-pwd").text("이메일 뒷자리를 입력해주세요.");
		$("#email2").addClass("error");
		return false;
	} else if (email2 != "") {
		if (!check["email2-exp"].test(email2)) {
			$(".result-pwd").text("이메일 형식이 맞지 않습니다.");
			$("#email2").addClass("error");
			return false;
		} else {
			$("#email2").removeClass("error");
		}
	} else {
		$("#email2").removeClass("error");
	}


	var email = email1 + "@" + email2;
	var PATH = getContextPath();

	$.ajax({
		type: "post",
		url: PATH + "/admin/login/actionPwd",
		data: { "userid": userid, "email": email },
		success: function(data) {
			console.log(data);
			if (data != "null") {
				$(".result-pwd").text("임시비밀번호 [" + data + "]가 생성되었습니다.");
				$("#userid").val("");
				$("#email1").val("");
				$("#email2").val("");
			} else {
				$(".result-pwd").text("가입되지 않은 아이디 입니다.");
			}
		},
		beforeSend: function() {
 			$("#load").show();
		},
		complete: function() {
 			$("#load").hide();
		},
		error: function() {
			location.href = PATH + "/error/error.jsp";
		}
	});
}


//ID,PW찾기 팝업창 열기
function remindPopOpen() {
	var PATH = getContextPath();
	window.open(PATH + "/admin/login/remind", "_blank",
		"width=600, height=600, left=250, top=80, scrollbars=no, toollbars=no, location=no");
}

