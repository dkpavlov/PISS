<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="path">
    <span>Заявка за нов потребител</span>
</div>
<div class="main-area">
    <form:form method="post" commandName="user" id="acc_form">
        <label>Потребителско име</label>
        <form:input path="username" class="form-control" ></form:input>
        <label>Парола </label>
        <form:password path="password" class="form-control" id="password"/>
        <label>e-mail</label>
        <form:input type="text" class="form-control" path="email"/>
        <h2>Допълнителни данни</h2>
        <label>Име</label>
        <form:input type="text"  class="form-control" path="fullName"/>
        <label>Телефон</label>
        <form:input type="text" class="form-control" path="phoneNumber"/>
        <c:if test="${type eq 'student'}">
            <label>Специалност</label>
            <form:input type="text" class="form-control" path="speciality"/>
            <label>Факултетен номер</label>
            <form:input type="text" class="form-control" path="fn"/>
            <label>Випуск</label>
            <form:input type="text" class="form-control" path="classOf"/>
        </c:if>
        <c:if test="${type eq 'teacher'}">
            <label>катедра/ВУЗ/институт</label>
            <form:input type="text" class="form-control" path="speciality"/>
        </c:if>
        <c:if test="${type eq 'phd'}">
            <label>Дата на встъпване</label>
            <form:input cssClass="form-control form-control-date" path="startOfPHDAsString"/>
            <label>Входящ номер на заповета</label>
            <form:input cssClass="form-control" path="orderNumber"/>
            <label>Дата заповета</label>
            <form:input cssClass="form-control form-control-date" path="orderFromAsString"/>
        </c:if>
        <br/>
        <input type="submit" value="Изпрати" id="submit_button"/>
        <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/home'"/>
    </form:form>
</div>
<script>
    $(function(){
        $(".form-control-date").datepicker({ dateFormat: 'dd/mm/yy'});
        //TODO problem s prowerqwaneto na poletata
        $("#acc_form").submit(function(e){
            var flag = 0;
            $("input.form-control").each(function(){
                console.log($(this));
                if($.trim($(this).val()).length == 0){
                    flag++;
                }
            });
            console.log("Flag "+flag);
            if(flag != 0){
                alert("Моля потълнете всички полета!");
                e.preventDefault();
            }
        });

    });
</script>