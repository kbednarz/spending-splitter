function openModal(selector) {
    $(selector).modal('show');
}

function closeModal(selector) {
    $(selector).modal('hide');
}

function saveSpending(groupId) {
    var amount = $('#addSpendingModal #amount');
    var description = $('#addSpendingModal #description');

    $.ajax({
        url: "/api/spending",
        type: "post",
        data: {
            groupId: groupId,
            amount: amount.val(),
            description: description.val()
        }
    }).done(function (response) {
        $('#spending-body').replaceWith(response);
    }).always(function () {
        amount.val('')
        description.val('')
        closeModal('#addSpendingModal');
    });
}

function deleteSpending(spendingId) {
    $.ajax({
        url: "/api/spending/" + spendingId,
        type: "delete",
    }).done(function (response) {
        $('#spending-body').replaceWith(response);
    });
}