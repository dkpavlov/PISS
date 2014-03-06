<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="path">
    <span>Дипломна работа</span>
</div>
<div class="main-area">
    <form:form method="post" commandName="committee" id="form_form">
        <label>Дата</label>
        <input type="text" class="form-control form-control-date" name="date" id="startDate"/><br/>
        <label>Член на комисията 1(Водещ)</label>
        <form:select path="committee[0].id" cssClass="form-control-date form-control">
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.firstName}</option>
            </c:forEach>
        </form:select><br/>
        <label>Член на комисията 2</label>
        <form:select path="committee[1].id" cssClass="form-control-date form-control">
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.firstName}</option>
            </c:forEach>
        </form:select><br/>
        <label>Член на комисията 3</label>
        <form:select path="committee[2].id" cssClass="form-control-date form-control">
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.firstName}</option>
            </c:forEach>
        </form:select><br/>
        <div id="additional_committee"></div>
        <input type="button" value="Добави член" id="add_committee"/>
        <input type="submit" value="Запази" id="submit_button"/>
        <h2>Предстоящи комиси</h2>
        <table class="data table-striped">
            <tr>
                <th>Дата</th>
                <th>Водещ</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${upcomingCommittees}" var="committee">
                <tr>
                    <%--TODO да се форматира датата--%>
                    <td>${committee.date}</td>
                    <td>${committee.committee[0].fullName}</td>
                    <td><input type="button" value="Добави студент" onclick="window.location='${pageContext.request.contextPath}/committee/${committee.id}'"></td>
                </tr>
            </c:forEach>
        </table>
    </form:form>
</div>
<script>
    var committee_count = 2;
    $(function(){
        $('#startDate').datepicker({ dateFormat: 'dd/mm/yy',
            minDate: '0'});
        $("#add_committee").click(function(){
            committee_count++;
            $("#additional_committee").append(
                    '<label>Член на комисията ' + (committee_count + 1) +'</label>'
                    +'<select name="committee[' + committee_count + '].id" class="form-control-date form-control">'
                        <c:forEach items="${teacherList}"  var="teacher">
                            +'<option value="${teacher.id}">${teacher.firstName}</option>'
                        </c:forEach>
                    +'</select><br/>');
        });
    });
</script>