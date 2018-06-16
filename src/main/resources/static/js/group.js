function openModal(selector) {
    $(selector).modal('show');
}

function closeModal(selector) {
    $(selector).modal('hide');
}

function saveSpending(groupId) {
    var amount = $('#addSpendingModal #amount');
    var description = $('#addSpendingModal #description');
    var category = $('#addSpendingModal #category');

    $.ajax({
        url: "/api/spending",
        type: "post",
        data: {
            groupId: groupId,
            amount: amount.val(),
            description: description.val(),
            category: category.val()
        }
    }).done(function (response) {
        $('#spending-body').replaceWith(response);
        updateBalance(groupId);
    }).always(function () {
        amount.val('')
        description.val('')
        closeModal('#addSpendingModal');
    });
}

function deleteSpending(groupId, spendingId) {
    $.ajax({
        url: "/api/spending/" + spendingId,
        type: "delete",
    }).done(function (response) {
        $('#spending-body').replaceWith(response);
        updateBalance(groupId);
    });
}

function updateBalance(groupId) {
    $.ajax({
        url: "/api/spending/balance",
        type: "get",
        data: {
            groupId: groupId
        }
    }).done(function (response) {
        $('#balance').html(response);
    });
}