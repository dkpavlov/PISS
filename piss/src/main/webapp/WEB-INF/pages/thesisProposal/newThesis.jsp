<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>
<div id="path">
    <span>Предложение за дипломна работа</span>
</div>
<div class="main-area">
    <form:form method="post" commandName="thesis" id="form_form">
        <label>Научен ръководител</label>
        <frm:select path="tutor.id" cssClass="form-control form-control-date">
            <c:forEach items="${teachersList}" var="tutor">
                <option value="${tutor.id}">${tutor.fullName}</option>
            </c:forEach>
        </frm:select>

        <label>Консултант</label>
        <frm:select path="consultant.id" cssClass="form-control form-control-date">
            <c:forEach items="${teachersList}" var="tutor">
                <option value="${tutor.id}">${tutor.fullName}</option>
            </c:forEach>
        </frm:select>

        <label>Тема на дипломната работа</label>
        <form:textarea class="form-control" path="thesis"/>
        <label>Анотация</label>
        <form:textarea class="form-control" path="annotation"/>
        <label>Цел на дипломната работа</label>
        <form:textarea class="form-control" path="thesisGoal"/>
        <label>Задачи, произтичащи от целта</label>
        <form:textarea class="form-control" path="tasks"/>
        <label>Ограничаващи/облекчаващи условия</label>
        <form:textarea class="form-control" path="constraints"/>
        <label>Срок за изпълнение</label>
        <form:textarea class="form-control" path="deadline"/>
        <br/>
        <input type="submit" value="Изпрати" id="submit_button"/>
        <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/home'"/>
    </form:form>
</div>
<script>
    $(function(){
        /*TODO Validation for empty fealds*/
    });
</script>