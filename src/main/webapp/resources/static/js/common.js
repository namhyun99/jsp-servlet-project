
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

//검색 기능
function listSearch(f) {
	f.submit();
}

//회원정보 수정
function memberUpdateSubmit(f){
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
function memberDeleteSubmit(f){
	var PATH = getContextPath();
	if(confirm("정말 삭제하시겠습니까?")){
		f.action = PATH + "/admin/member/deleteMember.do";
		f.submit();
	}	
}

//회원추가
function memberJoinSubmit(f){
	var PATH = getContextPath();
	var authority = $("input:radio[name='authority']");
	var userid = $("#userid").val();
	var passwd = $("#passwd").val();
	var passwd2 = $("#passwd2").val();
	var name = $("#name").val();
	var email1 = $("#email1").val();
	var email2 = $("#email2").val()
	var phone1 = $("#phone1").val();
	var phone2 = $("#phone2").val();
	var phone3 = $("#phone3").val();
		
	if(!authority.is(":checked")){
		alert("권한을 체크해주세요.");
		return false;
	}
	
	if(userid == ""){
		alert("아이디를 입력해주세요. (4자이상 20자미만)");
		return false;
	} else if ($(".error-msg.userid").text() == "중복된 아이디입니다."){
		alert("중복된 아이디입니다.");
		return false;
		
	} else if(!check["userid-exp"].test(userid)){
		alert("아이디에는 영문숫자 포함 4~10자로 입력가능합니다.");
		return false;
	}
		
	if(passwd == ""){
		alert("비밀번호를 입력해주세요.")
		return false;	
	} 
	
	if(!check["passwd-exp"].test(passwd)){
		alert("비밀번호는 영문소문자,숫자,특수기호(!@#$%^*+=-)를 모두 사용해서 8~12자리로 입력하세요.");
		return false;
	} 
	
	if(passwd != passwd2){
		alert("비밀번호 체크를 확인해주세요.");
		return false;
	}
		
	if(name == ""){
		alert("이름을 입력해주세요");
		$("#name").focus();
		return false;
	}  
	
	if(email1 != "" || email2 != ""){
		var email = email1 + "@" + email2;
		if(!check["email-exp"].test(email)){
			alert("이메일 형식에 맞춰 작성해 주세요.")
			return false;
		}
	} 

	
	if(phone1 != "" || phone2 != "" || phone3 != ""){
		var phone = phone1 + phone2 + phone3;
		if(!check["phone-exp"].test(phone) || phone.length < 10){
			alert("전화번호는 11자리 숫자만 입력해주세요.");
			return false;
		}
	} 
	
	f.action = PATH + "/admin/member/joinMember.do";
	f.submit();
	
}

//비밀번호 체크 확인 
function passwdDuplicateCheck(){
	var passwd = $("#passwd").val();
	var passwd2 = $("#passwd2").val();
	
	if(!check["passwd-exp"].test(passwd)){
		$(".error-msg.passwd").text("영문소문자,숫자,특수기호(!@#$%^*+=-)를 모두 사용해서 8~12자리로 입력해주세요.");
		$(".error-msg").show();
	} 
	
	else if(passwd != passwd2){
		$(".error-msg.passwd").text("비밀번호가 일치하지 않습니다.");
		$(".error-msg").show();
	} 
	
	else {
		$(".error-msg.passwd").text("");
		$(".error-msg").hide();
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


//게시글 수정 
function editBoardSubmit(f){
	var PATH = getContextPath();
	var subject = $("#subject").val();
	
	if(subject == ""){
		alert("제목은 필수입력 사항입니다.");
		return false;
	}
	
	f.action = PATH + "/admin/board/update.do";
	f.submit()
}

//게시글 삭제
function deleteBoardSubmit(f){
	var PATH = getContextPath();
	if(confirm("정말 삭제하시겠습니까?")){
		f.action = PATH + "/admin/board/deleteBoard.do";
		f.submit();
	}	
}


//게시글 등록
function writeBoardSubmit(f){
	var PATH = getContextPath();
	var board_no = $("#board_no").val();
	var subject = $("#subject").val();
	var show = $("#show");
	
	if(board_no == ""){
		alert("게시판 선택은 필수 사항입니다.");
		return false;
	}
	
	if(subject == ""){
		alert("제목은 필수입력 사항입니다.");
		return false;
	}
	
	if(!show.is(":checked")){
		alert("공개/비굥개 여부를 체크해주세요.");
		return false;
	}
	
	f.action = PATH + "/admin/board/write.do";
	f.submit();
}


