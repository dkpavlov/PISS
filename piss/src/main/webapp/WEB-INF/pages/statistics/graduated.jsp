<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Справка за дипломирани студенти с даден ръководител от дата до дата</h2></br>
    <label>Тип проверка</label>
    <select class="form-control form-control-date" id="type_select">
        <option value="1">От дата до дата</option>
        <option value="2">За конктретна дата</option>
    </select><br/>
    <div id="from_to">
        <label>От дата:</label>
        <input type="text" id="from_date" class="form-control form-control-date"/>
        <br/>
        <label>До дата:</label>
        <input type="text" id="to_date"  class="form-control form-control-date"/>
    </div>
    <div id="date_one" class="hide">
        <label>Дата:</label>
        <input type="text" id="date_date"  class="form-control form-control-date"/>
    </div>
    <br/>
    <input type="button" value="Търси" id="search">
    <c:if  test="${!empty userList}">
        <table class="data table-striped">
            <tr>
                <th>Име</th>
                <th>ФН</th>
                <th>Специалност</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.fullName}</td>
                    <td>${user.fn} </td>
                    <td>${user.speciality} </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<script>
    $(function(){
        $('#from_date').datepicker({ dateFormat: 'dd/mm/yy'});
        $('#to_date').datepicker({ dateFormat: 'dd/mm/yy'});
        $('#date_date').datepicker({ dateFormat: 'dd/mm/yy'});
        $("#type_select").change(function(){
            if($(this).val() == '1'){
                $("#date_one").addClass("hide");
                $("#from_to").removeClass("hide")
            } else if($(this).val() == '2'){
                $("#from_to").addClass("hide");
                $("#date_one").removeClass("hide");
            }
        });
        $("#search").click(function(){
            window.location = "${pageContext.request.contextPath}/statistic/graduated?startDate="+$("#from_date").val()+"&endDate="+$("#to_date").val()+"&date="+$("#date_date").val()+"&type="+$("#type_select").val();
        });
    });
</script>
