<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Дипломи работи за които съм в комисията</h2></br>
    <c:if  test="${!empty studentList}">
        <table class="data table-striped">
            <tr>
                <th>Име и фамилия</th>
                <th>ФН</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${studentList}" var="user">
                <tr id="student_${user.user.id}">
                    <td>${user.user.fullName} </td>
                    <td>${user.user.fn} </td>
                    <td><input type="button" value="Рецензия" onclick="window.location='${pageContext.request.contextPath}/thesisReview/${user.user.id}/export'"></td>
                    <td><input type="button" value="Въведи оценка" class="add_mark" rev="${user.user.id}"></td>
                </tr>
                <tr id="hidden_${user.user.id}" class="hidden">
                    <td></td>
                    <td><input type="text" class="form-control form-control-date" id="input_${user.user.id}"/></td>
                    <td></td>
                    <td><input type="button" value="Въведи" class="set_mark" rev="${user.user.id}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<script>
    $(function(){
        $(".add_mark").click(function(){
            var id = $(this).attr("rev");
            $("#hidden_"+id).removeClass("hidden");
        });

        $(".set_mark").click(function(){
            var id = $(this).attr("rev");
            var score = $("#input_"+id).val();
            if(!isNaN(score) && score.toString().indexOf(".") != -1){
                alert("Моля въведете валидна оценка използваите '.' ");
            } else {
                $("#hidden_"+id).addClass("hidden");
                window.location = "${pageContext.request.contextPath}/committee/score/"+id+"?score="+score;
            }
        });
    });
</script>