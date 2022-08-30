
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

