<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="customer.info"/></title>
</head>
<body>
	<h1><spring:message code="customer.info"/></h1>
	<spring:message code="customer.name"/> :  ${customer.name} <br>
	<spring:message code="customer.address"/> :  ${customer.address}  <br>
	<spring:message code="customer.email"/> :  ${customer.email}	
	<p>
	<spring:message code="customer.enroll">
		<spring:argument value="${customer.name}" />
		<spring:argument value="${customer.address}" />
		<spring:argument value="${customer.email}" />
	</spring:message>
	<hr>
	<a href="/order"><spring:message code="home"/></a>
</body>
</html>