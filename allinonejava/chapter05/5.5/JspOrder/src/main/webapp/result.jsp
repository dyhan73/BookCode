<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ensoa.order.domain.Customer" %> 
<jsp:useBean id="customerBean" class="com.ensoa.order.domain.Customer" scope="page"/>
<jsp:useBean id="time" class="java.util.Date" scope="page" />
<jsp:setProperty name="customerBean" property="*" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객 등록 정보</title>
</head>
<body>
<%! 
	public String name = null;
	public String address = null;
	public String email = null;
%>
<%
	name = request.getParameter("name");
	address = request.getParameter("address");
	email = request.getParameter("email");
%>
	<h1>고객 등록 정보</h1>
	이름 :  <%= name %><br>
	주소 :  <%= address %><br>
	이메일 :  <%= email %>
	<p>
	<hr>
	<h1>자바빈으로 전달된 고객 등록 정보</h1>
	이름 :  <%= customerBean.getName() %><br>
	주소 :  <%= customerBean.getAddress() %><br>
	이메일 :  <%= customerBean.getEmail() %>	
	<p>
	<hr>
	<h1>서블릿에서 전달된 고객 등록 정보</h1>
	<%  Customer customer = (Customer)request.getAttribute("customer"); %>
	이름 :  <%= customer.getName() %><br>
	주소 :  <%= customer.getAddress() %><br>
	이메일 :  <%= customer.getEmail() %>	
	<p>
	<hr>
	<h1>표현식 사용 고객 등록 정보</h1>
	이름 :  ${customer.name} <br>
	주소 :  ${customer.address}  <br>
	이메일 :  ${customer.email}	
	<p>
	이름 :  ${param.name} <br>
	주소 :  ${param.address}  <br>
	이메일 :  ${param.email}	
	<p>	
	<hr>
	<jsp:include page="include.jsp" />
	<%-- 
	<%@ include file="include.jsp" %>

	<%-- 
	<jsp:forward page="new.jsp" />
	 --%>
	
	<p>
	<hr>
	<h3>데이터베이스 설정</h3>
	driverClassName : <%= application.getInitParameter("driverClassName") %><br/>
	url : <%= application.getInitParameter("url") %><br/>
	username : <%= application.getInitParameter("username") %><br/>
	password : <%= application.getInitParameter("password") %>
	
	<p>
	<hr>
	현재 시간 : <%= time.toLocaleString() %>
	<p>
	<hr>
	<a href="index.html">홈으로</a>
</body>
</html>