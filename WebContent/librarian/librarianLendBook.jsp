<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.librarianEntity}">
	<jsp:forward page="../homepage.jsp" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Librarian Lend Book</title>
<meta charset="UTF-8">
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
	<!-- WRAPPER -->
	<div id="wrapper">
		<%@ include file="navbar.jsp"%>
		<%@ include file="sidebar.jsp"%>
		<!-- MAIN -->

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">Lend Book</h3>
					<div class="row">
						<!-- BASIC TABLE -->
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Librarian Lend Book</h3>
							</div>
							<div class="panel-body">
								<form method="post" onsubmit="return inputCheck1(this)"
									action="LibrarianLendBook">
									<label class="control-label" for="librarianName">Reader
										ID</label>
									<c:if test="${not empty readerEntity}">
										<input type="text" name="readerId" id="readerID1"
											value="${readerEntity.id }" readonly="readonly" />
									</c:if>
									<c:if test="${empty readerEntity}">
										<input type="text" name="readerId" id="readerID2" />
									</c:if>
									<input type="submit" class="btn btn-primary"
										value="Show Reader Information" />
								</form>
								<br />
								<c:if test="${not empty readerEntity}">
									<table class="table">
										<tr>
											<td>Reader ID:</td>
											<td>${readerEntity.id }</td>
										</tr>
										<tr>
											<td>Reader Name:</td>
											<td>${readerEntity.name }</td>
										</tr>
										<tr>
											<td>Reader Email:</td>
											<td>${readerEntity.email }</td>
										</tr>
										<tr>
											<td>Reader State:</td>
											<td>${readerEntity.state }</td>
										</tr>
										<tr>
											<td>Current Borrow Count:</td>
											<td>${total}</td>
										</tr>
									</table>
									<form method="post" onsubmit="return inputCheck2(this)"
										action="LibrarianLendBook">
										Book ID: <input type="text" name="bookId" id="bookID" /> <input
											type="hidden" name="readerId" value="${readerEntity.id }" />
										<input type="submit" class="btn btn-primary" value="Lend Book" />
									</form>


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
		var isReaderId = /^\d{4,10}$/;

		function inputCheck1(form) {
			if (!isReaderId.test(form.readerID2.value)) {
				alert("Invalid reader ID(Should Be 4-10 Numbers!), Please Input Again!");
				form.readerID2.focus();
				return false;
			}
			if (!isReaderId.test(form.readerID1.value)) {
				alert("Invalid reader ID(Should Be 4-10 Numbers!), Please Input Again!");
				form.readerID1.focus();
				return false;
			}

		}

		var isBookId = /^\d{1,10}$/;
		function inputCheck2(form) {
			if (!isBookId.test(form.bookID.value)) {
				alert("Invalid Book ID(Should Be 1-10 Numbers!), Please Input Again!");
				form.bookID.focus();
				return false;
			}
		}
	</script>
</body>
</html>