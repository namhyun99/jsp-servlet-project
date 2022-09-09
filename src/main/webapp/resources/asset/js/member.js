//ajax통신으로 비동기 로그인 하기
function loginAction() {
	var userid = $("#userid").val();
	var passwd = $("#passwd").val();

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
		url: PATH + "/member/actionLogin.do",
		data: { "userid": userid, "passwd": passwd },
		success: function(data) {
			if (data == 'false') {
				$("#loginError").text("아이디 또는 비밀번호가 일치하지 않습니다.");
				$("#loginError").show();
			} else {
				location.href = PATH + "/main";
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
		url: PATH + "/member/actionID",
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
		url: PATH + "/member/actionPwd",
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



//회원추가
function memberJoinSubmit(f) {
    var PATH = getContextPath();
    var userid = $("#userid").val();
    var passwd = $("#passwd").val();
    var passwd2 = $("#passwd2").val();
    var name = $("#name").val();
    var email1 = $("#email1").val();
    var email2 = $("#email2").val()
    var phone1 = $("#phone1").val();
    var phone2 = $("#phone2").val();
    var phone3 = $("#phone3").val();
    var consent = $("#consent");
    var privacy = $("#privacy");

    if (userid == "") {
        alert("아이디를 입력해주세요. (4자이상 20자미만)");
        return false;
    } else if ($(".error-msg.userid").text() == "중복된 아이디입니다.") {
        alert("중복된 아이디입니다.");
        return false;
    } else if (!check["userid-exp"].test(userid)) {
        alert("아이디에는 영문숫자 포함 4~10자로 입력가능합니다.");
        return false;
    }

    if (passwd == "") {
        alert("비밀번호를 입력해주세요.")
        return false;
    }

    if (!check["passwd-exp"].test(passwd)) {
        alert("비밀번호는 영문소문자,숫자,특수기호(!@#$%^*+=-)를 모두 사용해서 8~12자리로 입력하세요.");
        return false;
    }

    if (passwd != passwd2) {
        alert("비밀번호 체크를 확인해주세요.");
        return false;
    }

    if (name == "") {
        alert("이름을 입력해주세요");
        $("#name").focus();
        return false;
    }

    if (email1 != "" || email2 != "") {
        var email = email1 + "@" + email2;
        if (!check["email-exp"].test(email)) {
            alert("이메일 형식에 맞춰 작성해 주세요.")
            return false;
        }
    }

    if (phone1 != "" || phone2 != "" || phone3 != "") {
        var phone = phone1 + phone2 + phone3;
        if (!check["phone-exp"].test(phone) || phone.length < 10) {
            alert("전화번호는 11자리 숫자만 입력해주세요.");
            return false;
        }
    }

    if (!consent.is(":checked")) {
        alert("이용약관을 확인해주세요");
        return false;
    }

    if (!privacy.is(":checked")) {
        alert("개인정보동의을 확인해주세요");
        return false;
    }

    f.action = PATH + "/member/insertMember.do";
    f.submit();
}


//비밀번호 체크 확인 
function passwdDuplicateCheck() {
    var passwd = $("#passwd").val();
    var passwd2 = $("#passwd2").val();

    if (!check["passwd-exp"].test(passwd)) {
        $(".error-msg.passwd").text("영문소문자,숫자,특수기호(!@#$%^*+=-)를 모두 사용해서 8~12자리로 입력해주세요.");
        $(".error-msg").show();
    }

    else if (passwd != passwd2) {
        $(".error-msg.passwd").text("비밀번호가 일치하지 않습니다.");
        $(".error-msg").show();
    }

    else {
        $(".error-msg.passwd").text("");
        $(".error-msg").hide();
    }
}

//회원추가시 아이디 중복체크
function useridDuplicateCheck() {
    var PATH = getContextPath();
    var userid = $("#userid").val();
    var str_space = /\s/; // 공백체크

    $.ajax({
        type: "post",
        url: PATH + "/admin/member/duplicate.do",
        data: { "userid": userid },
        success: function (data) {
            if (data != '0') {
                $(".error-msg.userid").text("중복된 아이디입니다.");
                $(".error-msg").show();
            } else {
                if (userid.length == 0) {
                    $(".error-msg.userid").text("아이디는 필수 입력 값입니다.");
                    $(".error-msg").show();
                }
                else if (str_space.exec(userid)) {
                    $(".error-msg.userid").text("아이디에는 띄어쓰기를 하실 수 없습니다.");
                    $(".error-msg").show();
                }
                else if (!check["userid-exp"].test(userid)) {
                    $(".error-msg.userid").text("아이디에는 영문숫자 포함 4~10자로 입력가능합니다.");
                    $(".error-msg").show();
                }
                else {
                    $(".error-msg.userid").text("");
                    $(".error-msg").hide();
                }
            }
        },
        error: function () {
            alert("데이터베이스에 접근이 필요합니다!");
        }
    });
}

//이메일 셀렉
function e_select(obj) {
    $("[name=email2]").val(obj.value);
}


