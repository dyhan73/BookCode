<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 목록 조회</title>
</head>
<body>
<h1>제품 목록 조회</h1>
<table>
	<tr><th>제품명</th><th>가격</th><th>제품 설명</th></tr>
<c:forEach var="product" items="${products}">
	<tr>
		<td>${product.name }</td>
		<td>${product.price}</td>
		<td>${product.description }</td>
	</tr>	
</c:forEach>
</table>

	<p>
	<hr>
	<a href="/order">홈으로</a>
</body>
</html>