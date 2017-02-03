<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  
  <head><title>주문</title></head>
  <body>
	<h2>제품 주문</h2>
	
	<form:form commandName="selectedItem">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
		제품 : <form:select path="product"  itemValue="id" itemLabel="name"  items="${products}" /><br>
		수량 : <form:input path="amount" /><p>
		<input type="submit" name="_eventId_addItem" value="계속"/>
		<input type="submit" name="_eventId_cancel" value="취소" />
	</form:form>
  </body>
</html>