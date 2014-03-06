<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-area">
    <h3>Преглед на молба за стаж</h3>
    </br>
    <table>
        <tr>
            <td><label>От Фирма/Организация:</label></td>
            <td><label>${internship.organizationName}</label></td>
        </tr>
        <tr>
            <td><label>Лице за контакт:</label></td>
            <td><label>${internship.personForContact}</label></td>
        </tr>
        <tr>
            <td><label>Телефон:</label></td>
            <td><label>${internship.phoneNumber}</label></td>
        </tr>
        <tr>
            <td><label>Е-поща:</label></td>
            <td><label>${internship.email}</label></td>
        </tr>
        <tr>
            <td><label>Стажант:</label></td>
            <td><label>${internship.user.fullName} ${internship.user.fn} ${internship.user.speciality} ${internship.user.email} ${internship.user.phoneNumber}</label></td>
        </tr>
        <tr>
            <td><label>Тема на стажа:</label></td>
            <td><label>${internship.internshipTheame}</label></td>
        </tr>
        <tr>
            <td><label>Анотация:</label></td>
            <td><label>${internship.internshipGoal}</label></td>
        </tr>
        <tr>
            <td><label>Цел на стажа:</label></td>
            <td><label>${internship.annotation}</label></td>
        </tr>
        <tr>
            <td><label>Задачи, произтичащи от целта:</label></td>
            <td><label>${internship.problemsFromGoal}</label></td>
        </tr>
        <tr>
            <td><label>Ограничаващи/облекчаващи условия:</label></td>
            <td><label>${internship.constraints}</label></td>
        </tr>
        <tr>
            <td><label>Продължителност на проекта:</label></td>
            <td><label>${internship.durationInHours}</label></td>
        </tr>
        <tr>
            <td><label>Работно място:</label></td>
            <%--TODO da se proweli poleto raboti li--%>
            <td><label></label></td>
        </tr>
        <tr>
            <td><label>Дата:</label></td>
            <td><label>${internship.dateCreated}</label></td>
        </tr>
        <br/>

        <tr>

        </tr>
    </table>
    <br>
    <input type="button" value="Одобри" id="save" onclick="window.location='${pageContext.request.contextPath}/internship/${internship.id}/validate';"/>
    <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/internship/forValidation';" value="Назад"></td>
</div>
<script>
</script>
