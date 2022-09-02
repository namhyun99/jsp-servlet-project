<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="load" style="display:none;">
	<img src="${path}/resources/static/images/load_img.gif" alt="loading">
</div>

<div id="login-wrap">
	<div class="login-box">
		<div class="title-wrap">
			<div class="title">
				<span>LOG IN</span>
			</div>
			<div class="subtitle">
				<span>Administration</span>
			</div>
		</div>


		<div class="input-wrap">
			<div class="input-box">
				<input name="userid" id="userid" placeholder="아이디를 입력해주세요"
					autocomplete="off">
			</div>
			<div class="input-box">
				<input type="password" name="passwd" id="passwd"
					placeholder="비밀번호를 입력해주세요">
			</div>
			<p id="loginError" class="error-msg help error"></p>
		</div>

		<div class="info-wrap">
			<div class="save-box">
				<div>
					<input type="checkbox" id="chkSave" onclick="checkSaveId()">
				</div>
				<div>
					<span>아이디저장</span>
				</div>
			</div>
			<div class="remind-box">
				<a href="#" onclick="remindPopOpen()"><span>ID/PW찾기</span></a>
			</div>
		</div>

		<div class="button-wrap">
			<input type="button" onclick="loginAction()" value="로그인">
		</div>
	</div>
</div>


<script type="text/javascript">
        $(function () {
            //쿠키값 확인
            var cookie_userid = getCookie("userid");
            
            if (cookie_userid != "") {
                $("#userid").val(cookie_userid);
                $("#chkSave").attr("checked", 'checked');
            }
            
            $("#userid").focusout(function () {
                var userid = $("#userid").val();
                
                if (userid == "") {
                    $("#useridError").show();
                    $("#userid").addClass("error");
                } else {
                    $("#useridError").hide();
                    $("#userid").removeClass("error");
                }
            })

            //엔터키를 입력하면 
            $("#passwd").keydown(function (e) {
                if (e.keyCode == 13) {
                    loginAction('${path}');
                }
            })
        })
    </script>

<script type="text/javascript">
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
            var today = new Date();
            today.setDate(today.getDate() + days);
            //쿠키변수명=쿠키값; path=저장경로; expires=쿠키유효기간;
            document.cookie = name + "=" + value + ";path=${path}" + ";expires=" + today.toGMTString() + ";"
        }

        //쿠키값 저장하기
        function saveCookie(id) {
            if (id != "") {
                setCookie("userid", id, 7); //7일
            } else {
                setCookie("userid", id, -1); //삭제
            }
        }
    </script>

</body>
</html>