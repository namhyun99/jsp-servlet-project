<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="./include/header.jsp"%>
<%@ include file="./include/top.jsp"%>
	<div class="title" style="display:none">검색</div>
    <div class="sub-screen">
        <div class="search-form">
            <div class="icon"><i class="fas fa-search"></i></div>
            <input id="search" name="keyword" placeholder="검색어를 입력하세요">
        </div>
    </div>
</body>
</html>

