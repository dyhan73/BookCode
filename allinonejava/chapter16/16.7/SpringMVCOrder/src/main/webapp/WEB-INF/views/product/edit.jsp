<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제품 정보 등록</title>
<style>
.error {
background: white;
color : red;
text-align: left;
}
</style>
</head>
<body>
<h3>제품 정보 입력</h3>
<fieldset>
<form:form method="post" modelAttribute="product" enctype="multipart/form-data">
  	<form:label path="name" cssErrorClass="error">제품명 : </form:label>
    <form:input type="text" path="name" /> 
    <form:errors path="name" cssClass="error"/> <br>
    <form:label path="price" cssErrorClass="error">가격 : </form:label>
    <form:input type="text" path="price" /> 
    <form:errors path="price" cssClass="error"/><br>
    <form:label path="description" cssErrorClass="error">설명 : </form:label>
    <form:input type="text" path="description" /> 
    <form:errors path="description" cssClass="error"/><br>
    <input name="image" type="file" /> <br>
    <input type="submit" value="저장" />
</form:form>
</fieldset>
</body>
</html>