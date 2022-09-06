<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" type="text/css" href="${path}/Admin/resources/asset/css/summernote-lite.css">
    	<link rel="stylesheet" type="text/css" href="${path}/Admin/resources/asset/css/common.css">

    	<script type="text/javascript" src="${path}/Admin/resources/asset/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript" src="${path}/Admin/resources/asset/js/summernote-lite.js"></script>
		<script type="text/javascript" src="${path}/Admin/resources/asset/js/summernote-ko-KR.js"></script>
    	<script type="text/javascript" src="${path}/Admin/resources/asset/js/common.js"></script>
    	<script type="text/javascript" src="${path}/Admin/resources/asset/js/login.js"></script>
		<script src="https://kit.fontawesome.com/fbdfc0aa10.js"></script>
    	<script type="text/javascript">
        	sessionStorage.setItem("contextPath", "${path}");
        	sessionStorage.setItem("url", "${url}");
    	</script>

    	<title>관리자 페이지</title>
	</head>
