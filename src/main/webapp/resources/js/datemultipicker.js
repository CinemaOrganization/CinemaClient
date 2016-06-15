$(".datepicker").datepicker({
    monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май",
        "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
    dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
    firstDay: 1,
    dateFormat: "yy-mm-dd",
    minDate: new Date(),
    beforeShowDay: function(date) {
        var date1 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input1").val());
        var date2 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input2").val());
        return [true, date1 && ((date.getTime() == date1.getTime()) || (date2 && date >= date1 && date <= date2)) ? "dp-highlight" : ""];
    },
    onSelect: function(dateText, inst) {
        var date1 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input1").val());
        var date2 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input2").val());
        if (!date1 || date2) {
            $("#input1").val(dateText);
            $("#input2").val("");
            $(this).datepicker();
        } else {
            $("#input2").val(dateText);
            $(this).datepicker();
        }
    }
});