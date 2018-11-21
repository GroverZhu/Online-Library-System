<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.librarianEntity}">
	<jsp:forward page="../homepage.jsp" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Show New Book</title>
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
		<%@ include file="navbar.jsp"%>
		<%@ include file="sidebar.jsp"%>
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">New Book Item</h3>
						</div>
						<div class="panel-body no-padding">
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>New Book ID</th>
										<th>Book Name</th>
										<th>ISBN</th>
										<th>Author</th>
										<th>Publisher</th>
										<th>Location</th>
									</tr>
								</thead>

								<c:forEach var="bookid" items="${bookIdList }" varStatus="i">
									<tbody>
										<tr>
											<td>${i.count}</td>
											<td>${bookid}</td>
											<td>${newBookName}</td>
											<td>${newISBN}</td>
											<td>${newAuthor}</td>
											<td>${newPublisher }</td>
											<td>${newLocation }</td>
										</tr>
									</tbody>
								</c:forEach>

							</table>
							<br?>
								<div style="width: 550px; margin: 0 auto" align="center">
									<a href="librarianAddBook.jsp" class="demo-button">
										<button class="btn btn-success btn-block">confirm</button>
									</a>
								</div>
								<div class="qrcode"></div>
						</div>
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<jsp:include page="../Footer.jsp" />
	</div>
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
		$("#qrcode").qrcode({
			text : "1234567891",
			size : 100
		});

		function add() {

			window.location.href = "addBook.jsp";

		}
	</script>
</body>
</html>