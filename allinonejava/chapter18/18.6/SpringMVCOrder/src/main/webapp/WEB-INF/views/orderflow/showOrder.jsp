 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<html> 
  <head><title>주문</title></head>
  <body>
    <h2>주문 정보</h2>

    <h3>고객정보:</h3>
		<b>${order.customer.name}</b><br/>
		<b>${order.customer.address}</b><br/>
		<b>${order.customer.email}</b><br/>
		<hr/>
		<h3>주문 합계: ${order.total}</h3>
		<hr/>
		<h3>주문 내역:</h3>
		
		<c:if test="${fn:length(order.items) eq 0}">
		<b>주문내역이 없습니다.</b>
		</c:if>
		
		<br/>
		<c:forEach items="${order.items}" var="item">
			<li>
		    <c:out value="${item.product.name}" />,
		    <c:out value="${item.amount}" /> 개
		    </li>		    
		</c:forEach>

    <form:form>
      <input type="hidden" name="_flowExecutionKey"  value="${flowExecutionKey}"/>
      <input type="submit" name="_eventId_selectProduct"  value="제품 선택" />
    <c:if test="${fn:length(order.items) gt 0}">
      <input type="submit" name="_eventId_orderPlaced"  value="주문" />
    </c:if>
      <input type="submit" name="_eventId_cancel" value="취소" />
    </form:form>
    
<a href=“${flowExecutionUrl}&_eventId=selectProduct">제품 선택</a>
<a href=“${flowExecutionUrl}&_eventId_orderPlaced">주문</a>


	</body>
</html>