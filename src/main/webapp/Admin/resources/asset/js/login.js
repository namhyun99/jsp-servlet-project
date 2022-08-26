$(function(){
	var path = getContextPath();
	
    //로그인 버튼 클릭 시
    $("#btnLogin").click(function () {
        if(LoginCheck()) {
            ajaxLogin(path);
        }
    });

    //체크박스 클릭 시
    $("#chkSave").click(function () {
        checkSaveId();
    });

    //ID,PW찾기 클릭시
    $("#remindIdPw").click(function () {
        remindIdPwOpen();
    });
    
    
    //아이디찾기 버튼
    $("#btnRemindId").click(function () {
        if (remindIDCheck()) {
            ajaxRemindID(path);
        }
    });

    //비밀번호 찾기 버튼
    $("#btnRemindPw").click(function () {
        if (remindPwdCheck()) {
            ajaxRemindPwd(path);
        }
    });
    
})


//아이디 찾기 입력 체크
function remindIDCheck() {
    var name = $("#name").val();
    var phone2 = $("#phone2").val();

    if (name == "") {
        $(".result-id").text("이름을 입력해주세요.");
        $(".input-name-box").addClass("error");
        $("#name").focus();
        return false;
    } else {
        $(".input-name-box").removeClass("error");
    }

    if (phone2 == "") {
        $(".result-id").text("연락처를 입력해주세요.");
        $(".input-phone-box").addClass("error");
        $("#phone2").focus();
        return false;
    } else {
        $(".input-phone-box").removeClass("error");
    }

    var p_exp = /^[0-9]+$/;
    if (phone2 != "") {
        if (!p_exp.test(phone2)) {
            $(".result-id").text("연락처는 숫자로만 입력해주세요.");
            $(".input-phone-box").addClass("error");
            $("#phone2").focus();
            return false;
        } else {
            $(".input-phone-box").removeClass("error");
        }
    }

    if (phone2.length > 8 || phone2.length <= 7) {
        $(".result-id").text("연락처는 8개의 숫자로 입력 가능합니다.");
        $(".input-phone-box").addClass("error");
        $("#phone2").focus();
        return false;
    } else {
        $(".input-phone-box").removeClass("error");
    }

    return true;
}

//아이디 비동기화 처리
function ajaxRemindID(path) {
    var name = $("#name").val();
    var phone = $("#phone1").val() + $("#phone2").val();

    $.ajax({
        type: "post",
        url: path + "/admin/actionID",
        data: { "name": name, "phone": phone },
        success: function (data) {
            if (data != "null") {
                $(".result-id").text("아이디는 [ " + data + " ] 입니다.");
                $("#name").val("");
                $("#phone2").val("");
            } else {
                $(".result-id").text("없는 아이디 입니다,");
                $("#name").val("");
                $("#phone2").val("");
            }
        },
        beforeSend: function(){ //조회중
			$("#load").css("display","block");
		},
        complete: function(){ //조회완료
			$("#load").css("display","none");
		},
        error: function () {//에러
            location.href = path + "/error/error.jsp";
        }
    });
}


//비밀번호 찾기 입력 체크
function remindPwdCheck() {
    var userid = $("#userid").val();
    var email1 = $("#email1").val();
    var email2 = $("#email2").val();

    if (userid == "") {
        $(".result-pwd").text("아이디를 입력해주세요.");
        $(".input-id-box").addClass("error");
        $("#userid").focus();
        return false;
    } else {
        $(".input-id-box").removeClass("error");
    }

    if (userid.length < 4 || userid.length > 15) {
        $(".result-pwd").text("아이디는 4자이상 15자이하로 입력해야 합니다.");
        $(".input-id-box").addClass("error");
        $("#userid").focus();
        return false;
    } else {
        $(".input-id-box").removeClass("error");
    }

    if (email1 == "") {
        $(".result-pwd").text("이메일 앞자리를 입력해주세요.");
        $(".input-email1-box").addClass("error");
        $("#email1").focus();
        return false;
    } else {
        $(".input-email1-box").removeClass("error");
    }

    if (email2 == "") {
        $(".result-pwd").text("이메일 뒷자리를 입력해주세요.");
        $(".input-email2-box").addClass("error");
        $("#email2").focus();
        return false;
    } else {
        $(".input-email2-box").removeClass("error");
    }

    var e2_exp = /^[a-z0-9]{2,}\.[a-z]{2,}$/
    if (email2 != "") {
        if (!e2_exp.test(email2)) {
            $(".result-pwd").text("이메일 형식이 맞지 않습니다.");
            $(".input-email2-box").addClass("error");
            $("#email2").focus();
            return false;
        } else {
            $(".input-email2-box").removeClass("error");
        }
    }

    return true;
}

