<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-area">
    <h3>Преглед на оценка от стаж</h3>
    </br>
    <table>
        <tr>
            <td><label>от Ръководител:</label></td>
            <td><label>${estimation.manager}</label></td>
        </tr>
        <tr>
            <td><label>От Фирма/Организация:</label></td>
            <td><label>${estimation.organizationName}</label></td>
        </tr>
        <tr>
            <td><label>Телефон:</label></td>
            <td><label>${estimation.phoneNumber}</label></td>
        </tr>
        <tr>
            <td><label>Е-поща:</label></td>
            <td><label>${estimation.email}</label></td>
        </tr>
        <tr>
            <td><label>Стажант:</label></td>
            <td><label>${estimation.user.fullName} ${estimation.user.fn} ${estimation.user.speciality} ${estimation.user.email} ${estimation.user.phoneNumber}</label></td>
        </tr>
        <tr>
            <td><label>Тема на стажа:</label></td>
            <td><label>${estimation.internshipTheame}</label></td>
        </tr>
        <tr>
            <td><label>Мнение за степента на изпълнение на целите и задачите на стажа:</label></td>
            <td><label>${estimation.opinionOnFulfillment}</label></td>
        </tr>
        <tr>
            <td><label>Постигнати резултати:</label></td>
            <td><label>${estimation.resultsAchieved}</label></td>
        </tr>
        <tr>
            <td><label>Придобити умения по време на стажа:</label></td>
            <td><label>${estimation.gainedExperience}</label></td>
        </tr>
        <tr>
            <td><label>Спазен ли е срокът за изпълнение и , ако не , по какви причини:</label></td>
            <td><label>${estimation.projectDuration}</label></td>
        </tr>
        <tr>
            <td><label>Мнение за професионалната подготовка на стажанта:</label></td>
            <td><label>${estimation.viewOfIntern}</label></td>
        </tr>
        <tr>
            <td><label>Обща оценка:</label></td>
            <td><label>${estimation.score}</label></td>
        </tr>
        <tr>
            <td><label>Препоръки към стажанта:</label></td>
            <td><label>${estimation.recommendationsForIntern}</label></td>
        </tr>
        <tr>
            <td><label>Дата:</label></td>
            <td><label>${estimation.dateCreated}</label></td>
        </tr>
        <br/>

        <tr>

        </tr>
    </table>
    <br>
    <input type="button" value="Одобри" id="save" onclick="window.location='${pageContext.request.contextPath}/internship/estimation${estimation.id}/validate';"/>
    <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/internship/estimation/forValidation';" value="Назад"></td>
</div>
<script>
</script>
