var joinGroup = function (id) {
    console.log("test");
    console.log(id);
    $.ajax({
        type: 'POST',
        url: '/groups/join',
        data: {
            'id': id
        }
    });
};