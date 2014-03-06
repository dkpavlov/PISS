<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Предложения за дипломи работи чакащи одобрение</h2></br>
    <c:if  test="${!empty thesisList}">
        <table class="data table-striped">
            <tr>
                <th>Име и фамилия</th>
                <th>ФН</th>
                <th>Специалност</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${thesisList}" var="thesis">
                <tr>
                    <td>${thesis.user.fullName}</td>
                    <td>${thesis.user.fn}</td>
                    <td>${thesis.user.speciality}</td>
                    <td><input type="button" value="Преглед" onclick="window.location='${pageContext.request.contextPath}/thesis/${thesis.id}'"></td>
                    <td><input type="button" value="Печат" onclick="window.location='${pageContext.request.contextPath}/thesis/${thesis.id}/export'"></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>