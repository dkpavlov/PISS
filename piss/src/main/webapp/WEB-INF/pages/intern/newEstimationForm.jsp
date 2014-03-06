<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
</head>
<body>
<div id="path">
    <span>Оценка от стаж</span>
</div>
<div class="main-area">
    <c:if test="${not empty flag and flag eq '1'}">
        <h4>Не сте въвели предложение за стаж</h4>
    </c:if>
    <c:if test="${not empty flag and flag eq '2'}">
        <h4>Вече е въведена оценка за стаж от вашия акаунт</h4>
    </c:if>
    <c:if test="${empty flag}">
        <form:form method="post" commandName="estimation" id="form_form">
        <label>от Ръководител</label>
        <form:input path="manager" class="form-control" ></form:input>
        <label>Фирма/Организация</label>
        <form:password path="organizationName" class="form-control" id="password"/>
        <label>Телефон</label>
        <form:input type="text" class="form-control" path="phoneNumber"/>
        <label>Е-поща</label>
        <form:input type="text"  class="form-control" path="email"/>
        <label>Стажант (трите имена, фак.номер, специалност,маг. пр , випуск, Е-поща, тел. за връзка)</label>
        <form:textarea type="text" class="form-control" path="dataForIntern"/>
        <label>Тема на стажа</label>
        <form:textarea type="text" class="form-control" path="internshipTheame"/>
        <label>Мнение за степента на изпълнение на целите и задачите на стажа</label>
        <form:textarea type="text" class="form-control" path="opinionOnFulfillment"/>
        <label>Постигнати резултати</label>
        <form:textarea type="text" class="form-control" path="resultsAchieved"/>
        <label>Придобити умения по време на стажа</label>
        <form:textarea type="text" class="form-control" path="gainedExperience"/>
        <label>Спазен ли е срокът за изпълнение и , ако не , по какви причини</label>
        <form:textarea type="text"  class="form-control" path="projectDuration"/>
        <label>Мнение за професионалната подготовка на стажанта</label>
        <form:textarea type="text"  class="form-control" path="viewOfIntern"/>
        <label>Обща оценка</label>
        <form:input type="text" class="form-control" path="score"/>
        <label>Препоръки към стажанта</label>
        <form:textarea type="text"  class="form-control" path="recommendationsForIntern"/>
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