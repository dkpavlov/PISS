<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Начална страница</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/<spring:theme code="style"/>" type="text/css" />
    <script src="<c:url value='/assets/js/jquery/jquery.js'  />" ></script>
    <script>
        $(document).ready(function(){

            var keyMark_promoted = "";
            var valueMark_promoted = "";
            var keyMark_new = "";
            var valueMark_new = "";
            var hotelID_promoted = "";
            var hotelID_new = "";
            var value = "";
            $(".package_key_promoted").each(function(){

                var key = $(this).val();
                keyMark_promoted += key.toString()+ ",";
            });

            $(".package_value_promoted").each(function(){

                var value = $(this).val();
                valueMark_promoted += value.toString()+ ";";
            });


            $(".package_key_new").each(function(){

                var key = $(this).val();
                keyMark_new += key.toString()+ ",";
            });

            $(".package_value_new").each(function(){

                var value = $(this).val();
                valueMark_new += value.toString()+ ";";
            });

            keyMark_promoted = keyMark_promoted.split(",");
            valueMark_promoted = valueMark_promoted.split(";");
            keyMark_new = keyMark_new.split(",");
            valueMark_new = valueMark_new.split(";");

            $(".idPromoted").each(function(){

                var id = $(this).val();
                hotelID_promoted += hotelID_promoted.toString()+ ",";
                for (var i=0; i<keyMark_promoted.length; i++ ) {
                    if (keyMark_promoted[i] == id){
                        value = valueMark_promoted[i];
                    }
                }
                $('#markPromoted'+id+'').text(': ' + value);
            });

            $(".idNew").each(function(){

                var id = $(this).val();
                hotelID_new += hotelID_new.toString()+ ",";
                for (var i=0; i<keyMark_new.length; i++ ) {
                    if (keyMark_new[i] == id){
                        value = valueMark_new[i];
                    }
                }
                $('#markNew'+id+'').text(': ' + value);
            });

            hotelID_new = hotelID_new.split(",");
            hotelID_promoted = hotelID_promoted.split(",");
        });
    </script>
</head>

