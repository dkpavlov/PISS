<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Дипломи работи чакащи рецензия</h2></br>
    <c:if  test="${!empty thesisList}">
        <table class="data table-striped">
            <tr>
                <th>Име и фамилия</th>
                <th>ФН</th>
                <th>Специалност</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${thesisList}" var="thesis">
                <tr>
                    <td>${thesis.thesisProposal.user.fullName}</td>
                    <td>${thesis.thesisProposal.user.fn}</td>
                    <td>${thesis.thesisProposal.user.speciality}</td>
                    <td><input type="button" value="Преглед" onclick="window.location='${pageContext.request.contextPath}/thesis/final/${thesis.id}'"></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>