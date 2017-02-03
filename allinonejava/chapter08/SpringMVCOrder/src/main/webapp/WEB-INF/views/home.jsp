<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="customer/edit.do?lang=ko">고객 등록</a> <br>
<a href="customer/edit.do?lang=en">Customer Enrollment</a> <br>
<a href="customer/list.do">고객 목록 조회</a><br>
<a href="customer/search.do">고객 검색</a><br>
<p>
<a href="product/edit.do">제품 등록</a> <br>
<a href="product/list.do">제품 목록 조회</a><br>
<a href="product/listjr.do?format=pdf">제품 목록 JasperReports PDF</a><br>
<a href="product/listjr.do?format=xls">제품 목록 JasperReports Excel</a><br>
<a href="product/listjr.do?format=csv">제품 목록 JasperReports CSV</a><br>
<a href="product/listjr.do?format=html">제품 목록 JasperReports HTML</a>
<p>
<a href="/order/order/orderprocess" >주문</a> <br>
<a href="/order/order/scope" >플로우 변수 영역</a> 

<hr>
<spring:url var="authUrl" value="/static/j_spring_security_check" />
<security:authorize access="isAnonymous()">
    <form method="post" action="${authUrl}">
        ID : <input name="j_username" type="text" />&nbsp;
        비밀번호 : <input name="j_password" type="password" />  &nbsp;
        <input type="submit" value="로그인" />
	</form>
</security:authorize>

<c:url var="logoutUrl" value="/static/j_spring_security_logout" />
<security:authorize access="isAuthenticated()" >
	<security:authentication property="principal.username" />로 로그인되었습니다.<br>
	<a href="${logoutUrl }">로그아웃</a>
</security:authorize>
<p>
<security:authorize access="hasRole('ROLE_ADMIN')" >
	<a href="./admin.do">관리</a>
</security:authorize>
</body>
</html>
