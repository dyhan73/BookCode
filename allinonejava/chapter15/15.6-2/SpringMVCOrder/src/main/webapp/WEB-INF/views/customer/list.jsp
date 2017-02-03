<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객 목록 조회</title>
<style>
th {
background: blue;
color : white;
text-align: center;
}
</style>
</head>
<body>
<h1>고객 목록 조회</h1>
<table>
	<tr><th>이름</th><th>주소</th><th>이메일</th></tr>
<c:forEach var="customer" items="${customers}">
	<tr>
		<td>${customer.name }</td>
		<td>${customer.address}</td>
		<td>${customer.email }</td>
	</tr>	
</c:forEach>
</table>

	<p>
	<hr>
	<a href="/order">홈으로</a>
</body>
</html>