<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  
   <head>
   <title>주문</title>
   <style>
	.error {
		background: white;
		color : red;
		text-align: left;
	}
  </style>
  </head>

  <body>
    <h2>고객 등록</h2>
    
    <form:form method="post" modelAttribute="customer">
      	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
    	<form:label path="name" cssErrorClass="error"><spring:message code="customer.name"/>: </form:label>
    	<form:input type="text" path="name" /> 
    	<form:errors path="name" cssClass="error"/> <br> 
		<form:label path="address" cssErrorClass="error"><spring:message code="customer.address"/> : </form:label>
    	<form:input type="text" path="address" size="60" /> 
    	<form:errors path="address" cssClass="error"/> <br>
		<form:label path="email" cssErrorClass="error"><spring:message code="customer.email"/> : </form:label>
    	<form:input type="text" path="email" /> 
    	<form:errors path="email" cssClass="error"/> <br> 	     	
      	<input type="submit" name="_eventId_save"  value="저장" />
      	<input type="submit" name="_eventId_cancel" value="취소" />
    </form:form>
	</body>
</html>