<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<link rel="stylesheet"
	href="assets/vendor/chartist/css/chartist-custom.css">

<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
	<div class="sidebar-scroll">
		<nav>
		<ul class="nav">
			<li><a href="adminHomepage.jsp" class=""><i
					class="lnr lnr-home"></i> <span>Administrator Homepage</span></a></li>
			<li><a href="adminRegisterLibrarian.jsp" class=""><i
					class="lnr lnr-code"></i> <span>Register Librarian</span></a></li>
			<li><a href="#subPages" data-toggle="collapse" class="collapsed">
					<i class=""></i> <span>Manage Librarian</span> <i
					class="icon-submenu lnr lnr-chevron-left"></i>
			</a>
				<div id="subPages" class="collapse">
					<ul class="nav">
						<li><a href="AdminViewLibrarian" class="">View Librarian</a></li>
						<li><a href="adminSearchLibrarian.jsp" class="">Search
								Librarian</a></li>
						<li><a href="adminModifyLibrarianBefore.jsp" class="">Modify
								Librarian</a></li>
						<li><a href="adminDeleteLibrarian.jsp" class="">Delete
								Librarian</a></li>
					</ul>
				</div></li>

			<li><a href="adminUpdateInfo.jsp" class=""><i
					class="lnr lnr-cog"></i> <span>Modify Information</span></a></li>
			<li><a href="#" class="" onclick="logout();"> <i
					class="lnr lnr-linearicons"></i> <span>Logout</span></a></li>
		</ul>
		</nav>
	</div>
</div>
<jsp:include page="Footer.jsp" />
<script>
	function logout() {
		var result = confirm("Please make sure to logout!");
		if (result == true) {
			window.location.href = "DestorySession";
		} else {

		}
	}
</script>
</html>