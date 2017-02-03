<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  
  <head><title>주문</title></head>

  <body>
    <h2>고객 등록 거절</h2>
    
   ${customer.name }씨를 고객으로 등록할 수 없습니다!!!
    
    <p>
    <a href="${flowExecutionUrl}&_eventId_cancel">확인</a>

  </body>
</html>