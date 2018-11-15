<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="en">

<head>
	<title>Reader Search Book</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<link rel="stylesheet" href="assets/vendor/chartist/css/chartist-custom.css">
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
							class="img-circle" alt="Avatar"> <span>${sessionScope.Tele}</span></a></li>
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
						<li><a href="charts.jsp" class="active"><i class="lnr lnr-chart-bars"></i> <span>Search</span></a></li>
						<li><a href="panels.jsp" class=""><i class="lnr lnr-cog"></i> <span>Borrow History</span></a></li>
						<li><a href="notifications.jsp" class=""><i class="lnr lnr-alarm"></i> <span>Return History</span></a></li>
						<li><a href="ReaderIndex.jsp" class=""><i class="lnr lnr-dice"></i> <span>Reader Index</span></a></li>
						<li><a href="typography.jsp" class=""><i class="lnr lnr-text-format"></i> <span>Fine</span></a></li>
						<li><a href="icons.jsp" class=""><i class="lnr lnr-linearicons"></i> <span>Reserve</span></a></li>
					
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
				<div class="tab-content">
						<div class="tab-pane active" id="panel-1">
				
						
	<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
							<!-- BASIC TABLE -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Search</h3>
								</div>
								<div class="panel-body">
							<form class="navbar-form navbar-left" role="search"
								align="center" method="post"
								action="SearchBookForReader">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp; <select id="input" name="style">
									<option selected value="Please select info type">Please
										select</option>
									<option value="bookName" selected="selected">Book name</option>
									<option value="author">author</option>
									<option value="publisher">publisher</option>
								</select>
								<div class="form-group" align="center">
									<input type="text" name="name" placeholder="begin to search..."
										class="form-control" style="width: 400px;" />
								</div>
								<button type="submit" name="submit" class="btn btn-success">Search</button>

							</form>

							<br />
							<br />
							<br />
									<table class="table">
										<thead>
											<tr>
												<th>ISBN</th>
												<th>Book_name</th>
												<th>Author</th>
												<th>Publisher</th>
												<th>Edition</th>
												<th>Statue</th>
												
											</tr>
											
											<!-- 展示书籍信息 -->
											
										</thead>
										
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
	
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>

</body>

</html>
