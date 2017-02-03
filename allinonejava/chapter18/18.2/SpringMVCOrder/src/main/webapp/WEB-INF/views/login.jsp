<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div>
   <h2>로그인</h2>
   
   <spring:url var="authUrl" 
          value="/static/j_spring_security_check" />
   <form method="post" class="signin" action="${authUrl}">
   
    <fieldset>
    <table cellspacing="0">
    <tr>
    <th><label for="username_or_email">ID</label></th>
    <td><input id="username_or_email" 
               name="j_username" 
               type="text" /> 
      </td>
    </tr>
    <tr>
    <th><label for="password">비밀번호</label></th>
      <td><input id="password" 
                 name="j_password" 
                 type="password" /> 
      </td>
    </tr>
    <tr>
    <th></th>
    <td><input id="remember_me" 
        name="_spring_security_remember_me" 
        type="checkbox"/>
        <label for="remember_me" 
               class="inline">로그인 저장</label></td>
    </tr>
    <tr>
    <th></th>
    <td><input name="commit" type="submit" value="로그인" /></td>
    </tr>
    </table>
    </fieldset>
   </form>
   
   <script type="text/javascript">
    document.getElementById('username_or_email').focus();
   </script>
</div>