
//정규식을 통한 유효성 체크 변수
const check = {
	"phone-exp": /^[0-9]+$/,
	"email-exp": /^[a-zA-Z0-9]{2,}@[a-z0-9]{2,}\.[a-z]{2,}$/,
	"email2-exp": /^[a-z0-9]{2,}\.[a-z]{2,}$/,
	"passwd-exp" : /(?=.*[a-z])(?=.*[!@#$%^*+=-])(?=.*\d){8,12}/,
	"userid-exp" : /^[A-Za-z0-9]{4,10}$/
}

//페이지 로딩완료시 로딩바 숨기기
$(window).one('load', function() {
	// console.log('로드완료...')
	$("#load").hide();
	document.title += " - " + $(".title h2").text(); //페이지별 타이틀 설정
});

$(function() {
	//탭메뉴 구현
	$(".tab-menu li").click(function() {
		var activeTab = $(this).attr('data-tab');
		$(".tab-menu li").removeClass('on');
		$(".menu-cont").removeClass('on');
		$(this).addClass('on');
		$("#" + activeTab).addClass('on');
		$(".result-id").text("");
		$(".result-pwd").text("");
	});
});


//sessionStorage를 이용해 header에서 선언한 contextpath값 받아오기
function getContextPath() {
	return sessionStorage.getItem("contextPath");
}

//sessionStorage를 이용해 url 값 받아오기
function getUrl() {
	return sessionStorage.getItem("url");
}

//페이지네이션
function pagination(page, order, op, keyword) {
	var url = getUrl();
	if (keyword != "") {
		location.href = url + "?order=" + order + "&op="
			+ op + "&keyword=" + keyword + "&page=" + page
	} else {
		location.href = url + "?order=" + order + "&page=" + page;
	}
}

//리스트 정렬
function orderSelect(f, order, op, keyword) {
	var url = getUrl();
	var order = $("#order").val();

	$.ajax({
		type: "get",
		url: url,
		data: { "order": order },
		success: function() {
			if (keyword != "") {
				location.href = url + "?order=" + order + "&op="
					+ op + "&keyword=" + keyword;
			} else {
				f.submit();
			}
		}
	})

}

//검색어 기능
function listSearch(f) {
	f.submit();
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
		}
	});

}

//비밀번호 찾기 버큰
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
		}
	});
}

//ID,PW찾기 팝업창 열기
function remindPopOpen() {
	var PATH = getContextPath();
	window.open(PATH + "/admin/login/remind", "_blank",
		"width=600, height=600, left=250, top=80, scrollbars=no, toollbars=no, location=no");
}


//회원정보 수정
function memberInfoUpdate(f){
	var PATH = getContextPath();
	var passwd = $("#passwd").val();
	var name = $("#name").val();
	var email = $("#email").val();
	var phone = $("#phone").val(); 	
	
	if(passwd == ""){
		$("#passwd").addClass("error");
		$(".error-msg.passwd").show();
		$(".error-msg.passwd").text("비밃번호를 입력하세요");
		return false;	
	} else if(!check["passwd-exp"].test(passwd)){
		$("#passwd").addClass("error");
		$(".error-msg.passwd").show();
		$(".error-msg.passwd").text("비밀번호는 영문소문자,숫자,특수기호(!@#$%^*+=-)를 모두 사용해서 8~12자리로 입력하세요.");
		return false;
	} else {
		$("#passwd").removeClass("error");
		$(".error-msg.passwd").hide();
	}
		
		
	if(name == ""){
		$("#name").addClass("error");
		$(".error-msg.name").show();
		$(".error-msg.name").text("이름을 입력해주세요");
		return false;
	}  else {
		$("#name").removeClass("error");
		$(".error-msg.name").hide();
	}
	
	if(email == ""){
		$("#email").addClass("error");
		$(".error-msg.email").show();
		$(".error-msg.email").text("이메일 주소를 입력해주세요");
		return false;
	} else if(email != ""){
		if(!check["email-exp"].test(email)){
			$("#email").addClass("error");
			$(".error-msg.email").show();
			$(".error-msg.email").text("이메일 형식에 맞지 않습니다.");
			return false;
		}
	} else {
		$("#email").removeClass("error");
		$(".error-msg.email").hide();
	}
	
	if(phone == ""){
		$("#phone").addClass("error");
		$(".error-msg.phone").show();
		$(".error-msg.phone").text("전화번호를 입력해주세요.");
		return false;
	} else if(phone != ""){
		if(!check["phone-exp"].test(phone) || phone.length < 10){
			$("#phone").addClass("error");
			$(".error-msg.phone").show();
			$(".error-msg.phone").text("전화번호는 11자리 숫자만 입력해주세요.");
			return false;
		}
	} else {
		$("#phone").removeClass("error");
		$(".error-msg.phone").hide();
	}

	f.action = PATH + "/admin/member/updateMember.do";
	f.submit();
}

//회원정보 삭제
function memberInfoDelete(f){
	var PATH = getContextPath();
	if(confirm("정말 삭제하시겠습니까?")){
		f.action = PATH + "/admin/member/deleteMember.do";
		f.submit();
	}	
}

//회원추가
function memberAdd(f){
	var PATH = getContextPath();
	var authority = $("input:radio[name='authority']");
	
	if(!authority.is(":checked")){
		alert("권한을 체크해주세요.");
	}
}

//회원추가시 아이디 중복체크
function useridDuplicateCheck(){
	var PATH = getContextPath();
	var userid = $("#userid").val();
	var str_space = /\s/; // 공백체크
	
	$.ajax({
		type : "post",
		url : PATH + "/admin/member/duplicate.do",
		data : {"userid" : userid },
		success : function(data){
			if(data != '0'){
				$(".error-msg.userid").text("중복된 아이디입니다.");
				$(".error-msg").show();
			} else {
                if (userid.length == 0) {
                    $(".error-msg.userid").text("아이디는 필수 입력 값입니다.");
                    $(".error-msg").show();
                } else if (str_space.exec(userid)) {
                    $(".error-msg.userid").text("아이디에는 띄어쓰기를 하실 수 없습니다.");
                    $(".error-msg").show();
                }else {
                    $(".error-msg.userid").text("사용 가능한 아이디입니다.");
                    $(".error-msg").show();
                }
            }
        }
        ,
        error: function () {
            alert("데이터베이스에 접근이 필요합니다!");
        }

	});
}




