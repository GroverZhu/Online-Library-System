<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Reader Information</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="../assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="../assets/vendor/linearicons/style.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="../assets/css/main.css">
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="../assets/css/demo.css">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="../assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="../assets/img/favicon.png">
</head>

<body>
	<c:if test="${empty sessionScope.librarianEntity}">
		<jsp:forward page="../homepage.jsp" />
	</c:if>
	<!-- WRAPPER -->
	<div id="wrapper">
		<%@ include file="navbar.jsp"%>
		<%@ include file="sidebar.jsp"%>
		<!-- MAIN -->

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">Show Reader Information</h3>
					<div class="row">
						<!-- BASIC TABLE -->
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Show</h3>
							</div>
							<div class="panel-body">
								<c:if test="${not empty currentList }">
									<h1>Show Current Borrow</h1>
									<table align="center" class="table">
										<tr>
											<td>Number</td>
											<td>Reader ID</td>
											<td>Reader Name</td>
											<td>Book Name</td>
											<td>Book ID</td>
											<td>Borrow Time</td>
											<td>Due Return Time</td>
											<td>Librarian ID</td>
											<td>Librarian Name</td>
											<td>State</td>
										</tr>
										<c:forEach var="item" items="${currentList}" varStatus="i">
											<tr>
												<td>${i.count }</td>
												<td>${item.readerId}</td>
												<td>${item.readerName }</td>
												<td>${item.bookName }</td>
												<td>${item.bookId }</td>
												<td>${item.borrowTime }</td>
												<td>${item.returnTime }</td>
												<td>${item.borrowLibrarianId }</td>
												<td>${item.borrowLibrarianName }</td>
												<td>${item.state }</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${not empty historyList }">
									<h1>Show History Borrow</h1>
									<table align="center" class="table">
										<tr>
											<td>Number</td>
											<td>Reader ID</td>
											<td>Reader Name</td>
											<td>Book Name</td>
											<td>Book ID</td>
											<td>Borrow Time</td>
											<td>Lend Librarian ID</td>
											<td>Lend Librarian Name</td>
											<td>Return Time</td>
											<td>Return Librarian ID</td>
											<td>Return Librarian Name</td>
											<td>State</td>
										</tr>
										<c:forEach var="item" items="${historyList}" varStatus="i">
											<tr>
												<td>${i.count }</td>
												<td>${item.readerId}</td>
												<td>${item.readerName }</td>
												<td>${item.bookName }</td>
												<td>${item.bookId }</td>
												<td>${item.borrowTime }</td>
												<td>${item.borrowLibrarianId }</td>
												<td>${item.borrowLibrarianName }</td>
												<td>${item.returnTime }</td>
												<td>${item.returnLibrarianId }</td>
												<td>${item.returnLibrarianName }</td>
												<td>${item.state }</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
								<c:if test="${not empty borrowCart }">
									<h1>Show History Borrow</h1>
									<table align="center" class="table">
										<tr>
											<td>Number</td>
											<td>Reader ID</td>
											<td>Reader Name</td>
											<td>Book Name</td>
											<td>Book ID</td>
											<td>Submit Time</td>
										</tr>
										<c:forEach var="item" items="${borrowCart}" varStatus="i">
											<tr>
												<td>${i.count }</td>
												<td>${item.readerId}</td>
												<td>${item.readerName }</td>
												<td>${item.bookName }</td>
												<td>${item.bookId }</td>
												<td>${item.submitTime }</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
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
	<jsp:include page="../Footer.jsp" />
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</body>
</html>
