<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="main-area">
    <h2>Всички докторанти</h2></br>
    <c:if  test="${!empty pHDsList}">
        <table class="data table-striped">
            <tr>
                <th>Име и фамилия</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${pHDsList}" var="phd">
                <tr>
                    <td>${phd.fullName} </td>
                    <td><input type="button" rev="${phd.id}" value="Печат на аботни планове" class="plans"/></td>
                    <td><input type="button" value="Добави атестация" onclick="window.location='${pageContext.request.contextPath}/phd/${phd.id}/ate'"></td>
                    <td><input type="button" value="Консултант/и" onclick="window.location='#'"></td>
                </tr>
                <tr class="hidden plan_list" id="plan_${phd.id}">
                    <th>&nbsp;</th>
                    <td><input type="button" value="Индивидуален" onclick="window.location='${pageContext.request.contextPath}/plan/individual/${phd.id}'"/></td>
                    <td><input type="button" rev="${phd.id}" value="По години" class="year"/></td>
                    <td><input type="button" value="Общ" onclick="window.location='${pageContext.request.contextPath}/plan/full/${phd.id}'"/></td>
                </tr>
                <tr class="hidden year_list" id="year_${phd.id}">
                    <th>&nbsp;</th>
                    <td><input type="button" value="Първа" onclick="window.location='${pageContext.request.contextPath}/plan/yearly/1/export/${phd.id}'"/></td>
                    <td><input type="button" value="Втора" onclick="window.location='${pageContext.request.contextPath}/plan/yearly/2/export/${phd.id}'"/></td>
                    <td><input type="button" value="Трета" onclick="window.location='${pageContext.request.contextPath}/plan/yearly/3/export/${phd.id}'"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<script>
    $(function(){
        $("input.plans").click(function(){
            $("tr.plan_list").each(function(){
               $(this).addClass("hidden");
            });
            $("tr.year_list").each(function(){
               $(this).addClass("hidden");
            });
            $("tr#plan_"+$(this).attr("rev")).removeClass("hidden");
        });
        $("input.year").click(function(){
            $("tr#year_"+$(this).attr("rev")).removeClass("hidden");
        });
    });
</script>