<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head></head>
<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/cms.css"  />"/>
<body>
<div id="path">
    <span>Административен панел >> Управление на резервации</span>
</div>
<div class="main-area">
      <util:blobOut context="${termsOfUse.conditions}"/>
</div>
</body>
</html>