//비밀번호 비동기화 처리
function ajaxRemindPwd(path) {
    var userid = $("#userid").val();
    var email = $("#email1").val() + "@" + $("#email2").val();

    $.ajax({
        type: "post",
        url: path + "/admin/actionPwd",
        data: { "userid": userid, "email": email },
        success: function (data) {
            if (data != "null") {
                $(".result-pwd").text("임시비밀번호가 메일로 발송되었습니다.");
                $("#name").val("");
                $("#phone2").val("");
            } else {
                $(".result-pwd").text("가입되지 않은 아이디 입니다.");
                $("#name").val("");
                $("#phone2").val("");
            }
        },
        beforeSend: function(){ //조회중
			$("#load").css("display","block");
		},
        complete: function(){ //조회완료
			$("#load").css("display","none");
		},
        error: function () {//에러
            location.href = path + "/error/error.jsp";
        }
    });
}



//로그인 입력체크
function LoginCheck() {
    var userid = $("#userid");
    var passwd = $("#passwd");
    var chkSave = $("#chkSave");

    if (userid.val() == "") {
        alert("아이디를 입력하세요");
        userid.focus();
        return false;
    }

    if (passwd.val() == "") {
        alert("비밀번호를 입력하세요");
        passwd.focus();
        return false;
    }

    // 아이디 저장 체크박스 쿠기 저장	
    if (chkSave.is(":checked")) {
        saveCookie(userid.val());
    } else {
        saveCookie("");
    }

    return true;
}

//로그인 비동기화 처리
function ajaxLogin(path) {
    var param = "userid=" + $("#userid").val()
        + "&passwd=" + $("#passwd").val();

    $.ajax({
        type: "post",
        url: path + "/admin/actionLogin",
        data: param,
        success: function (data) {
            if (data === 'false') {
                alert("아이디 또는 비밀번호가 일치하지 않습니다.");
            } else {
                location.href = path + "/admin/main";
            }
        },
        beforeSend: function(){ //조회중
			$("#load").css("display","block");
		},
        complete: function(){ //조회완료
			$("#load").css("display","none");
		},
        error: function () {//에러
            location.href = path + "/error/error.jsp";
        }
    });
}


//아이디 저장 체크박스 
function checkSaveId() {
    var chkSave = $("#chkSave");

    if (chkSave.is(":checked")) {
        chkSave.prop("checked", true);
    }
}

//getCookie
function getCookie(cname) {
    var cookie = document.cookie + ";";
    var idx = cookie.indexOf(cname, 0);
    var val = "";
    if (idx != -1) {
        cookie = cookie.substring(idx, cookie.length);
        begin = cookie.indexOf("=", 0) + 1;
        end = cookie.indexOf(";", begin);
        val = cookie.substring(begin, end);
    }
    return val;
}

//setCookie(쿠키변수명, 쿠키값, 유효날짜)
function setCookie(name, value, days) {
    var path = getContextPath();
    var today = new Date();
    today.setDate(today.getDate() + days);
    //쿠키변수명=쿠키값; path=저장경로; expires=쿠키유효기간;
    document.cookie = name + "=" + value + ";path=" + path + ";expires=" + today.toGMTString() + ";"
}

//쿠키값 저장하기
function saveCookie(id) {
    if (id != "") {
        setCookie("userid", id, 7); //7일
    } else {
        setCookie("userid", id, -1); //삭제
    }
}

//ID,PW찾기 팝업창 열기
function remindIdPwOpen() {
    var path = getContextPath();
    window.open(path + "/admin/remind", "_blank", "width=600, height=600, left=250, top=80, scrollbars=no, toollbars=no, location=no");
}