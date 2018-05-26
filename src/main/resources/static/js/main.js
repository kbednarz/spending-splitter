var test = function () {
    console.log("test");
    $.ajax({
        type: 'POST',
        url: 'groups/join',
        data: {
            'id': 1
        }
    });
};