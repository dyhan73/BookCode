<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="customer/edit.do">고객 등록</a> <br>
<a href="customer/list.do">고객 목록 조회</a><br>
<a href="customer/search.do">고객 검색</a><br>
<p>
<a href="product/edit.do">제품 등록</a> <br>
<a href="product/list.do">제품 목록 조회</a>
</body>
</html>
