<%-- 
    Document   : linechart
    Created on : Oct 4, 2017, 10:44:49 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script async="" src="./lib/analytics.js.download"></script>
        <script src="./lib/moment.min.js.download"></script>
	<script src="./lib/Chart.js.download"></script>
        <style type="text/css">/* Chart.js */
        @-webkit-keyframes chartjs-render-animation{
            from{opacity:0.99}to{opacity:1}
        }@keyframes chartjs-render-animation{
            from{opacity:0.99}to{opacity:1}
        }.chartjs-render-monitor{
            -webkit-animation:chartjs-render-animation 0.001s;
            animation:chartjs-render-animation 0.001s;}
        </style>
        
        <script src="./lib/utils.js.download"></script>
	<style>
    canvas {
        -moz-user-select: none;
        -webkit-user-select: none;
        -ms-user-select: none;
    }
	</style>
      <title>JSP Page</title>
    </head>
 <div style="width:75%;"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
		<canvas id="canvas" width="1013" height="506" class="chartjs-render-monitor" style="display: block; width: 1013px; height: 506px;"></canvas>
	</div>
	<br>
	<br>
	<button id="randomizeData">Randomize Data</button>
	<button id="addData">Add Data</button>
	<button id="removeData">Remove Data</button>
	<script>
		function newDate(days) {
			return moment().add(days, 'd').toDate();
		}

		function newDateString(days) {
			return moment().add(days, 'd').format();
		}

		var color = Chart.helpers.color;
		var config = {
			type: 'line',
			data: {
				datasets: [{
					label: "Dataset with string point data",
					backgroundColor: color(window.chartColors.red).alpha(0.5).rgbString(),
					borderColor: window.chartColors.red,
					fill: false,
					data: [{
						x: newDateString(0),
						y: randomScalingFactor()
					}, {
						x: newDateString(2),
						y: randomScalingFactor()
					}, {
						x: newDateString(4),
						y: randomScalingFactor()
					}, {
						x: newDateString(5),
						y: randomScalingFactor()
					}],
				}, {
					label: "Dataset with date object point data",
					backgroundColor: color(window.chartColors.blue).alpha(0.5).rgbString(),
					borderColor: window.chartColors.blue,
					fill: false,
					data: [{
						x: newDate(0),
						y: randomScalingFactor()
					}, {
						x: newDate(2),
						y: randomScalingFactor()
					}, {
						x: newDate(4),
						y: randomScalingFactor()
					}, {
						x: newDate(5),
						y: randomScalingFactor()
					}]
				}]
			},
			options: {
				responsive: true,
				title:{
					display:true,
					text:"Chart.js Time Point Data"
				},
				scales: {
					xAxes: [{
						type: "time",
						display: true,
						scaleLabel: {
							display: true,
							labelString: 'Date'
						},
                        ticks: {
                            major: {
                                fontStyle: "bold",
                                fontColor: "#FF0000"
                            }
                        }
					}],
					yAxes: [{
						display: true,
						scaleLabel: {
							display: true,
							labelString: 'value'
						}
					}]
				}
			}
		};

		window.onload = function() {
			var ctx = document.getElementById("canvas").getContext("2d");
			window.myLine = new Chart(ctx, config);
		};

		document.getElementById('randomizeData').addEventListener('click', function() {
			config.data.datasets.forEach(function(dataset) {
				dataset.data.forEach(function(dataObj) {
					dataObj.y = randomScalingFactor();
				});
			});

			window.myLine.update();
		});

		document.getElementById('addData').addEventListener('click', function() {
			if (config.data.datasets.length > 0) {
				var numTicks = myLine.scales['x-axis-0'].ticksAsTimestamps.length;
				var lastTime = numTicks ? moment(myLine.scales['x-axis-0'].ticksAsTimestamps[numTicks - 1]) : moment();
				var newTime = lastTime
					.clone()
					.add(1, 'day')
					.format('MM/DD/YYYY HH:mm');

				for (var index = 0; index < config.data.datasets.length; ++index) {
					config.data.datasets[index].data.push({
						x: newTime,
						y: randomScalingFactor()
					});
				}

				window.myLine.update();
			}
		});

		document.getElementById('removeData').addEventListener('click', function() {
			config.data.datasets.forEach(function(dataset, datasetIndex) {
				dataset.data.pop();
			});

			window.myLine.update();
		});
	</script>
    </body>
</html>
