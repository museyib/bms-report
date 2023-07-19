
let dateFormat = 'yy-mm-dd';
let submit = $('#submit');
let cancel = $('#cancel');

$(function () {
    $('#start-date').datepicker({
        dateFormat: dateFormat,
        changeMonth: true,
        changeYear: true
    }).val();

    $('#end-date').datepicker({
        dateFormat: dateFormat,
        changeMonth: true,
        changeYear: true
    }).val()
});

let today = new Date();
let firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);

$(function () {
    $('#start-date').val($.datepicker.formatDate(dateFormat, firstDayOfMonth, null));
    $('#end-date').val($.datepicker.formatDate(dateFormat, new Date(), null));

    $('#show').click(function (e) {
        e.preventDefault()
        alert($('#start-date').val())
    })
})

let url = 'sbe-report';

$('#sbe').click(function () {
    $('#select-sbe').attr('hidden', false)
    $('#select-whs').attr('hidden', true)
    $(this).blur()
    url = 'sbe-report';
})
$('#whs').click(function () {
    $('#select-sbe').attr('hidden', true)
    $('#select-whs').attr('hidden', false)
    $(this).blur()
    url = 'stock-report';
})

submit.click(function (e) {
    e.preventDefault();

    if (url === 'sbe-report' && $('#sbe-list').val().length === 0) {
        alert('Ən az bir təmsilçi seçməlisiniz!');
        exit();
    }
    if (url === 'stock-report' && $('#whs-list').val().length === 0) {
        alert('Ən az bir anbar seçməlisiniz!');
        exit();
    }
    if ($('#price-list').val().length === 0) {
        alert('Qiymət seçməlisiniz!');
        exit();
    }


    $(this).attr('disabled', true);
    cancel.attr('disabled', false);
    $('#result').removeClass('alert alert-danger').html("");
    $('#report-progress').attr('hidden', false);
    getAjaxData();
})

cancel.click(function (e) {
    e.preventDefault();
    $('#result').html("");
    $('#report-progress').attr('hidden', true)
    $('#submit').attr('disabled', false);
    $(this).attr('disabled', true);
    jqXHR.abort();
})

let request = function () {
    let startDate = $('#start-date').val();
    let endDate = $('#end-date').val();
    let priceCode = $('#price-list').val();
    let sbeList = $('#sbe-list').val();
    let whs_list = $('#whs-list').val();
    let data = '';
    if (url === 'sbe-report')
        data = 'start-date=' + startDate
            + '&end-date=' + endDate
            + '&sbe-list=' + sbeList
            + '&price-code=' + priceCode;

    else if (url === 'stock-report')
        data = 'start-date=' + startDate
            + '&end-date=' + endDate
            + '&whs-list=' + whs_list
            + '&price-code=' + priceCode;

    jqXHR = $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        data: data,
        responseJSON: null
    }).done(function (data) {
        displaySuccess(data);
    }).fail(function (jqXHR) {
        displayError(jqXHR);
    });
}


function getAjaxData() {
    request();
}

function displaySuccess(data) {
    $('#result').html(data);
    $('#report-progress').attr('hidden', true)
    submit.attr('disabled', false);
    cancel.attr('disabled', true);
}

function displayError(jqXHR) {
    let error = jqXHR.responseJSON ? jqXHR.responseJSON.error : jqXHR.statusText;
    $('#result').addClass('alert alert-danger').html(error);
    $('#report-progress').attr('hidden', true)
    submit.attr('disabled', false);
    cancel.attr('disabled', true);
}

$('.select-picker').selectpicker({
    liveSearchPlaceholder: 'Axtar',
    sanitize: true,
    actionsBox: true,
    selectAllText: 'Hamısını seç',
    deselectAllText: 'Seçimi sil',
    width: '50vw',
    liveSearchNormalize: true,
    noneResultsText: 'Tapılmadı: {0}'
})