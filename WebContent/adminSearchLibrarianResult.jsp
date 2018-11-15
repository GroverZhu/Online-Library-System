<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>Search Librarian Result</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<link rel="stylesheet"
	href="assets/vendor/chartist/css/chartist-custom.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="brand">
			<a href="index.jsp"><img src="assets/img/BiblioSoft Logo.png"
				alt="BiblioSoft Logo" class="img-responsive logo"></a>
		</div>
		<div class="container-fluid">
			<div class="navbar-btn">
				<button type="button" class="btn-toggle-fullwidth">
					<i class="lnr lnr-arrow-left-circle"></i>
				</button>
			</div>
			<form class="navbar-form navbar-left"></form>
			<div id="navbar-menu">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <img src="assets/img/user.png"
							class="img-circle" alt="Avatar"> <span>${sessionScope.Admin_Name}</span></a></li>
				</ul>
			</div>
		</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
				<ul class="nav">
					<li><a href="index.jsp" class=""><i class="lnr lnr-home"></i>
							<span>Administrator Index</span></a></li>
					<li><a href="registerLib.jsp" class=""><i
							class="lnr lnr-code"></i> <span>Register Librarian</span></a></li>
					<li><a href="#subPages" data-toggle="collapse"
						class="active"> <i class="lnr lnr-file-empty"></i> <span>Manage
								Librarian</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
						<div id="subPages" class="collapse in">
							<ul class="nav">
								<li><a href="manage-search.jsp" class="active">Search</a></li>
								<li><a href="manage-delete.jsp" class="">Delete</a></li>
							</ul>
						</div></li>
					<li><a href="default-value.jsp" class=""><i
							class="lnr lnr-dice"></i> <span>Default Value</span></a></li>
					<li><a href="modify-password.jsp" class=""><i
							class="lnr lnr-cog"></i> <span>Modify Password</span></a></li>
					<li><a href="AdminLogin.jsp" class=""><i
							class="lnr lnr-linearicons"></i> <span>Logout</span></a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="panel">
						<div class="panel-heading">
							<h1 align="center" class="page-title">Search The Librarian</h1>
						</div>
						<div class="panel-body">
							<table align="center">
								<tr>
									<td width="120px">
										<div align="center">
											<label style="font-size: 20px" class="control-label"
												for="LibrarianID">Librarian ID</label>
										</div>
									</td>
									<td width="550px">
										<form method="post" action="SearchResult">
											<div class="input-group">
												<input class="form-control" type="text" name="searchID"
													id="searchID"
													placeholder="Please Input The Librarian ID You Want To Search">
												<span class="input-group-btn">
													<button type="submit" class="btn btn-primary" type="button">Search</button>
												</span>
											</div>
										</form>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">Result Of Finding</h3>
						</div>
						<div class="panel-body no-padding">
							<form method="post" action="manage-modify.jsp">
							<table class="table">
								<thead>
									<tr>
										<th>Librarian ID</th>
										<th>Librarian Name</th>
										<th>Librarian Password</th>
										<th>Operate</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${sessionScope.SearchLib_ID}</td>
										<td>${sessionScope.SearchLib_Name}</td>
										<td>${sessionScope.SearchLib_Password}</td>
										<th><button type="submit" class="btn btn-primary">
												<i class="fa fa-refresh"></i> Update </button></th>
										
									</tr>
								</tbody>
							</table>
							</form>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
	<!-- END MAIN -->
	<div class="clearfix"></div>
	<footer>
	<div class="container-fluid">
		<p class="copyright">
			Copyright &copy; 2018.Company name All rights reserved.BiblioSoft - Collect from Software
		</p>

	</div>
	</footer>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script>
	$(function() {
		var data, options;

		// headline charts
		data = {
			labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
			series: [
				[23, 29, 24, 40, 25, 24, 35],
				[14, 25, 18, 34, 29, 38, 44],
			]
		};

		options = {
			height: 300,
			showArea: true,
			showLine: false,
			showPoint: false,
			fullWidth: true,
			axisX: {
				showGrid: false
			},
			lineSmooth: false,
		};

		new Chartist.Line('#headline-chart', data, options);


		// visits trend charts
		data = {
			labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
			series: [{
				name: 'series-real',
				data: [200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900],
			}, {
				name: 'series-projection',
				data: [240, 350, 360, 380, 400, 450, 480, 523, 555, 600, 700, 800],
			}]
		};

		options = {
			fullWidth: true,
			lineSmooth: false,
			height: "270px",
			low: 0,
			high: 'auto',
			series: {
				'series-projection': {
					showArea: true,
					showPoint: false,
					showLine: false
				},
			},
			axisX: {
				showGrid: false,

			},
			axisY: {
				showGrid: false,
				onlyInteger: true,
				offset: 0,
			},
			chartPadding: {
				left: 20,
				right: 20
			}
		};

		new Chartist.Line('#visits-trends-chart', data, options);


		// visits chart
		data = {
			labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
			series: [
				[6384, 6342, 5437, 2764, 3958, 5068, 7654]
			]
		};

		options = {
			height: 300,
			axisX: {
				showGrid: false
			},
		};

		new Chartist.Bar('#visits-chart', data, options);


		// real-time pie chart
		var sysLoad = $('#system-load').easyPieChart({
			size: 130,
			barColor: function(percent) {
				return "rgb(" + Math.round(200 * percent/100) + ", " + Math.round(200 * (1.1 - percent / 100)) + ", 0)";
			},
			trackColor: 'rgba(245, 245, 245, 0.8)',
			scaleColor: false,
			lineWidth: 5,
			lineCap: "square",
			animate: 800
		});

		var updateInterval = 3000; // in milliseconds

		setInterval(function() {
			var randomVal;
			randomVal = getRandomInt(0, 100);

			sysLoad.data('easyPieChart').update(randomVal);
			sysLoad.find('.percent').text(randomVal);
		}, updateInterval);

		function getRandomInt(min, max) {
			return Math.floor(Math.random() * (max - min + 1)) + min;
		}

	});
	</script>
</body>

</html>
