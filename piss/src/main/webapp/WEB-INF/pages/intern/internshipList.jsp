<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shro" uri="http://shiro.apache.org/tags" %>
<div class="main-area">
    <h2>Молби за стаж чакащи одобрение</h2></br>
    <c:if  test="${!empty internshipList}">
        <table class="data table-striped">
            <tr>
                <th>Студент</th>
                <th>Фирма/Организация</th>
                <th>Тема</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${internshipList}" var="internship">
                <tr>
                    <td>${internship.user.username}</td>
                    <td>${internship.organizationName}</td>
                    <td>${internship.internshipTheame}</td>
                    <td><input type="button" value="Преглед" onclick="window.location='${pageContext.request.contextPath}/internship/${internship.id}'"></td>
                    <td><input type="button" value="Одобри" onclick="window.location='${pageContext.request.contextPath}/internship/${internship.id}/validate'"></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>