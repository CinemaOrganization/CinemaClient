<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            background-color: #6a6f99;
            display: inline-block;
            cursor: pointer;
            border: 1px solid #600000;
        }

        .infoBord {
            height: 20px;
            width: 140px;
            margin-right: 10px;
            background-color: #995e46;
            display: inline-block;
            cursor: pointer;
            border: 1px solid #600000;
            visibility: hidden;
        }

        .passageBetween {
            height: 10px;
            width: 100%;
            display: block;
        }

        .chosen {
            background-color: #92ff51;
        }

        .mouse {
            border: 1px solid #FFFFFF;
        }

        .paid {
            background-color: #beb7b8;
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

<form method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input id="ticketsField" type="hidden" name="tickets">
    <input type="submit" value="<spring:message code="booking.butten.Book"/>" disabled="true">
</form>
<br>
<div class='result'>
</div>
<div class='cinemaHall'></div>
<div class='infoBord'></div>


<script type="text/javascript">
    //создаем карту мест
    var hallMap = matrixArray(${hall.rows}, ${hall.numberInRows}, 0);
    //меняем поле на 1, если уже занято
    hallMap = paidTiсkets(hallMap);
    cinemaHallMap = '<table>';
    for (i = 1; i <= hallMap.length; i++) {
        cinemaHallRow = '<tr><td><spring:message code="booking.Row"/> ' + i + ': </td><td>';
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
            $(e.currentTarget).toggleClass('chosen');
            //показываем сколько билетов выбрано
            showBaySeat();
            var ticketsJson = generateJson();
            $('#ticketsField').val(ticketsJson);
        }
        enableSubmitIfSomeChosen();
    });

    $('.seat').mouseover(function (e) {
        //меняем при наведении
        $(e.currentTarget).addClass('mouse');
        var coordinates = $(e.currentTarget).offset();
        coordinates.top += 20;
        coordinates.left += 20;
        $('.infoBord').offset(coordinates);
        $('.infoBord').html('<spring:message code="booking.Row"/>: ' +
                $(e.currentTarget).data().row +
                ', <spring:message code="booking.Place"/>:' +
                $(e.currentTarget).data().seat);
        $('.infoBord').css('visibility', 'visible');
    });
    $('.seat').mouseout(function (e) {
        // меняем обратно при отведении
        $(e.currentTarget).removeClass('mouse');
        $('.infoBord').css('visibility', 'hidden');
    });

    function generateJson() {
        var ticketArray = [];
        $('.seat.chosen').each(function (key, item) {
            var ticket = new Ticket($(item).data().row, $(item).data().seat);
            ticketArray.push(ticket);
        });
        return JSON.stringify(ticketArray);
    }
    function Ticket(row, number) {
        this.row = row;
        this.number = number;
        this.session = new Session('${session.id}');
        this.user = new User('<sec:authentication property="principal.username"/>');
    }
    function Session(id) {
        this.id = id;
    }
    function User(username) {
        this.username = username;
    }
    function showBaySeat() {
        result = '';
        //ищем все места купленные и показываем список выбранных мест
        $.each($('.seat.chosen'), function (key, item) {
            result += '<div class="ticket"><spring:message code="booking.Row"/>: ' +
                    $(item).data().row + ' <spring:message code="booking.Place"/>:' +
                    $(item).data().seat + '</div>';
        });

        $('.result').html(result);
    }
    function matrixArray(rows, columns, defineNumber) {
        var arr = [];
        for (var i = 0; i < rows; i++) {
            arr[i] = [];
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

    function enableSubmitIfSomeChosen() {
        var countOfChosen = $('.chosen').length;
        if (countOfChosen == 0) {
            $('input[type="submit"]').prop('disabled', true);
        } else {
            $('input[type="submit"]').prop('disabled', false);
        }
    }
</script>
</body>