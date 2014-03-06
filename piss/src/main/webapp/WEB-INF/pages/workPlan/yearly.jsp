<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Въвеждане на работен план по години</h2></br>
    <table class="data table-striped">
        <tr>
            <th>Година</th>
            <th>Статус</th>
            <th>&nbsp;</th>
        </tr>
        <tr>
            <td>Първа</td>
            <td><c:if test="${not empty yearOne}">Попълнен</c:if><c:if test="${empty yearOne}">Непопълнен</c:if></td>
            <td><input type="button" value="Добави" onclick="window.location='${pageContext.request.contextPath}/plan/yearly/1'"></td>
        </tr>
        <tr>
            <td>Втора</td>
            <td><c:if test="${not empty yearTwo}">Попълнен</c:if><c:if test="${empty yearTwo}">Непопълнен</c:if></td>
            <td><input type="button" value="Добави" onclick="window.location='${pageContext.request.contextPath}/plan/yearly/2'"></td>
        </tr>
        <tr>
            <td>Трета</td>
            <td><c:if test="${not empty yearThree}">Попълнен</c:if><c:if test="${empty yearThree}">Непопълнен</c:if></td>
            <td><input type="button" value="Добави" onclick="window.location='${pageContext.request.contextPath}/plan/yearly/3'"></td>
        </tr>
    </table>
</div>
