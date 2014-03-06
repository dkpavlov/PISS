<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title></title>
</head>
<body>

<span style="float: right">
    <a href="?lang=bg_BG">bg</a>
    |
    <a href="?lang=en_US">en</a>
</span>

<h3>User</h3>
</br>
<c:if  test="${!empty user}">
    <label>Username:</label> <label>${user.username}</label></br>
    <label>Role:</label> <label>${user.role}</label></br>
    <label>First name:</label> <label>${user.firstName}</label></br>
    <label>Second name:</label> <label>${user.secondName}</label></br>
    <label>Third Name:</label> <label>${user.thirdName}</label></br>
    <label>Email:</label> <label>${user.email}</label>
    </br>
    <input type="button" onclick="window.location='${pageContext.request.contextPath}/user/${user.id}/edit';" value="Edit">
    <input type="button" onclick="window.location='${pageContext.request.contextPath}/user/all';" value="Back">
</c:if>

</body>
</html>