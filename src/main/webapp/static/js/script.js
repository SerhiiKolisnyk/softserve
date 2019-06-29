$(document).ready(function () {

    $('#doctorlist').show();
    $('.doctor li:first-child a').addClass('active');
    $('.doctor li a').click(function (e) {

        var tabDiv = this.hash;
        $('.doctor li a').removeClass('active');
        $(this).addClass('.active');
        $('.switchgroup').hide();
        $(tabDiv).fadeIn();
        e.preventDefault();

    });
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});
