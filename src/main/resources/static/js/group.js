function openModal(selector) {
    $(selector).modal('show');
}

function closeModal(selector) {
    $(selector).modal('hide');
}

function saveSpending() {
    var amount = $('#amount').val();

    $.ajax({
        url: "/api/spending",
        type: "post",
        data: {
            groupId: groupId,
            amount: amount
        }
    }).done(function (response) {
        $('#spending-body').replaceWith(response);
    }).always(function () {
        closeModal('#addSpendingModal');
    });
}