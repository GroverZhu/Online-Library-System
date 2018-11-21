<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.AdministratorEntity}">
	<jsp:forward page="../homepage.jsp" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Result</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

<!-- MAIN CSS -->
<link rel="stylesheet" href="assets/css/main.css">

<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<jsp:include page="adminNavbar.jsp" />
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<jsp:include page="adminLeftSlider.jsp" />
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel">
						<div class="panel-heading">
							<h1 align="center" class="page-title">${message}</h1>
						</div>

					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
			<!-- END MAIN -->
			<div class="clearfix"></div>

			<!-- END WRAPPER -->
			<!-- Javascript -->
			<script src="assets/vendor/jquery/jquery.min.js"></script>
			<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
			<script
				src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
			<script
				src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
			<script src="assets/vendor/chartist/js/chartist.min.js"></script>
			<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>