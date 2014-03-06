<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="path">
    <span>Нов работен план</span>
</div>
<div class="main-area">
    <%--TODO ako e edit da se populvat datite--%>
    <form:form method="post" commandName="workPlan" id="form_form"><br/>
        <label>Научен ръководител</label>
        <form:select path="head.id" cssClass="form-control">
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.fullName}</option>
            </c:forEach>
        </form:select><br/>
        <h2>Подготовка и полагане на изпити от кандидатския минимум</h2>
        <table id="part_one">
            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td colspan="2"><label class="section">Секция 1</label></td>
            </tr>
            <tr class="section">
                <td><label>Съдържание на работите</label></td>
                <td>
                    <input type="text" name="partOne[0].workContent" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на провеждане</label></td>
                <td>
                    <input type="text" name="partOne[0].form" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Срок за изпълнение </label></td>
                <td>
                    <input type="text" name="partOne[0].deadlineAsString" class="form-control date"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на отчитане</label></td>
                <td>
                    <input type="text" name="partOne[0].typeOfReport" class="form-control "/>
                </td>
            </tr>
        </table>
        <input type="button" value="Добави секция" rev="one" class="add_to"/>

        <h2>Научноизследователска и експериментална работа</h2>
        <table id="part_two">
            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td colspan="2"><label class="section">Секция 1</label></td>
            </tr>
            <tr class="section">
                <td><label>Съдържание на работите</label></td>
                <td>
                    <input type="text" name="partTwo[0].workContent" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на провеждане</label></td>
                <td>
                    <input type="text" name="partTwo[0].form" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Срок за изпълнение </label></td>
                <td>
                    <input type="text" name="partTwo[0].deadlineAsString" class="form-control date"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на отчитане</label></td>
                <td>
                    <input type="text" name="partTwo[0].typeOfReport" class="form-control "/>
                </td>
            </tr>
        </table>
        <input type="button" value="Добави секция" class="add_to" rev="two"/>

        <h2>Оформяне на дисертацията</h2>
        <table id="part_three">
            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td colspan="2"><label class="section">Секция 1</label></td>
            </tr>
            <tr class="section">
                <td><label>Съдържание на работите</label></td>
                <td>
                    <input type="text" name="partThree[0].workContent" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на провеждане</label></td>
                <td>
                    <input type="text" name="partThree[0].form" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Срок за изпълнение </label></td>
                <td>
                    <input type="text" name="partThree[0].deadlineAsString" class="form-control date"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на отчитане</label></td>
                <td>
                    <input type="text" name="partThree[0].typeOfReport" class="form-control "/>
                </td>
            </tr>
        </table>
        <input type="button" value="Добави секция" class="add_to" rev="three"/>


        <h2>Учебно-методическа и педагогическа работа</h2>
        <table id="part_four">
            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td colspan="2"><label class="section">Секция 1</label></td>
            </tr>
            <tr class="section">
                <td><label>Съдържание на работите</label></td>
                <td>
                    <input type="text" name="partFour[0].workContent" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на провеждане</label></td>
                <td>
                    <input type="text" name="partFour[0].form" class="form-control"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Срок за изпълнение </label></td>
                <td>
                    <input type="text" name="partFour[0].deadlineAsString" class="form-control date"/>
                </td>
            </tr>
            <tr class="section">
                <td><label>Форми на отчитане</label></td>
                <td>
                    <input type="text" name="partFour[0].typeOfReport" class="form-control"/>
                </td>
            </tr>
        </table>
        <input type="button" value="Добави секция" class="add_to" rev="four"/>

        <div id="part_three">

        </div>
        <input type="submit" value="Запис"/>
    </form:form>
</div>
<script>
    var part_one = 1;
    var part_two = 1;
    var part_three = 1;
    var part_four = 1;

    $(function(){
        $('.date').datepicker({ dateFormat: 'dd/mm/yy'});
        $(".add_to").click(function(){
            var number = $(this).attr("rev");
            var temp = 0;
            var varName = "";
            if(number == "one"){
                temp = part_one;
                varName = "partOne";
            } else if(number == "two"){
                temp = part_two;
                varName = "partTwo";
            } else if(number == "three"){
                temp = part_three;
                varName = "partThree";
            } else if(number == "four"){
                temp = part_four;
                varName = "partFour";
            }
            $("#part_"+number).append(
                    '<tr >'+
                            '     <td colspan="2"><label class="section">Секция '+(temp + 1)+'</label></td>'+
                            '</tr>'+
                            '<tr>'+
                            '<td><label>Съдържание на работите</label></td>'+
                            '<td>' +
                            '<input class="form-control" type="text" name="'+varName+'['+temp+'].workContent" />'+
                            '</td>'+
                            '</tr>'+
                            '<tr>'+
                            '<td><label>Форми на провеждане</label></td>'+
                            '<td>' +
                            '<input class="form-control" type="text" name="'+varName+'['+temp+'].form" />' +
                            '</td>'+
                            '</tr>'+
                            '<tr>'+
                            '<td><label>Срок за изпълнение </label></td>'+
                            '<td>' +
                            '<input class="form-control date" type="text"  name="'+varName+'['+temp+'].deadlineAsString" />' +
                            '</td>'+
                            '</tr>'+
                            '<tr>'+
                            '<td><label>Форми на отчитане</label></td>'+
                            '<td>' +
                            '<input class="form-control date" type="text"  name="'+varName+'['+temp+'].typeOfReport" />' +
                            '</td>'+
                            '</tr>'

            );
            if(number == "one"){
                part_one++;
            } else if(number == "two"){
                part_two++;
            } else if(number == "three"){
                part_three++;
            } else if(number == "four"){
                part_four++;
            }
        });
    });
</script>