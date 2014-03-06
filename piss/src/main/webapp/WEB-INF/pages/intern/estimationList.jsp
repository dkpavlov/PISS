<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shro" uri="http://shiro.apache.org/tags" %>
<div class="main-area">
    <h2>Оцекни за стаж чакащи одобрение</h2></br>
    <c:if  test="${!empty estimationsList}">
        <table class="data table-striped">
            <tr>
                <th>Студент</th>
                <th>Фирма/Организация</th>
                <th>Тема</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${estimationsList}" var="estimation">
                <tr>
                    <td>${estimation.user.username}</td>
                    <td>${estimation.organizationName}</td>
                    <td>${estimation.score}</td>
                    <td><input type="button" value="Преглед" onclick="window.location='${pageContext.request.contextPath}/internship/estimation/${estimation.id}'"></td>
                    <td><input type="button" value="Одобри" onclick="window.location='${pageContext.request.contextPath}/internship/estimation/${estimation.id}/validate'"></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>