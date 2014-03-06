<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/cms.css"  />"/>
</head>
<body>


<h3>Преглед на потребител</h3>
</br>
<table>
    <tr>
        <td><label>Потребителско име:</label></td>
        <td><label>${user.username}</label></td>
    </tr>
    <tr>
        <td><label>Име:</label></td>
        <td><label>${user.fullName}</label></td>
    </tr>
    <tr>
        <td><label>Email:</label></td>
        <td><label>${user.email}</label></td>
    </tr>
    <tr>
        <td><label>Телефонен номер:</label></td>
        <td><label>${user.phoneNumber}</label></td>
    </tr>
    <br/>
    <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/user/${user.id}/archive';" value="Архивирай"></td>
    <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/user/${user.id}/validate';" value="Одобри"></td>
    <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/admin/forValidation';" value="Назад"></td>
    <tr>
    </tr>
</table>
<br>

</body>
</html>