<body>
    <%--<input type="button" onclick="window.location='${pageContext.request.contextPath}/user/all';" value="All Users">--%>
    <%--<input type="button" onclick="window.location='${pageContext.request.contextPath}/user/new';" value="Create User">--%>
    <%--<input type="button" onclick="window.location='${pageContext.request.contextPath}/file';" value="Uplode File">--%>
    <%--<input type="button" onclick="window.location='${pageContext.request.contextPath}/nomenclatures';" value="Nomenclatures">--%>
    <%--</br>--%>

    <div id="wowslider-container1">
        <div class="yellow-stripe">
            <div class="ws_bullets"><div>
                    <c:forEach var="i" begin="1" end="${fn:length(bannerGallery)}">
                        <a href="#" title="${i}">${i}</a>
                    </c:forEach>
            </div></div>
        </div>
        <div class="ws_images">
            <ul>
               <c:forEach items="${bannerGallery}" var="doc">
                    <li>
                       <div>
                        <util:langOut context="${doc.context}" escapeXml="false" />
                       </div>
                    </li>
                </c:forEach>

                <%--  <li><img src="${pageContext.request.contextPath}/assets/images/img/photos/1.jpg" alt="1" title="1" id="wows1_0"/></li>
                  <li><img src="${pageContext.request.contextPath}/assets/images/img/photos/2.jpg" alt="2" title="2" id="wows1_1"/></li>
                  <li><img src="${pageContext.request.contextPath}/assets/images/img/photos/3.jpg" alt="3" title="3" id="wows1_2"/></li>
                  <li><img src="${pageContext.request.contextPath}/assets/images/img/photos/4.jpg" alt="4" title="4" id="wows1_3"/></li>--%>
            </ul>
        </div>
        <div class="ws_shadow"></div>
        <a href="#" class="ws_next"></a>
        <a href="#" class="ws_prev"></a>
        <div class="yellow-stripe"></div>
    </div>

    <script src="<c:url value='/assets/js/wowslider.js' />" ></script>
    <script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>--%>

    <%-------------------------------------------Секция препоръчани обекти------------------------------------------------------------%>

    <c:if test="${fn:length(promotedHotel) gt 0}">
    <div class="recommended-objects">
         <div class="corner-recommended-objects">
             <%--<img src="${pageContext.request.contextPath}/assets/images/site_images/corner-recommended-objects.png"/>--%>
             <spring:message code="recommended_object" text="Препоръчани обекти" />
         </div>

    <c:forEach items="${promotedHotel}" var="promotedHotel" varStatus="status">
         <div class="object">
             <input type="hidden" name="id" value="${promotedHotel.hotel.id}" class="idPromoted"/>
            <div <c:choose><c:when test="${status.count == '1'}">class="main-img-conainer first"</c:when><c:otherwise>class="main-img-conainer"</c:otherwise></c:choose>>
                <a href="${pageContext.request.contextPath}/selectedHotel/${promotedHotel.hotel.id}">
                    <img src="${pageContext.request.contextPath}/getImg/${promotedHotel.hotel.mainImage.id}" width="176" >
                </a>
            </div>
             <a href="${pageContext.request.contextPath}/selectedHotel/${promotedHotel.hotel.id}">
                 <label class="list-name"><util:langOut context="${promotedHotel.hotel.name}"/></label>
             </a>
             <label class="list-address"><spring:message code="address"/><util:langOut context="${promotedHotel.hotel.address}"/></label>
             <div class="stars" style="width:calc(${promotedHotel.hotel.stars}*15px);"></div>
             <p class="home-object-info"> <util:blobOut context="${promotedHotel.hotel.description}"></util:blobOut></p>
            <p class="mark"><spring:message code="evaluation"/><label id="markPromoted${promotedHotel.hotel.id}" type="text" value=""></label></p>
            <a href="${pageContext.request.contextPath}/selectedHotel/${promotedHotel.hotel.id}" class="more"><spring:message code="view-more" text="Виж още"/></a>
         </div>
    </c:forEach>

         <c:forEach items="${markPromotedHotel}" var="mark">
             <input type="hidden" name="package" value="${mark.key.id}" class="package_key_promoted"/>
             <input type="hidden" name="package" value="${mark.value}" class="package_value_promoted"/>
         </c:forEach>
    <div class="clear"></div>
        <%-- <div class="object">
             <div class="main-img-conainer">
                 <img src="${pageContext.request.contextPath}/assets/images/site_images/object-img.png" alt="Картинка на обекта" title="размер:174 х 131"/>
             </div>
             <label class="list-name">Къща “Хаджидимитрова къща”</label>
             <label class="list-address">адрес: ул.”Обиколна” №49</label>
             <div class="stars"></div>
             <p class="home-object-info">
                 Къща “Хаджидимитрова къща” се намира в центъра на Габрово, на 50 метраот Римския театър, Стария град и други забележителности в града. В непосредствена близост има ресторанти и магазини.
             </p>
             <p class="mark">Оценка: 5.6</p>
             <a href="#" class="more">Виж още</a>
         </div>--%>
     </div>
    </c:if>
    <c:if test="${fn:length(newHotel) gt 0}">
        <div class="recommended-objects">
        <div class="corner-last-added-objects">
            <spring:message code="last_added" text="Последни добавени" />
        </div>

        <c:forEach items="${newHotel}" var="newHotel" varStatus="status">

            <div class="object">
                <input type="hidden" name="id" value="${newHotel.hotel.id}" class="idNew"/>
                <div <c:choose><c:when test="${status.count == '1'}">class="main-img-conainer first"</c:when><c:otherwise>class="main-img-conainer"</c:otherwise></c:choose>>
                    <a href="${pageContext.request.contextPath}/selectedHotel/${newHotel.hotel.id}"><img src="${pageContext.request.contextPath}/getImg/${newHotel.hotel.mainImage.id}" width="176"></a>
                </div>

                <a href="${pageContext.request.contextPath}/selectedHotel/${newHotel.hotel.id}">
                <label class="list-name"><util:langOut context="${newHotel.hotel.name}"/> </label></a>
                <label class="list-address"><spring:message code="address"/><util:langOut context="${newHotel.hotel.address}"/></label>
                <div class="stars" style="width:calc(${newHotel.hotel.stars}*15px);"></div>
                <p class="home-object-info"><util:blobOut context="${newHotel.hotel.description}"/></p>
                <p class="mark"><spring:message code="evaluation"/><label id="markNew${newHotel.hotel.id}" type="text" value=""></label></p>
                <a href="${pageContext.request.contextPath}/selectedHotel/${newHotel.hotel.id}" class="more"><spring:message code="view-more"/></a>
            </div>

        </c:forEach>

        <c:forEach items="${markNewHotel}" var="mark">
            <input type="hidden" name="package" value="${mark.key.id}" class="package_key_new"/>
            <input type="hidden" name="package" value="${mark.value}" class="package_value_new"/>
        </c:forEach>
        <div class="clear"></div>
       <%-- <div class="object">
            <div class="main-img-conainer first">
                <img src="${pageContext.request.contextPath}/assets/images/site_images/object-img.png" alt="Картинка на обекта" title="размер:174 х 131"/>
            </div>
            <label class="list-name">Къща “Хаджидимитрова къща”</label>
            <label class="list-address">адрес: ул.”Обиколна” №49</label>
            <div class="stars"></div>
            <p class="home-object-info">
                Къща “Хаджидимитрова къща” се намира в центъра на Габрово, на 50 метраот Римския театър, Стария град и други забележителности в града. В непосредствена близост има ресторанти и магазини.
            </p>
            <p class="mark">Оценка: 5.6</p>
            <a href="#" class="more">Виж още</a>
        </div>
        <div class="object">
            <div class="main-img-conainer">
                <img src="${pageContext.request.contextPath}/assets/images/site_images/object-img.png" alt="Картинка на обекта" title="размер:174 х 131"/>
            </div>
            <label class="list-name">Къща “Хаджидимитрова къща”</label>
            <label class="list-address">адрес: ул.”Обиколна” №49</label>
            <div class="stars"></div>
            <p class="home-object-info">
                Къща “Хаджидимитрова къща” се намира в центъра на Габрово, на 50 метраот Римския театър, Стария град и други забележителности в града. В непосредствена близост има ресторанти и магазини.
            </p>
            <p class="mark">Оценка: 5.6</p>
            <a href="#" class="more">Виж още</a>
        </div>--%>
    </div>
    </c:if>
</body>
</html>
