<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Справка за текущи докторанти от дата до дата</h2></br>
    <label>От дата:</label>
    <input type="text" id="from_date" class="form-control form-control-date"/>
    <br/>
    <label>До дата:</label>
    <input type="text" id="to_date"  class="form-control form-control-date"/>
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
            window.location = "${pageContext.request.contextPath}/statistic/graduated?startDate="+$("#from_date").val()+"&endDate="+$("#to_date").val();
        });
    });
</script>
