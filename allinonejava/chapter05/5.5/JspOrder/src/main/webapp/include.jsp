<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2>포함된 JSP 파일</h2>
<%
	out.println(request.getParameter("name") + " 님이 등록되었습니다.");
%>
<br/><br/>
반갑습니다 <%= request.getParameter("name") %> 님!