<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  
  <head><title>주문</title></head>

  <body>
    <h2>이름을 입력하세요:</h2>

    <form:form>
      <input type="hidden" name="_flowExecutionKey"   value="${flowExecutionKey}"/> 
      <input type="text" name="name"/><p>
      <input type="submit" name="_eventId_nameEntered" value="고객 조회" />  
    </form:form>
  </body>
</html>