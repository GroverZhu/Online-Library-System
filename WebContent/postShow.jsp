<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="fullscreen-bg">

<head>
<title>Show Post</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
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
			<img src="assets/img/BiblioSoft Logo.png"
				alt="BiblioSoft Logo" class="img-responsive logo">
		</div>
		<div class="container-fluid">
			<div id="navbar-menu">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a class="update-pro" href="home_page.jsp" title="Back To Home Page" ><i class="fa fa-rocket"></i> <span>Back To Home Page</span></a>
					</li>
				</ul>
			</div>
		</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- MAIN -->
		<div>
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="panel">
						<div class="panel-heading">
							<h1 align="center" class="page-title"></h1>
						</div>
						<div class="panel-body">
							<h1 align="center" class="page-title">${sessionScope.title}</h1>
							<table align="center">
								<tr>
									<td align="center" width="350px">Writer:${sessionScope.writer}</td><td align="center" width="350px">Time:${sessionScope.time}</td>
								</tr>
							</table><hr />
							<table align="center">
								<tr>
									<td width="800px">${sessionScope.body}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
	<!-- END MAIN -->
	<footer>
	<div class="container-fluid">
		<p class="copyright">Copyright &copy; 2018.BiblioSoft All rights
			reserved.</p>
	</div>
	</footer>
</body>

</html>
