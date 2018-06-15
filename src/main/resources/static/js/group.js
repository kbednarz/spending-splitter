function openModal(selector) {
    $(selector).modal('show');
}

function saveSpending() {
    var amount = $('#amount').val();
    
    $.ajax({
        url: "/api/spending",
        type: "post",
        dataType : "json",
        data: {
            groupId: groupId,
            amount: amount
        }
    }).done(function (response) {
        console.log('x')
        console.log(response);
    }).fail(function(){});
}