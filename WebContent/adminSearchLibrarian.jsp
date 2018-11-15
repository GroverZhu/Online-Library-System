<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>Search Librarian</title>
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
			<div id="navbar-menu">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#"> <img
							src="assets/img/user.png" class="img-circle" alt="Avatar">
							<span>${sessionScope.Admin_Name}</span>
					</a></li>
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
					<li><a href="#subPages" data-toggle="collapse" class="active">
							<i class="lnr lnr-file-empty"></i> <span>Manage Librarian</span>
							<i class="icon-submenu lnr lnr-chevron-left"></i>
					</a>
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
					<li><a href="#" class="" onclick="logout();"><i
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
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
	<!-- END MAIN -->
	<div class="clearfix"></div>
	<footer>
	<div class="container-fluid">
		<p class="copyright">Copyright &copy; 2018.Company name All rights
			reserved.BiblioSoft - Collect from Software</p>

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
	function logout(){
		var result = confirm("Please make sure.Logout?");  
	    if (result == true) {  
	    	window.location.href="DestroySession"; 
	    } else {  
	        
	    }
	}
	</script>
</body>

</html>
