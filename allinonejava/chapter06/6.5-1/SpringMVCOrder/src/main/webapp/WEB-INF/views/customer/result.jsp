<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객 등록 정보</title>
</head>
<body>
	<h1>고객 등록 정보</h1>
	이름 :  ${customer.name} <br>
	주소 :  ${customer.address}  <br>
	이메일 :  ${customer.email}	
	
	<p>
	<hr>
	<a href="/order">홈으로</a>
</body>
</html>