<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Активни потребителски акаунти</h2></br>
    <label>Потребителско име</label>
    <input class="form-control" value="${oldUsername}"/></br>
    <select class="form-control form-control-date">
        <c:forEach items="${rolesList}" var="role">
                <option value="${role}" <c:if test="${not empty oldRole and oldRole eq role}">selected</c:if>>${role.displayValue}</option>
        </c:forEach>
    </select><br/>
    <input type="button" value="Търси">
    <c:if  test="${!empty userList}">
        <table class="data table-striped">
            <tr>
                <th>Потребителско име</th>
                <th>Име и фамилия</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.fullName} </td>
                    <td><input type="button" value="Архивирай" onclick="window.location='${pageContext.request.contextPath}/cms/owner/hotel/${hotel.id}/edit/'"></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
