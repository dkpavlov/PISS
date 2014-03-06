<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-area">
    <h3>Преглед на предложение на дипломна работа от: ${thesisProposal.user.fullName} ${thesisProposal.user.fn}</h3>
    </br>
    <table>
        <tr>
            <td><label>Консултант:</label></td>
            <td><label>${thesisProposal.consultant.fullName}</label></td>
            <td><label>катедра/ВУЗ/институт :</label></td>
            <td><label>${thesisProposal.consultant.chair}</label></td>
        </tr>
        <tr>
            <td><label>Тема на дипломната работа:</label></td>
            <td><label>${thesisProposal.thesis}</label></td>
        </tr>
        <tr>
            <td><label>Анотация:</label></td>
            <td><label>${thesisProposal.annotation}</label></td>
        </tr>
        <tr>
            <td><label>Цел на дипломната работа:</label></td>
            <td><label>${thesisProposal.thesisGoal}</label></td>
        </tr>
        <tr>
            <td><label>Задачи, произтичащи от целта:</label></td>
            <td><label>${thesisProposal.tasks}</label></td>
        </tr>
        <tr>
            <td><label>Ограничаващи/облекчаващи условия:</label></td>
            <td><label>${thesisProposal.constraints}</label></td>
        </tr>
        <tr>
            <td><label>Срок за изпълнение:</label></td>
            <td><label>${thesisProposal.deadline}</label></td>
        </tr>
        <tr>
            <%--TODO да се форматира датаат--%>
            <td><label>Дата:</label></td>
            <td><label>${thesisProposal.dateCreated}</label></td>
        </tr>
        <br/>

        <tr>

        </tr>
    </table>
    <br>
    <select class="form-control form-control-date" id="status_select">
        <c:forEach items="${thesisProposalStatus}" var="status">
            <c:if test="${status ne 'PENDING'}">
                <option value="${status}">${status.displayValue}</option>
            </c:if>
        </c:forEach>
    </select><br/>
    <label>Забележка</label>
    <div class="hide" id="note_div">
        <textarea class="form-control" id="note"></textarea>
    </div><br/>
    <input type="button" value="Запази" id="save"/>
    <td><input type="button" onclick="window.location='${pageContext.request.contextPath}/thesis/forValidation';" value="Назад"></td>
</div>
<script>
    $(function(){
        $("#save").click(function(){
            window.location = "${pageContext.request.contextPath}/thesis/${thesisProposal.id}/"+$("#status_select").val()+"?note="+$("note").text();
        });
        $("#status_select").change(function(){
            if($(this).val() == "CONDITION"){
                $("#note_div").removeClass("hide");
            } else {
                $("#note_div").addClass("hide");
            }
        });
        $
    });
</script>
