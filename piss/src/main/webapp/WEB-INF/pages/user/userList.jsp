<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script>
        $(document).ready(function() {

            $('.arch_button').click(function(){
                if(confirm("Желаете ли да архивирате записа?")){
                    return true;
                } else {
                    return false;
                }
            });

            $('#searchButton').click(function(){
                var u = $('#usernameField').val();
                var r = $('#roleField').val();
                if(u == ""){
                    u = "_";
                }
                window.location.href = "${pageContext.request.contextPath}/cms/admin/user/"+u+"/"+r;
            });

        });
    </script>
</head>
<body>
<div id="path">
    <span>Административен панел >> Потребители >> Всички потребители</span>
</div>
<div class="main-area">
    <form>
        <h2>Филтър</h2>
        <label>Потребителско име</label>
        <input type="text" <c:if test="${not empty oldUsername}">value="${oldUsername}" </c:if> class="form-control" id="usernameField"/>
        <div class="pull-left form-control-date">
            <label>Роля</label>
            <select class="form-control" id="roleField">
                <option <c:if test="${not empty oldRole}"><c:if test="${oldRole == 'all'}">selected="selected"</c:if></c:if> value="all"></option>
                <option <c:if test="${not empty oldRole}"><c:if test="${oldRole == 'admin'}">selected="selected"</c:if></c:if> value="admin">Администратор</option>
                <option <c:if test="${not empty oldRole}"><c:if test="${oldRole == 'owner'}">selected="selected"</c:if></c:if> value="owner">Хотелиер</option>
            </select>
        </div>
        <input type="button" value="Търси" class="pull-left btn-search-filter" id="searchButton"/>
        <div class="clear"></div>
    </form>
    <h2>Резултати</h2>
   <%-- <div class="centered">
    <ul class="pagination">
        <li><a href="#">&laquo;</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">&raquo;</a></li>
    </ul>
    </div>--%>
    <table class="data table-striped">
        <tr>
            <th>Потребителско име</th>
            <th>Роля</th>
            <th>&nbsp</th>
            <th>&nbsp</th>
            <th>&nbsp</th>
        </tr>
        <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.role.displayValue}</td>
            <td><a href="${pageContext.request.contextPath}/cms/admin/user/${user.id}/edit">Редактирай</a></td>
            <td><a href="${pageContext.request.contextPath}/cms/admin/user/${user.id}/password/0">Смяна на паролата</a></td>
            <td><a href="${pageContext.request.contextPath}/cms/admin/user/${user.id}/archive" class="arch_button">Архивирай</a></td>
        </tr>
        </c:forEach>
    </table>
    <%--<div class="centered">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>--%>
</div>
</body>
</html>