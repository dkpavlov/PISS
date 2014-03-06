<%@ page import="org.apache.shiro.authc.AuthenticationException" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
</head>

<body>

	<div id="login_page_content">
		<div id="login_header">
			<div class="line">
			</div>
		</div>
		<form method="post">
			<div>
			    <span>Потребител:</span>
                <input name="username" type="text" class="form-control form-control-date"/>
                <span>Парола:</span>
			    <input name="password" type="password" class="form-control form-control-date"/>
                <br/>
			<%
            String errorDescription = (String) request.getAttribute("shiroLoginFailure");
             //   AuthenticationException exception =  (AuthenticationException) request.getAttribute("shiroLoginFailure");
                if (errorDescription!=null) {
                String error="Неуспешен опит";
                if (errorDescription.equals("org.apache.shiro.authc.UnknownAccountException"))
                {
                   error = "Неразпознат потребител";
                }else if (errorDescription.equals("org.apache.shiro.authc.IncorrectCredentialsException"))
                {
                    error = "Некоректна парола";
                }
        %>

               <span class="error"> <%=error%></span>
        <%
            }
        %>
			<input type="submit" name="submit" value="Вход"/>
			</div>

		</form>
        <div class="clear"></div>
		<div id="login_footer">
			<div class="line">
			</div>
		</div>
	</div>

</body>

</html>