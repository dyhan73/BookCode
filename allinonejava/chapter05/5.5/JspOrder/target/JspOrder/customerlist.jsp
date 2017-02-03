<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page import="com.ensoa.order.domain.Customer" %> 
<%@ page import="java.util.List" %>
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
<%--
<fmt:setLocale value="ko_KR"/>
 

 <fmt:setLocale value="en_US"/>
 <c:set var="localvar" value="<h1>고객 목록 조회</h1>"/>
 --%>
 
<fmt:setBundle basename="message"/>

<c:out value="${localvar}" escapeXml="false" default="<h1>고객 목록 조회</h1>"/>
<p>

이름 : <c:out value="${customer.name}" /> <br>
주소 : <c:out value="${customer.address}" /> <br>
이메일 : <c:out value="${customer.email}" /> <br> 
<p>
<c:if test="${sessionScope.sessionVar == null}">
	<c:set var="sessionVar" value="${3 + 1}"  scope="session"/>
</c:if>
<c:if test="${sessionScope.sessionVar != null}">
세션 영역 변수 sessionVar 의 값은  
<c:choose>
	<c:when test="${sessionScope.sessionVar == 0}">
		0입니다.
	</c:when>
	<c:when test="${sessionScope.sessionVar == 1}">
	   1입니다.
	</c:when>
	<c:otherwise>
	${sessionScope.sessionVar} 입니다.
	</c:otherwise>
</c:choose>
 <br>
<c:remove var="sessionVar" scope="session" /> 
</c:if>
세션 영역 변수 sessionVar 다시 읽기 : <c:out value="${sessionScope.sessionVar}" default="삭제됨" />

<p>


<fmt:bundle basename="message" >
	<fmt:message key="customer.enroll">
		<fmt:param>${customer.name}</fmt:param>
		<fmt:param>${customer.address}</fmt:param>
		<fmt:param>${customer.email}</fmt:param>
	</fmt:message>
</fmt:bundle> 
<br>

<fmt:message key="customer.enroll">
	<fmt:param>${customer.name}</fmt:param>
	<fmt:param>${customer.address}</fmt:param>
	<fmt:param>${customer.email}</fmt:param>
</fmt:message>
<br>
<c:set var="numericvalue" value="1234567"/>
숫자 : <fmt:formatNumber value="${numericvalue}" type="number"/> <br>
화폐 : <fmt:formatNumber value="${numericvalue}" type="currency" /> <br>
<jsp:useBean id="now" class="java.util.Date" />
현재시간 : <c:out value="${now}" /> <br>
날짜 : <fmt:formatDate value="${now}" type="date"/> <br>
시간 : <fmt:formatDate value="${now}" type="time"/> <br>
둘다 표시 : <fmt:formatDate value="${now}" type="both"/> <br>

<table>
	<tr>
		<th><fmt:message key="customer.name"/></th>
		<th><fmt:message key="customer.address"/></th>
		<th><fmt:message key="customer.email"/></th>
	</tr>	
<c:forEach var="customer" items="${customers}">
	<tr>
		<td>${customer.name }</td>
		<td>${customer.address}</td>
		<td>${customer.email }</td>
	</tr>	
</c:forEach>
</table>
<p>
<c:forEach var="customer" items="${customers}" varStatus="status">
	status.begin : ${status.begin} <br>
	status.end : ${status.end} <br>
	status.step : ${status.step } <br>
	status.count : ${status.count } <br>
	status.current : ${status.current } <br>
	status.index : ${status.index } <br>
	status.first : ${status.first } <br>
	status.last : ${status.last } <br>
</c:forEach>
	<p>
<c:remove var="customers" scope="request" />
<c:choose>
	<c:when test="${fn:length(customers) == 0}">
		<i>고객 목록이 없습니다.</i>
	</c:when>
	<c:otherwise>
		<table>
			<tr><th>이름</th><th>주소</th><th>이메일</th></tr>
			<c:forEach var="i" begin="0" end="10">
				<tr>
					<td>${customers[i].name}</td>
					<td>${customers[i].address}</td>
					<td>${customers[i].email }</td>
				</tr>	
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

	<table>
	<tr><th>이름</th><th>주소</th><th>이메일</th></tr>
	<c:import url="customers.xml" var="xml"  charEncoding="UTF-8"/>
	<x:parse xml="${xml}" var="customersDoc" />
	<x:forEach select="$customersDoc/customers/customer">
		<tr>
			<td><x:out select="name"/> </td>
			<td><x:out select="address" /></td>
			<td><x:out select="email" /> </td>
		</tr>
	</x:forEach>
	</table>
	<p>
	<ul>
	<c:forTokens items="김일,김이,김삼,김사,김오" delims="," var="word">
		<li>${word } </li>
	</c:forTokens>
	</ul>
	<c:import url="include.jsp">
		<c:param name="name" value="전병선"/>
	</c:import>
	<%--
	<c:redirect url="include.jsp">
		<c:param name="name" value="전병선"/>
	</c:redirect>
	<c:redirect url="new.jsp" />
	 --%>
	<p>
	<hr>
	<a href="index.html">홈으로</a>
</body>
</html>