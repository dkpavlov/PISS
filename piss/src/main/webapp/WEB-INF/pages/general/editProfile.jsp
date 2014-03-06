<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="path">
    <span>Редактиране на профил на <shro:principal/></span>
</div>
<div class="main-area">
    <form:form method="post" commandName="user" id="form_form">
        <h2><label>Смяна на паролата</label><h2>
        <label>Нова парола</label>
        <input type="password" name="newPassword" class="form-control"/>

        <%--<label>Потвърди парола <span class="text-danger">*</span></label>
        <input type="password" class="form-control" id="validate_password">--%>
        <h2><label>Редактиране на профила</label></h2>
        <label>e-mail</label>
        <form:input type="text" class="form-control" path="email"/>
        <label>Име</label>
        <form:input type="text"  class="form-control" path="firstName"/>
        <label>Презиме</label>
        <form:input type="text"  class="form-control" path="secondName"/>
        <label>Фамилия</label>
        <form:input type="text" class="form-control" path="thirdName"/>
        <label>Телефон</label>
        <form:input type="text" class="form-control" path="phoneNumber"/>
        <br/>
        <input type="submit" value="Изпрати" id="submit_button"/>
        <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/cms'"/>
    </form:form>
</div>
</body>
</html>
<script>
    $(function(){

    });
</script>