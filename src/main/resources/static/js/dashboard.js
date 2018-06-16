window.onload = function () {
    showChart();
}

function showChart() {
    $.ajax({
        url: "/api/category/chart",
        type: "get",
        dataType: 'json'
    }).done(function (response) {
        prepareChartData(response);
    });
}

function prepareChartData(obj) {
    var data = [];
    var labels = [];
    var bgcolor = [];
    for (var category in obj) {
        labels.push(category);
        data.push(obj[category]);
        bgcolor.push(getRandomColor());
    }

    renderChart(data, labels, bgcolor);
}

function renderChart(data, labels, bgcolor) {
    var ctx = $('#category-chart');
    var chart = new Chart(ctx, {
        type: 'pie',
        data: {
            datasets: [{
                data: data,
                backgroundColor: bgcolor
            }],
            labels: labels
        }
    });
};

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}