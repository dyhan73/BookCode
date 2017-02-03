<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>첫번째 뷰</title>
</head>
<body>

<p> 이름을 입력하세요. </p>

<form method="post">
	<label for ="userName">사용자 이름 : </label>
	<input type="text" name="userName" value="${requestscope_start_username}"/>
	<br>
	<br>
	<input type="submit" name="_eventId_toSecond" value="두번째 뷰"/>
</form>
</body>
</html>