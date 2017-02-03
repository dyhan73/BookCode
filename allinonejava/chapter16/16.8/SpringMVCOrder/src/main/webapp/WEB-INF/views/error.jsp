<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러 페이지</title>
</head>
<body>
<h1>에러가 발생했습니다.</h1>

<c:choose>
	<c:when test="${ fn:length(customer.name)== 0}">
		<i>에러가 발생했습니다!!!.</i>
	</c:when>
	<c:otherwise>
		<i>${customer.name} 고객 정보를 저장하는 중에 에러가 발생했습니다.!!!</i>
	</c:otherwise>
</c:choose>

</body>
</html>