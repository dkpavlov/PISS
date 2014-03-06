<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head></head>
<body>
<div id="path">
    <span><spring:message code="recommended_object"/></span>
</div>
<div class="main-area">
    <div class="cms-added">
      <util:langOut context="${termsOfUse.recommendedObjects}"/>
    </div>
</div>
</body>
</html>