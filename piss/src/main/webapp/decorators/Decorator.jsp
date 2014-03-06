<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="shro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><decorator:title default=""/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/bootstrap.min.css"  />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/cms.css"  />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/custom.css"  />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/js/jquery/jquery-ui.css"  />"/>
    <script type="text/javascript" src="<c:url value="/assets/js/jquery/jquery-1.9.1.min.js"  />"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/bootstrap.js"  />"></script>
    <script type="text/javascript" src="<c:url value="/assets/js/jquery/jquery-ui.min.js"  />"></script>
    <script type=text/javascript src="<c:url value="/assets/js/jquery/datepicker.bg.js"  />"></script>
    <decorator:head/>
</head>
<body>

<div id="header">
    <shro:user>
        <div id="admin-line">
            <ul>

                <li>Здравейте, <a href="${pageContext.request.contextPath}/profile"><shiro:principal/></a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Изход</a></li>

            </ul>
        </div>
    </shro:user>
    <div id="inner-header">
        <h1>Система за електроно управлени на документи за студенти</h1>
    </div>
</div>


<div id="left-pannel">
    <div class="panel-group" id="accordion">
        <shro:guest>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/student">
                            Създай акаунт за студент
                        </a>
                    </h4>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/phd">
                            Създай акаунт за докторант
                        </a>
                    </h4>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/teacher">
                            Създай акаунт за преподавател
                        </a>
                    </h4>
                </div>
            </div>
        </shro:guest>
        <shro:user>
            <shro:hasRole name="ADMINISTRATOR">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/home/admin">
                                Всички акаунти
                            </a>
                        </h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/admin/forValidation">
                                Акаунти чакащи одобрение
                            </a>
                        </h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/teacher">
                                Създаване на акаунт
                            </a>
                        </h4>
                    </div>
                </div>
            </shro:hasRole>
            <shro:hasRole name="STUDENT">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle"  data-parent="#accordion" href="#internship">
                                Стаж
                            </a>
                        </h4>
                    </div>
                    <div id="internship" class="panel-collapse in">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/internship/new">Във еждане на предложение за преддипломен стаж</a>
                            <a href="${pageContext.request.contextPath}/internship/estimation">Въвеждане на оценка за преддипломен стаж</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/teacher">
                                Дипломна работа
                            </a>
                        </h4>
                    </div>
                    <div id="dr" class="panel-collapse in">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/thesis/new">Въвеждане предложение за дипломна работа</a>
                            <a href="${pageContext.request.contextPath}/thesis/final/new">Качване на дипломна работа</a>
                        </div>
                    </div>
                </div>
            </shro:hasRole>
            <shro:hasRole name="TEACHER">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <label class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/teacher">
                                Стаж
                            </label>
                        </h4>
                    </div>
                    <div id="t_in" class="panel-collapse in">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/internship/forValidation">Предложеня за стаж чакащи одобрение</a>
                            <a href="${pageContext.request.contextPath}/internship/estimation/forValidation">Оценки от стаж чакащи одобрение</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <label class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/teacher">
                                Дипломна работа
                            </label>
                        </h4>
                    </div>
                    <div id="t_dr" class="panel-collapse in">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/thesis/forValidation">Предложеня за дипломна работа чакащи одобрение</a>
                            <a href="${pageContext.request.contextPath}/thesis/final/forReview">Дипломна работа чакащи рецензия</a>
                            <a href="${pageContext.request.contextPath}/statistic/graduated">Справка за дипломирани студенти</a>
                            <a href="${pageContext.request.contextPath}/statistic/head">Справка за дипломирани студенти с даден ръководител</a>
                            <a href="${pageContext.request.contextPath}/statistic/committee">Справка за дипломирани студенти с даден рецензент</a>
                            <a href="${pageContext.request.contextPath}/statistic/teacher">Справка за участие на преподавател в комисии за дипломни защити</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <label class="accordion-toggle"  data-parent="#accordion" href="${pageContext.request.contextPath}/singUp/teacher">
                                Комисии за дипломна работа
                            </label>
                        </h4>
                    </div>
                    <div id="c_dr" class="panel-collapse in">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/committee/new">Комися за защита на диплома работа</a>
                            <a href="${pageContext.request.contextPath}/committee/my">Дипломи работи за които съм в комисията</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <label class="accordion-toggle"  data-parent="#accordion">
                                Докторанти
                            </label>
                        </h4>
                    </div>
                    <div id="phd_dr" class="panel-collapse in">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/phd/all">Всички докторанти с които съм асоцииран</a>
                            <a href="${pageContext.request.contextPath}/phd/all">Справка за текущи докторанти от дата до дата</a>
                       </div>
                    </div>
                </div>
            </shro:hasRole>
            <shro:hasRole name="PHD">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <label class="accordion-toggle"  data-parent="#accordion">
                                Работен план
                            </label>
                        </h4>
                    </div>
                    <div id="rp_dr" class="panel-collapse in">
                        <div class="panel-body">
                            <a href="${pageContext.request.contextPath}/plan/full">Въвеждне на общ работен план</a>
                            <a href="${pageContext.request.contextPath}/plan/individual">Въвеждане на индивидуален план</a>
                            <a href="${pageContext.request.contextPath}/plan/yearly"> Въвеждане на работен план за една година</a>
                        </div>
                    </div>
                </div>
            </shro:hasRole>
        </shro:user>
    </div>
</div>

<div id="content">
    <decorator:body/>
</div>
<div class="clear"></div>
</body>
</html>