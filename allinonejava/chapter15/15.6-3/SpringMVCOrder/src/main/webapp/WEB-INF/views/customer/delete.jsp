<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="customer.title"/></title>
<style>
.error {
background: white;
color : red;
text-align: left;
}
</style>
</head>
<body>
<h3><spring:message code="customer.delete"/></h3>
<fieldset>
<form:form method="delete" modelAttribute="customer">
	<form:label path="id">ID : </form:label>
    <form:input type="text" path="id" /> 
    <input type="submit" value=<spring:message code="delete"/> />
</form:form>
</fieldset>
</body>
</html>