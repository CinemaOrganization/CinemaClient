<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <spring:url value="/resources/css/style.css" var="crunchifyCSS"/>
    <link href="${crunchifyCSS}" type="text/css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <style>
        .cinemaHall {
            text-align: center;
            display: inline-block;
            vertical-align: top;
        }

        .seat {
            height: 20px;
            width: 20px;
            margin-right: 10px;
            background-color: #999999;
            display: inline-block;
            cursor: pointer;
            border: 1px solid #600000;
        }

        .passageBetween {
            height: 10px;
            width: 100%;
            display: block;
        }

        .bay {
            background-color: #92ff51;
        }

        .mouse {
            border: 1px solid #FFFFFF;
        }

        .paid {
            background-color: red;
        }

        .result {
            font-size: 14px;
            display: inline-block;
            width: 90px;
            max-height: 200px;
            overflow-y: auto;
            margin-right: 5px;
        }
    </style>
</head>
<body>

<div class='result'>
</div>
<div class='cinemaHall'></div>


<script type="text/javascript">
    //создаем карту мест
    var hallMap = matrixArray(${hall.rows}, ${hall.numberInRows}, 0);
    //меняем поле на 1, если уже занято
    hallMap = paidTiсkets(hallMap);
    cinemaHallMap = '<table>';
    for (i = 1; i <= hallMap.length; i++) {
        cinemaHallRow = '<tr><td>Ряд ' + i + ': </td><td>';
        for (j = 1; j <= hallMap[i - 1].length; j++) {
            classesForSeats = "seat";
            if (hallMap[i - 1][j - 1] == 1) {
                classesForSeats += " paid";
            }
            // собираем ряды
            cinemaHallRow += '<div class="' + classesForSeats + '" data-row="' +
                    i + '" data-seat="' +
                    j + '">&nbsp;</div>';
        }
        //собираем зал с проходами между рядами
        cinemaHallMap += cinemaHallRow + '<div class="passageBetween">&nbsp;</div></td></tr>';
    }
    cinemaHallMap += '</table>';

    //заполняем в html зал номер
    $('.cinemaHall').html(cinemaHallMap);


    $('.seat').on('click', function (e) {
        // если первый раз кликнули билет - выбрали,
        // если повторно - вернули билет
        if ($(e.currentTarget).hasClass('paid') == false) {
            $(e.currentTarget).toggleClass('bay');
            //показываем сколько билетов выбрано
            showBaySeat()
        }
    });

    $('.seat').mouseover(function (e) {
        //меняем при наведении
        $(e.currentTarget).addClass('mouse');
    });
    $('.seat').mouseout(function (e) {
        // меняем обратно при отведении
        $(e.currentTarget).removeClass('mouse');
    });

    function showBaySeat() {
        result = '';
        //ищем все места купленные и показываем список выбранных мест
        $.each($('.seat.bay'), function (key, item) {
            result += '<div class="ticket">Ряд: ' +
                    $(item).data().row + ' Место:' +
                    $(item).data().seat + '</div>';
        });

        $('.result').html(result);
    }
    function matrixArray(rows, columns, defineNumber) {
        var arr = new Array();
        for (var i = 0; i < rows; i++) {
            arr[i] = new Array();
            for (var j = 0; j < columns; j++) {
                arr[i][j] = defineNumber;
            }
        }
        return arr;
    }
    function paidTiсkets(hallMap) {
        var tickets = JSON.parse('${paidTickets}');
        for (var i = 0; i < tickets.length; i++) {
            var row = tickets[i].row;
            var number = tickets[i].number;
            hallMap[row - 1][number - 1] = 1;
        }
        return hallMap;
    }
</script>
</body>