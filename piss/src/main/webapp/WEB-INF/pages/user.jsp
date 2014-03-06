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

<h3>All users</h3>
</br>
<c:if  test="${!empty users}">
    <table class="data">
        <tr>
            <th>Username</th>
            <th>Role</th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td width="130px"><a href="${pageContext.request.contextPath}/user/${user.id}"><label>${user.username}</label></a></td>
            <c:forEach items="${user.role}" var="ro">
                <td><a href="${pageContext.request.contextPath}/user/${user.id}"><label>${ro.displayValue}</label></a></td>
            </c:forEach>
        </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>