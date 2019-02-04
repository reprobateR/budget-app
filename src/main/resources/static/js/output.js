const outputCharUrl = 'http://localhost:8083/budget-app/indicators';

var displayLoader = function () {
    document.getElementById('ajaxLoader').style.display = 'block';
    document.getElementById('loaderSpinner').style.display = 'inline-block';
}

var hideLoader = function () {
    myVar = setTimeout(function () {
        document.getElementById('ajaxLoader').style.display = 'none';
    }, 1000);
}

window.onload = function () {
  displayLoader();
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      console.log('Successful response ');
      console.log(this.responseText);
      console.log(JSON.parse(this.responseText));
      let charts = JSON.parse(this.responseText);
      loadCharts(charts);
      hideLoader();
    } else if (this.readyState == 4 && this.status != 200){
    	displayStatusMessages('error', 'Error Loading Charts');
    	hideLoader();
    }
  };
  xhttp.open("GET", outputCharUrl, true);
  xhttp.send();
}

var loadCharts = function (charts) {

  let barChart = document.getElementById('barChart').getContext('2d');
  let pieChart = document.getElementById('pieChart').getContext('2d');

  // Global Options
  Chart.defaults.global.defaultFontFamily = 'Lato';
  Chart.defaults.global.defaultFontSize = 18;
  Chart.defaults.global.defaultFontColor = '#777';

  //pieChart.canvas.width = 50;
  //pieChart.canvas.height = 50;
  if (charts[0].type === 'bar') {
    let massPopChart = new Chart(barChart, charts[0]);
  }
  if(charts[1].type === 'pie'){
    let diffPieChart = new Chart(pieChart, charts[1]);
  }

}