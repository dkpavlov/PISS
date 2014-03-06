<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="path">
    <span>Дипломна работа</span>
</div>
<div class="main-area">
    <c:if test="${flag eq '0'}">
        <form:form method="post" commandName="thesis" enctype="multipart/form-data">
            <label>Текст</label>
            <textarea name="t1" class="form-control" ></textarea>
            <label>Резюме на български</label>
            <textarea name="t2" class="form-control"></textarea>
            <label>Резюме на английски</label>
            <textarea class="form-control" name="t3"></textarea>
            <label>Сорс код</label>
            <form:input type="file" path="file" value="Add File"/>
            <%--<input type="file" value="Add File"  name="file" class="form-control"/>--%>
            <br/>
            <input type="submit" value="Изпрати" id="submit_button"/>
            <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/home'"/>
        </form:form>
    </c:if>
    <c:if test="${flag eq '1'}">
        <br/>
        <h4>За да качите допломна работа трябва да имате одобрено предложение за дипломна работа</h4>
        <br/>
        <br/>
        <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/home'"/>
    </c:if>
</div>
<script>
    $(function(){
        /*TODO Validation for empty fealds
        * PROBLEM S ZAPISA NA DIPLOMNA RABOTA I RECENZIA*/
    });
</script>