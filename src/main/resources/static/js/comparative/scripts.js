let resultField = $('#result');
let progress = $('#report-progress');
let month = $('#month');
let prevResult = $('#prev-result');

let sbeCode;
let isSuperVisor;
let isAdmin;
let url = 'report-for-bm';
let data;
let monthId;

let reportPage;
let reportPageStack = [];

let jqXHR;

function newReport(element)
{
    let tmp = element.getAttribute('value');

    if(tmp)
    {
        sbeCode = tmp;
        isSuperVisor = element.getAttribute('isSuperVisor');
        isAdmin = element.getAttribute('isAdmin');

        reportPageStack.push({
            content: resultField.html(),
            url: url,
            monthId: monthId,
            sbeCode: sbeCode
        })

        getAjaxData();
    }
}

function sortBy(n)
{
    let table, rows, switching, i, x, y, shouldSwitch, dir, switchCount = 0;
    table = document.getElementById('data-table');
    switching = true;
    dir = 'desc';
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName('td')[n].getElementsByTagName('div')[0].getElementsByTagName('span')[0];
            y = rows[i + 1].getElementsByTagName('td')[n].getElementsByTagName('div')[0].getElementsByTagName('span')[0];
            if (dir === 'asc') {
                if (parseFloat(x.innerHTML.toLowerCase()) > parseFloat(y.innerHTML.toLowerCase())) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir === 'desc') {
                if (parseFloat(x.innerHTML.toLowerCase()) < parseFloat(y.innerHTML.toLowerCase())) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchCount ++;
        } else {
            if (switchCount === 0 && dir === 'desc') {
                dir = 'asc';
                switching = true;
            }
        }
    }
}

$('.select-picker').selectpicker({
    sanitize: true,
    actionsBox: true,
    width: '300px',
    liveSearchNormalize: true,
    noneResultsText: 'Tapılmadı: {0}'
})

function monthChanged()
{
    jqXHR.abort();
    resetObjects();
    reselectMonth();

    if (sbeCode && sbeCode.startsWith('SM'))
        data = 'sbe-code=' + sbeCode + '&month-id=' + monthId;
    else
        data = 'month-id=' + monthId;

    ajaxRequest();
}

function resetObjects()
{
    resultField.removeClass('alert alert-danger').html('');
    progress.attr('hidden', false);
    monthId = month.val();
}

function getAjaxData() {
    resetObjects();

    if (sbeCode && sbeCode.startsWith('SM')) {
        prevResult.removeClass('d-none');
        data = 'sbe-code=' + sbeCode;

        if (isAdmin || sbeCode.startsWith('SM')) {
            url = 'report-for-sub-bm-sbe';
        } else if (isSuperVisor) {
            url = 'report-for-sub-bm';
        }
    }

    if (data)
        data += '&month-id=' + monthId;
    else
        data = 'month-id=' + monthId;

    ajaxRequest();
}

let ajaxRequest = function () {
    jqXHR = $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        data: data,
        responseJSON: {
            error: null
        }
    }).done(function (data) {
        displaySuccess(data);
    }).fail(function (jqXHR) {
        displayError(jqXHR);
    });
    data = '';
}

function displaySuccess(data) {
    resultField.html(data);
    progress.attr('hidden', true)
}

function displayError(jqXHR) {
    let error = jqXHR.responseJSON ? jqXHR.responseJSON.error : jqXHR.statusText;
    resultField.addClass('alert alert-danger').html(error);
    progress.attr('hidden', true)
}

function reloadPrevResult()
{
    if (jqXHR)
        jqXHR.abort();

    reportPage = reportPageStack.pop();

    resultField.removeClass('alert alert-danger').html(reportPage.content);
    url = reportPage.url;
    monthId = reportPage.monthId;
    sbeCode = reportPage.sbeCode;

    if (reportPageStack.length === 0)
        prevResult.addClass('d-none');

    reselectMonth();
}

function reselectMonth()
{
    month.find('option').attr('selected', false);
    $('#month option[value='+monthId+']').attr('selected', true);
    month.val(monthId);
    month.selectpicker('refresh');
}