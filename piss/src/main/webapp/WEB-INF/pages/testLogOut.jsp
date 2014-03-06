
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Test Logout</title>
</head>

<body>

    <%--<h3><spring:message code="global.springmvc"/></h3>--%>
    <form>
        <input type="button" value="Log out"
               onclick="window.location='${pageContext.request.contextPath}/logout';"/>
    </form>

</body>
</html>
