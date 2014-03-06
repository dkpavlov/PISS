<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="path">
    <span>Нов индивидуален план</span>
</div>
<div class="main-area">
    <%--TODO ako e edit da se populvat datite--%>
    <form:form method="post" commandName="plan" id="form_form"><br/>
        <label>Тема на дисертционната работа</label>
        <form:input path="thesis" cssClass="form-control"/>
        <label>Дата на утвърждаване</label>
        <form:input type="text" path="dateThesisAsString" id="firstDate" class="form-control form-control-date"/>
        <label>№ на протокола</label>
        <form:input type="text" path="protocolNumber" cssClass="form-control form-control-date"/>
        <label>Вх. № на доклад на деканата</label>
        <form:input type="text" path="deanReportNumber" cssClass="form-control form-control-date"/>
        <label>От дата</label>
        <form:input type="text" path="deanReportDateAsString" id="secondDate" class="form-control form-control-date"/><br/>
        <label>Научен ръководител</label>
        <form:select path="head.id" cssClass="form-control-date form-control">
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.firstName}</option>
            </c:forEach>
        </form:select><br/>
        <input type="submit" value="Запис"/>
    </form:form>
</div>
<script>
    var committee_count = 2;
    $(function(){
        $('#firstDate').datepicker({ dateFormat: 'dd/mm/yy'});
        $('#secondDate').datepicker({ dateFormat: 'dd/mm/yy'});

    });
</script>