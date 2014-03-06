<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<span style="float: right">
    <a href="?lang=bg_BG">bg</a>
    |
    <a href="?lang=en_US">en</a>
</span>

<h3><c:if  test="${!empty oldUser}">Edit</c:if><c:if  test="${empty oldUser}">New</c:if> user</h3>
</br>
<form:form method="post" commandName="user">
    <table>
        <c:if  test="${empty oldUser}">
            <tr>
                <td><form:label path="username">Username:</form:label></td>
                <td><form:input path="username"/></td>
                <td><form:errors path="username" cssClass="text-danger" ></form:errors></td>
            </tr>
        </c:if>
        <c:if  test="${empty oldUser}">
            <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td><form:input path="password"/></td>
                <td><form:errors path="password" cssClass="text-danger" ></form:errors></td>
            </tr>
        </c:if>
        <c:if  test="${empty oldUser}">
            <tr>
                <td><form:label path="role">Role:</form:label></td>
                <c:forEach items="${roles}" var="role">
                    <td><form:checkbox path="role" value="${role.key}"/> <label>${role.value}</label></td>
                </c:forEach>
            </tr>
        </c:if>
        <tr>
            <td><form:label path="firstName">First name:</form:label></td>
            <c:if  test="${!empty oldUser}">
                <td><form:input path="firstName"  value="${oldUser.firstName}"/></td>
            </c:if>
            <c:if  test="${empty oldUser}">
                <td><form:input path="firstName"/></td>
            </c:if>
        </tr>
        <tr>
            <td><form:label path="secondName">Second name:</form:label></td>
            <c:if  test="${!empty oldUser}">
                <td><form:input path="secondName"  value="${oldUser.secondName}"/></td>
            </c:if>
            <c:if  test="${empty oldUser}">
                <td><form:input path="secondName"/></td>
            </c:if>
        </tr>
        <tr>
            <td><form:label path="thirdName">Third Name:</form:label></td>
            <c:if  test="${!empty oldUser}">
                <td><form:input path="thirdName"  value="${oldUser.thirdName}"/></td>
            </c:if>
            <c:if  test="${empty oldUser}">
                <td><form:input path="thirdName"/></td>
            </c:if>
        </tr>
        <tr>
            <td><form:label path="email">Email:</form:label></td>
            <c:if  test="${!empty oldUser}">
                <td><form:input path="email"  value="${oldUser.email}"/></td>
                <td><form:errors path="email" cssClass="text-danger" ></form:errors></td>
            </c:if>
            <c:if  test="${empty oldUser}">
                <td><form:input path="email"/></td>
                <td><form:errors path="email" cssClass="text-danger" ></form:errors></td>
            </c:if>
        </tr>
            <td><input type="submit" value="Save"></td>
            <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/user/all';" value="Back"></td>
        <tr>
        </tr>
    </table>
    <br>

</form:form>

</body>
</html>