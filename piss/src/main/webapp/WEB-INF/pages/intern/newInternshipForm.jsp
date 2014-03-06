<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
</head>
<body>
<div id="path">
    <span>Предложение за стаж </span>
</div>
<div class="main-area">
    <c:if test="${not empty flag and flag eq '1'}">
        <h4>Вече е въведено предложение за стаж от вашия акаунт</h4>
    </c:if>
    <c:if test="${empty flag}">
        <form:form method="post" commandName="internship" id="form_form">
            <label>От Фирма/Организация</label>
            <form:input path="organizationName" class="form-control" ></form:input>
            <label>Лице за контакт</label>
            <form:input path="personForContact" class="form-control" id="password"/>
            <label>Телефон</label>
            <form:input type="text" class="form-control" path="phoneNumber"/>
            <label>Е-поща</label>
            <form:input type="text"  class="form-control" path="email"/>
            <label>Стажант (трите имена, фак.номер, специалност,маг. пр , випуск, Е-поща, тел. за връзка)</label>
            <form:textarea type="text" class="form-control" path="dataForIntern"/>
            <label>Тема на стажа</label>
            <form:textarea type="text" class="form-control" path="internshipTheame"/>
            <label>Анотация</label>
            <form:textarea type="text" class="form-control" path="annotation"/>
            <label>Цел на стажа</label>
            <form:textarea type="text" class="form-control" path="internshipGoal"/>
            <label>Задачи, произтичащи от целта</label>
            <form:textarea type="text" class="form-control" path="problemsFromGoal"/>
            <label>Ограничаващи/облекчаващи условия</label>
            <form:input type="text"  class="form-control" path="constraints"/>
            <label>Продължителност на проекта</label>
            <form:input type="text"  class="form-control" path="durationInHours"/>
            <br/>
            <input type="submit" value="Изпрати" id="submit_button"/>
            <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/home'"/>
        </form:form>
    </c:if>
</div>
</body>
</html>
<script>
    $(function(){
     /*TODO Validation for empty fealds*/
    });
</script>