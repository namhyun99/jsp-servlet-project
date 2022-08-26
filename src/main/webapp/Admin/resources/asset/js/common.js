//페이지 로딩완료시 로딩바 숨기기
$(window).one('load', function () {
    // console.log('로드완료...')
    $("#load").hide();
});

//sessionStorage를 이용해 header에서 선언한 contextpath값 받아오기
function getContextPath() {
    return sessionStorage.getItem("contextPath");
}

function getUrl() {
	return sessionStorage.getItem("url");
}

$(function () {
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
});


//페이지네이션
function pagination(page, order, searchkey, keyword){
	var url = getUrl();
	
	console.log(order);
	console.log(searchkey);
	console.log(keyword);
	
//	
//	if(keyword == ""){
//		location.href = url + '?order=' +order +"&page=" + page;
//	} else {
//		location.href = url + "?page=" + page;
//	}
	

}

//리스트 정렬 ajax 통신
function orderSelect(f){
	var order = $("#order").val();
	
	$.ajax({
		type : "get",
		data : { "order": order },
		success : function(){
		},
        error: function () {//에러
            location.href = path + "/error/error.jsp";
        }
	});
}

//검색어 
function listSearch(f){
	var searchkey = $("#searchkey").val()
	var keyword = $("#keyword").val();
	
//	if(searchkey == ""){
//		alert("검색옵션을 선택해주세요");
//		return false;
//	}
//	if(keyword == ""){
//		alert("검색어를 입력해주세요.");
//		$("#keyword").focus();
//		return false;
//	}
	
	$.ajax({
		type : "get",
		data : {"searchkey" : searchkey, "keyword" : keyword},
		success : function(){
		},
        error: function () {//에러
            location.href = path + "/error/error.jsp";
        }
	});
}



