<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.userid != null}">
	<script>
		alert("이미 로그인 되어 있습니다.");
		location.href="${path}/admin/member/userList";
	</script>
</c:if>