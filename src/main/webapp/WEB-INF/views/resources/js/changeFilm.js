$(function () {
    var $formFilm = $('[name = "form1"]');
    $('[name = "menu"]').onchange($formFilm.submit());
})