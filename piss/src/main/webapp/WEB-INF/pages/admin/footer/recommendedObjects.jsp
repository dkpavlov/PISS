<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>

    <%--    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />--%>
    <c:set var="base" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
    <script src="${base}assets/ckeditor/ckeditor.js"></script>
    <title>Препоръчани обекти</title>
    <script>
        CKEDITOR.config.baseHref = '${base}';

        CKEDITOR.config.allowedContent = true;
        CKEDITOR.config.extraPlugins = 'stylesheetparser';
        CKEDITOR.config.contentsCss = '${base}assets/css/custom.css';
        var fmt = "mm/dd/yy";
        <c:if test="${rc.locale.language =='bg'}">
        fmt = "dd-mm-yy"
        </c:if>

        $(function(){
            var loc = "";
            <c:if test="${rc.locale.language =='bg'}">
            loc = "bg"
            </c:if>

            $("#from-date").datepicker($.datepicker.regional[loc]);
            $("#to-date").datepicker($.datepicker.regional[loc]);


        })
    </script>

</head>
<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/cms.css"  />"/>
<body>
<div id="path">
    Административен панел >> Страници >> Препоръчани обекти
</div>
<div class="main-area">

    <c:url var="saveUrl" value="/cms/admin/footer/recommendedObjects" />
    <form:form method="post" modelAttribute="recommendedObjects" action="${saveUrl}">


        <h2>Българска версия</h2>
        <textarea class="ckeditor" cols="80" id="editorBG" name="recommendedObjectsBG" rows="10" ><c:if test="${not empty recommendedObjects}"> ${recommendedObjects.recommendedObjects.map['bg'].text}</c:if></textarea>
        <h2>English version</h2>
        <textarea class="ckeditor" cols="80" id="editorEN" name="recommendedObjectsEN" rows="10" ><c:if test="${not empty recommendedObjects}"> ${recommendedObjects.recommendedObjects.map['en'].text}</c:if></textarea>

        <input type="submit" class="top10" value="Запази">
        <input type="button" class="top10" onclick="window.location='${pageContext.request.contextPath}/cms/admin/home';" value="Отказ">
    </form:form>

</div>
</body>
</html>