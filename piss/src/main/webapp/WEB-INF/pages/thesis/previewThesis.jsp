<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h4>Дипломи работи на: ${thesis.thesisProposal.user.fullName} фн: ${thesis.thesisProposal.user.fn}</h4></br>
    </br>
    <h2>Текст</h2><br/>
    <label>${thesis.text}</label>
    <h2>Резюме на български</h2><br/>
    <label>${thesis.summaryBulgarian}</label>
    <h2>Резюме на английски</h2><br/>
    <label>${thesis.summaryEnglish}</label>
    <h2>Сорс код</h2><br/>
    <a href="${pageContext.request.contextPath}/thesis/final/file/${thesis.id}">Свали</a><br/>
    <form:form method="post" commandName="thesisReview" id="form_form">
        <h4>Критерии за оценяване (оценени от 2 до 6):</h4><br/><br/>

        <h2>Общи:</h2><br/>
        <label>Теоретична обосновка</label>
        <form:input path="theoretical" class="form-control form-control-date" />
        <label>Собствени идеи</label>
        <form:input path="ownIdeas" class="form-control form-control-date"/>
        <label>Изпълнение на заданието</label>
        <form:input type="text" class="form-control form-control-date" path="execution"/>
        <label>Стил и оформление</label>
        <form:input type="text"  class="form-control form-control-date" path="style"/>

        <h2>Реализация:</h2><br/>
        <label>Структура и архитектура</label>
        <form:input type="text" class="form-control form-control-date" path="architecture"/>
        <label>Функционалност</label>
        <form:input type="text" class="form-control form-control-date" path="functionality"/>
        <label>Надеждност</label>
        <form:input type="text" class="form-control form-control-date" path="reliability"/>
        <label>Документация</label>
        <form:input type="text" class="form-control form-control-date" path="documentation"/>

        <h2>Експериментална част:</h2><br/>
        <label>Описание на експеримента</label>
        <form:input type="text" class="form-control" path="description"/>
        <label>Представяне на резултатите</label>
        <form:input type="text"  class="form-control" path="presentation"/>
        <label>Интерпретация на резултатите</label>
        <form:input type="text"  class="form-control" path="interpretation"/>

        <label>Обобщено мнение</label>
        <form:textarea type="text"  class="form-control" path="summary"/>
        <label>Въпроси</label>
        <form:textarea type="text"  class="form-control" path="interpretation"/>

        <br/>
        <input type="submit" value="Изпрати" id="submit_button"/>
        <input type="button" value="Назад"  onclick="window.location='${pageContext.request.contextPath}/final/forReview'"/>
    </form:form>
</div>