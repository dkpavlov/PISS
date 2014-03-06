<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main-area">
    <h2>Въвеждане на атестация за ${phd.fullName}</h2>
    <form:form method="post" commandName="attestation" id="form_form">
        <label>Текст</label>
        <form:textarea path="text" class="form-control" /><br/>
        <input type="submit" value="Изпрати" id="submit_button"/>
        <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/phd/all'"/>
    </form:form>
</div>
<script>
    $(function(){

    });
</script>