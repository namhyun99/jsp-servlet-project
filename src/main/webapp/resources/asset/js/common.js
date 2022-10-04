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

//페이지네이션
function pagination(page, keyword) {
	var url = getUrl();
//	console.log(keyword);
	if(keyword != ""){
		location.href = url + "?keyword=" + keyword + "&page=" + page;
	} else {
		location.href = url + "?page=" + page;
	}
}


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
        var ext = filename.split('.').pop().toLowerCase(); //확장자
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

//옵션 체크
function myOptionList() {
    $(".option-list").toggleClass('on ');
}


//댓글 리스트 보기
function showCommentList(c_idx){
	var PATH = getContextPath();
	$.ajax({
		type : "post",
		url : PATH + "/board/commentList.do",
		data : {"c_idx" : c_idx},
		success : function(result){
			$(".comment-list").html(result);
		}
	});
}


// 댓글 작성하기
function addCommentSubmit(c_idx){
	var PATH = getContextPath();
	var writer = $("#writer").val();
	var content = $("#cmt_content").val();
	console.log(c_idx);
	
	$.ajax({
		type : "post",
		url : PATH + "/board/addComment.do",
		data : {"c_idx": c_idx , "writer" : writer , "content" : content},
		success : function(){
			$("#cmt_content").val(" ");
			showCommentList(c_idx);
		}
	});
}

//댓글 삭제하기
function deleteCommentSubmit(cmt_idx, c_idx){
	var PATH = getContextPath();
	
	if(confirm("정말 삭제하겠습니까?")){
		$.ajax({
			type : "post",
			url : PATH + "/board/deleteComment.do",
			data : {"cmt_idx": cmt_idx},
			success : function(){
				showCommentList(c_idx);
			}
		});
	}
}


//게시글 작성하기
function insertContentsSubmit(f){
	var PATH = getContextPath();
	var cate_no = $("#cate_no").val();
	var subject = $("#subject").val();
	var show = $("#show");
	
	
	if(cate_no == ""){
		alert("카테고리 선택은 필수 사항입니다.");
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
	
	f.action = PATH + "/board/insertContents.do";
	f.submit();
	
}

//게시글 수정하기
function updateContentsSubmit(f){
	var PATH = getContextPath();
	var subject = $("#subject").val();
	
	if(subject == ""){
		alert("제목은 필수 입력사항 입니다.");
		return false;
	}
	
	f.action = PATH + "/board/updateContents.do";
	f.submit();
}


//게시글 삭제하기
function deleteContentsSubmit(c_idx){
	var PATH = getContextPath();
	
	if(confirm("정말 삭제하겠습니까?")){
		$.ajax({
			type : "post",
			data : {"c_idx" : c_idx},
			url : PATH + "/board/deleteContents.do",
			success : function(data){
				if(data == 'true'){
					alert("삭제가 완료되었습니다.");
					location.href= PATH + "/main";
				} else {
					location.href = PATH + "/error/error.jsp";
				}
			}
		})
	}
}

//url에 파라미터값 추가
function insertURLParam(){
	var keyword = $("#search").val();
	history.pushState(null, null, "search?q="+keyword);
}

//검색기능 구현 (비동기)
function searchAction(){
	insertURLParam();
	const locationURL = location.href; //현재주소
	const searchParams = new URL(locationURL).searchParams; //
	const keyword = searchParams.get('q');
//	console.log("결과 : " + keyword);
	var PATH = getContextPath();
//	location.href= PATH + "/search?keyword="+keyword;
	$.ajax({
		type : "get",
		url : PATH + "/search",
		data : {"keyword" : keyword},
		success : function(data){
//			console.log(data);
			$("#search_result").html(data);
		}
	})
}

//공지사항 및 1대1문의 내 검색 기능
function listSearch(f){
	f.submit();
}


//1대1 문의 작성하기
function insertInquirySubmit(f){
	var PATH = getContextPath();
	var title = $("#title").val();
	
	if(title == ""){
		alert("제목은 필수입력 사항입니다.");
		return false;
	}
	
	
	f.action = PATH + "/board/insertInquiry.do";
	f.submit();
	
}


//서머노트 이미지 ajax 업로드 
function sendFile(file, editor) {
	var PATH = getContextPath();
	data = new FormData();

	
	console.log(file);
	console.log(editor);
	data.append("uploadFile", file);

	$.ajax({
		data: data,
		type: "POST",
		url: PATH + "/summernoteImageUpload.do",
		enctype: "multipart/form-data",
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			console.log(data);
			$(editor).summernote('editor.insertImage', data.url);
		}
	})
}
















	

