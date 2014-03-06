<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function(){
        $("#search").click(function(){
            window.location = "${pageContext.request.contextPath}/admin/forValidation?role="+$("#role_select").val();
        });
    });
</script>
<div class="main-area">
    <h2>Потребители чакащи одобрение</h2></br>
    <select class="form-control form-control-date" id="role_select">
        <c:forEach items="${roles}" var="role">
            <option value="${role}" <c:if test="${not empty oldRole and oldRole eq role}">selected </c:if>>${role.displayValue}</option>
        </c:forEach>
    </select><br/>
    <input type="button" value="Търси" id="search">
    <c:if  test="${!empty users}">
        <table class="data table-striped">
            <tr>
                <th>Потребителско име</th>
                <th>Име и фамилия</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.fullName}</td>
                    <td><input type="button" value="Преглед" onclick="window.location='${pageContext.request.contextPath}/admin/user/${user.id}'"></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
