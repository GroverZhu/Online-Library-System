<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Homepage</title>
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
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="brand">
			<img src="assets/img/BiblioSoft Logo.png" alt="BiblioSoft Logo"
				class="img-responsive logo">
		</div>
		<div class="container-fluid">
			<div id="navbar-menu">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="update-pro" href="Initialization" title="Login"><i
							class="fa fa-rocket"></i> <span>Login</span></a></li>
				</ul>
			</div>
		</div>
		</nav>
		<!-- MAIN -->
		<div>
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<br /> <br /> <br /> <br />
					<div class="panel">
						<div class="panel-heading">
							<br />
							<div class="logo text-center">
								<img src="assets/img/logo-big.png" alt="BiblioSoft Logo">
								<br />
								<br />
								<h1>Recommend Books</h1>
							</div>
						</div>
						<div class="panel-body">
							<table align="center" style="text-align: center">
								<tr>
									<td width="350px"><h3>Poetry</h3></td>
									<td width="350px"><h3>Prose</h3></td>
									<td width="350px"><h3>Novel</h3></td>
								</tr>
								<tr>
									<td width="350px"><img src="assets/img/poetry.jpg"
										width="350px"></td>
									<td width="350px"><img src="assets/img/prose.jpg"
										width="350px"></td>
									<td width="350px"><img src="assets/img/novel.jpg"
										width="335px"></td>
								</tr>
								<tr>
									<td width="350px"><br />
									<h3>Author:Lin Huiyin</h3></td>
									<td width="350px"><br />
									<h3>Author:Yang Jiang</h3></td>
									<td width="350px"><br />
									<h3>Author:Qian Zhongshu</h3></td>
								</tr>
								<tr>
									<td width="350px"></td>
									<td width="350px"></td>
									<td width="350px"></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="Footer.jsp" />
</body>

</html>
