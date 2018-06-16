window.onload = function () {
    var ctx = $('#category-chart');
    var chart = new Chart(ctx, {
        type: 'pie',
        data: {
            datasets: [{
                data: [10, 20, 30],
                backgroundColor: [
                    '#03A9F4', '#E91E63', '#8BC34A'
                ]
            }],
            labels: [
                'Grocery',
                'Restaurant',
                'Holiday'
            ]
        }
    });
};