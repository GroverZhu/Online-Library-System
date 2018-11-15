<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">

<head>
	<title>Reader Modify Information</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	<form method="post" action="ReaderIndex.jsp">
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
							class="img-circle" alt="Avatar"> <span>Tele</span></a></li>
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
					
			
						<li><a href="elements.jsp" class=""><i class="lnr lnr-code"></i> <span>Modify Password</span></a></li>
						<li><a href="charts.jsp" class=""><i class="lnr lnr-chart-bars"></i> <span>Search</span></a></li>
						<li><a href="panels.jsp" class=""><i class="lnr lnr-cog"></i> <span>Borrow History</span></a></li>
						<li><a href="notifications.jsp" class=""><i class="lnr lnr-alarm"></i> <span>Return History</span></a></li>
					
						<li><a href="ReaderIndex.jsp" class="active"><i class="lnr lnr-dice"></i> <span>Reader Index</span></a></li>
						<li><a href="typography.jsp" class=""><i class="lnr lnr-text-format"></i> <span>Fine</span></a></li>
						<li><a href="icons.jsp" class=""><i class="lnr lnr-linearicons"></i> <span>Reserve</span></a></li>
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
					<h3 class="page-title">Reader Index</h3>
					<div class="row">
							<!-- BASIC TABLE -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Reader Index
									<input type="SUBMIT" name="cis1" value="Return" onclick="javascript:this.form.action='ReaderIndex.jsp';">
									</h3>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>Reader_id</th>
												<th>Reader_name</th>
												<th>sex</th>
												<th>Tele</th>
												<th>MaxBorrow</th>
												
											</tr>
										</thead>
										<tbody>
											<tr>
												<!-- Reader信息 -->
												
											</tr>
											
											<tr>
											<td><input type="SUBMIT" name="cis2" value="Modify" onclick="javascript:this.form.action='ReaderIndex.jsp';"></td>
											</tr>
										
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
	
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	</form>
</body>

</html>
