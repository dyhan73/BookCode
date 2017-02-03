<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>고객 정보 입력</title>
</head>
<body>
<h3>고객 정보 입력</h3>
<fieldset>
<form method="post" action="customer">
  	<label for="name">이름 : </label>
    <input type="text" name="name" /> <br>
    <label for="address">주소 : </label>
    <input type="text" name="address" /> <br>
    <label for="email">이메일 : </label>
    <input type="text" name="email" /> <br>
    <input type="submit" value="저장" />
</form>
</fieldset>
</body>
</html>