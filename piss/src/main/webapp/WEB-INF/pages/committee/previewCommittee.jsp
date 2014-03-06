<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-area">
    <h2>Учасници в комисията</h2></br>
    <table class="data table-striped">
        <tr>
            <th>Имена</th>
            <th>катедра/ВУЗ/институт</th>
        </tr>
        <c:forEach items="${committee.committee}" var="teacher">
            <tr>
                <td>${teacher.fullName}</td>
                <td>${teacher.chair} ${user.thirdName}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Студенти които ще защитават пред комисията</h2></br>
    <table class="data table-striped">
        <tr>
            <th>Имена</th>
            <th>Ф.Н.</th>
            <th>Специалност</th>
        </tr>
        <c:forEach items="${committeeStudents}" var="student">
            <tr class="tr_with_rev" rev="${student.user.id}">
                <td>${student.user.fullName}</td>
                <td>${student.user.fn}</td>
                <td>${student.user.speciality}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Добави студент</h2></br>
    <select id="student_select" class="form-control">
        <c:forEach items="${studentList}" var="student">
            <option value="${student.id}">${student.fn} ${student.fullName}</option>
        </c:forEach>
    </select><br/>
    <input type="button" value="Добави" id="add_button"/>
</div>
<script>
    $(function(){
        $("#add_button").click(function(){
            var flag = 0;
            var selected_user_id = $("#student_select").val();
            $("tr.tr_with_rev").each(function(){
                if(selected_user_id == $(this).attr('rev')){
                    flag = 1;
                }
            });
            if(flag == 1){
                alert("Този студент е вече въведен");
            } else {
                window.location = "${pageContext.request.contextPath}/committee/${committee.id}/"+selected_user_id;
            }
        });
    });
</script>