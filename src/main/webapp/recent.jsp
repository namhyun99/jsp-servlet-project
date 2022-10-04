<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="./include/header.jsp"%>
<%@ include file="./include/top.jsp"%>

<!--contents-->
<div id="contents-wrap" class="screen">
	<nav id="nav">
		<div class="option-wrap">
			<span> <a href="./main" class="noselected"> <i
					class="fas fa-chart-line"></i> 트렌딩
			</a>
			</span> <span class="title"> <a href="./recent" class="selected">
					<i class="far fa-clock"></i> 최신
			</a>
			</span>
		</div>
	</nav>

	<section id="list-wrap">
		<c:forEach var="dto" items="${list}">
			<div class="items">
				<a href="./board/view?c_idx=${dto.c_idx}">
					<div class="thumb_img">
						<c:choose>
							<c:when test="${dto.filesize > 0}">
								<img src="/thumbnail/${dto.filename}">
							</c:when>
							<c:otherwise>
								<img src="./resources/asset/images/no_thumb.png">
							</c:otherwise>
						</c:choose>
					</div>
				</a>
				<div class="text-wrap">
					<a href="./board/view?c_idx=${dto.c_idx}">
						<div class="contents">
							<h3>${dto.subject}</h3>
							<div class="desc">
								<p>${dto.content}</p>
							</div>
						</div>
					</a>
					<div class="sub-info">
						<span> <fmt:formatDate value="${dto.write_date}"
								pattern="yyyy년 M월 d일" />
						</span> <span> · ${dto.cmt_count}개의 댓글</span>
					</div>
					<div class="user-info">
						<div class="profile">
							<c:choose>
								<c:when test="${dto.profile_img != '-'}">
									<img src="/profile/${dto.profile_img}">
								</c:when>
								<c:otherwise>
									<img src="./resources/asset/images/no_profile.png">
								</c:otherwise>
							</c:choose>
							by.<span>${dto.userid}</span>
						</div>
						<div class="view-count">
							<i class="fas fa-eye"></i><span>${dto.view_cnt}</span>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</section>
</div>

<script type="text/javascript">
	var curPage = 1;
	var isLoading = false;

	$(window).on("scroll", function() {
		var scrollTop = $(window).scrollTop(); // 위로 스크롤된 길이
		var windowsHeight = $(window).height(); //웹브라우저의 창의 높이
		var documentHeight = $(document).height(); // 문서 전체의 높이
		var isBottom = scrollTop + windowsHeight + 10 >= documentHeight;
		
		if (isBottom) {
			//만일 현재 마지막 페이지라면
			if (curPage >= ${totPage}) {
				return false; //함수종료
			} else {
				isLoading = true; //위에서 종료되지 않으면 로딩상태를 true로 변경
				$("#load").show(); //로딩바 표시
				curPage++; //현재페이지 1증가
				getList(curPage); //추가로 받을 리스트 ajax처리
			}
		}
	});

	function getList(curPage) {
		$.ajax({
			type: "get",
			url : "${path}/ajax_page.do",
			data : { "curPage" : curPage,
					 "order" : "write_date"},
			success:function(data){
				$("#list-wrap").append(data);
				$("#load").hide(); //로딩바 숨기기
				isLoading = false;
			}
		});
	}
</script>

</body>
</html>
