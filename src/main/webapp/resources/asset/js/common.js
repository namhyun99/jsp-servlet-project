//정규식을 통한 유효성 체크 변수
const check = {
    "phone-exp": /^[0-9]+$/,
    "email-exp": /^[a-zA-Z0-9]{2,}@[a-z0-9]{2,}\.[a-z]{2,}$/,
    "email2-exp": /^[a-z0-9]{2,}\.[a-z]{2,}$/,
    "passwd-exp": /(?=.*[a-z])(?=.*[!@#$%^*+=-])(?=.*\d){8,12}/,
    "userid-exp": /^[A-Za-z0-9]{4,10}$/
}

//페이지 로딩완료시 로딩바 숨기기
$(window).one('load', function() {
	// console.log('로드완료...')
	$("#load").hide();
	document.title += " - " + $(".title").text(); //페이지별 타이틀 설정
});

$(function () {
    modeCheck();

    //탭메뉴 구현
    $(".tab-menu li").click(function () {
        var activeTab = $(this).attr('data-tab');
        $(".tab-menu li").removeClass('on');
        $(".menu-cont").removeClass('on');
        $(this).addClass('on');
        $("#" + activeTab).addClass('on');
        $(".result-id").text("");
        $(".result-pwd").text("");
    });

    var backTop = $(".btn-back_to_top");

    $(window).scroll(function () {
        if ($(document).scrollTop() > 100) {
            backTop.css('visibility', 'visible');
        }
        else if ($(document).scrollTop() < 100) {
            backTop.css('visibility', 'hidden');
        }
    });

    backTop.click(function () {
        $('html').animate({
            scrollTop: 0
        }, 1000);
        return false;
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


// 모드체크 함수
function modeCheck() {
    var modeCheck = localStorage.getItem('darkMode');

    if (modeCheck == null) {
        localStorage.setItem('darkMode', 'N');
    } else if (modeCheck == 'Y') {
        darkModeHandler();
    } else if (modeCheck == 'N') {
        lightModeHandler();
    }
}

// 로컬스토리지를 이용한 다크모드 구현
function modeChange() {
    var mode = localStorage.getItem('darkMode');
    if (mode == 'N') {
        darkModeHandler();
        localStorage.setItem('darkMode', 'Y');
    } else if (mode == 'Y') {
        lightModeHandler();
        localStorage.setItem('darkMode', 'N');
    }

}
// 다크 모드
function darkModeHandler() {
    $("body").attr('data-theme', 'dark');
    $("#dark").toggleClass('fas fa-moon far fa-lightbulb');
}

//라이트 모드 
function lightModeHandler() {
    $("body").attr('data-theme', 'light');
    $("#dark").toggleClass('fas fa-moon far fa-lightbulb');
}

//파일 업로드 체크
function uploadFileCheck() {
    var maxSize = 10 * 1024 * 1024; //10MB
    var filename = $("#filename").val();
    var filesize = $("#filename")[0].files[0].size;

    if (filename != "") {
        var ext = filename.split('.').pop().toLowerCase();
        if ($.inArray(ext, ['jpg', 'jpeg', 'gif', 'png']) == -1) {
            alert("jpg, gif, jpeg, png 파일만 업로드 할수 있습니다.");
            $("#filename").val("");
            return false;
        };
    }

    if (filesize > maxSize) {
        alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
        $("#filename").val("");
        return false;
    }
}

function myOptionList() {
    $(".option-list").toggleClass('on ');
}