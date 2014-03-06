<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title></title>
</head>
<body>
<div id="path">
    <span>Административен панел >> Потребители >> Нов потребител</span>
</div>
<div class="main-area">
    <form:form method="post" commandName="user">
        <label>Потребителско име <span class="text-danger">* <form:errors path="username" cssClass="error" class="text-danger"></form:errors>
        <c:if test="${not empty usernameError}">${usernameError}</c:if>
        </span></label>
        <form:input path="username" class="form-control" ></form:input>
        <label>Парола <span class="text-danger">*  <form:errors path="password" cssClass="text-danger" ></form:errors></span></label>
        <form:input path="password" class="form-control"/>
        <label>Роля <span class="text-danger">*</span></label>
        <form:select path="role" class="form-control">
            <c:forEach items="${roleList}" var="userRole">
                <form:option  value="${userRole}">${userRole.displayValue}</form:option>
            </c:forEach>
        </form:select>
        <h2>Допълнителни данни</h2>
        <label>Име</label>
        <form:input type="text"  class="form-control" path="firstName"/>
        <label>Презиме</label>
        <form:input type="text"  class="form-control" path="secondName"/>
        <label>Фамилия</label>
        <form:input type="text" class="form-control" path="thirdName"/>
        <label>e-mail <span class="text-danger"><form:errors path="email" cssClass="text-danger" ></form:errors></span></label>
        <form:input type="text" class="form-control" path="email"/>
        <label>Телефон</label>
        <form:input type="text" class="form-control" path="phoneNumber"/>
        <br/>
        <input type="submit" value="Запис"/>
        <input type="button" value="Отказ"  onclick="window.location='${pageContext.request.contextPath}/cms/admin/user'"/>
    </form:form>
</div>
</body>
</html>