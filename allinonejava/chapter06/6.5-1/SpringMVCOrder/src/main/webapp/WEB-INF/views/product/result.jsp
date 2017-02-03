<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 등록 정보</title>
</head>
<body>
	<h1>제품 등록 정보</h1>
	제품명 :  ${product.name} <br>
	가격 :  ${product.price}  <br>
	제품 설명 :  ${product.description}	
	
	<p>
	<hr>
	<a href="/order">홈으로</a>
</body>
</html>