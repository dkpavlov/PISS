<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/cms.css"  />"/>
</head>
<body>
<div id="path">
    <span>Административен панел >> Потребители >> Всички потребители >> ${user.username} >> Смяна на паролата</span>
</div>
<div class="main-area">
    <form:form method="post" commandName="user">
        <div class="promote">
            <label>Нова парола</label>
            <input name="newPassword" type="password" class="form-control form-control-date"/>
            <label>Потвърди паролата</label>
            <input name="confirmPassword" type="password" class="form-control form-control-date"/>
            <form:hidden path="id"/>
            <br/>
            <c:if test="${flag == '1'}">Паролите не съвпадат</c:if>
            <input type="submit" value="Запази" class="btn btn-default">
            <input type="button" class="btn btn-default" onclick="window.location='${pageContext.request.contextPath}/cms/admin/user';" value="Отказ">
        </div>
    </form:form>
</div>
</body>
</html>