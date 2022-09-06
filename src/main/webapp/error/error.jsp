<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" type="text/css" href="${path}/Admin/resources/asset/css/common.css">
	<title>에러 페이지</title>
</head>
<body>
	<div id="error-wrap">
		<div class="info">
			<h1 class="title">Error :(</h1>
			<p class="desc">페이지에 문제가 있나봐요. <br> 잠시후에 다시 시도해 주세요!</p>
			<button type="button" onclick="history.back()">돌아가기</button>
		</div>
	</div>
</body>
</html>