<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.librarianEntity}">
	<jsp:forward page="../homepage.jsp" />
</c:if>
<html lang="en">
<head>
<title>Book Details</title>
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
<link rel="stylesheet"
	href="../assets/vendor/chartist/css/chartist-custom.css">
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

<!--  pay the deposit to submit the registration information  -->
<script>
	function check() {
		var checkbox = document.getElementByID("paid"); //checkbox's id
		if (checkbox.checked == true) { //selected
			document.getElementByID("submit").style.backgroundColor = "red";
			document.getElementByID("submit").removeAttribute = "disabled"; //remove disabled
		} else {
			document.getElementByID("submit").disabled = "disabled";
		}
	}
</script>

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
					<h3 class="page-title">Information</h3>
					<div class="row">
						<div class="col-md-12">

							<!-- INPUTS -->
							<div class="panel">
								<div class="panel-heading"></div>

								<div class="panel-body">
									ISBN:<input type="text" class="form-control" name="readerName"
										readonly="readonly" value="${information.ISBN }" /> <br>
									Book Name:<input type="text" class="form-control"
										name="password" readonly="readonly"
										value="${information.name}" /> <br> Publisher:<input
										type="text" class="form-control" name="readerName"
										readonly="readonly" value="${information.publisher.name }" />
									<br> Authors:<input type="text" class="form-control"
										name="readerName" readonly="readonly"
										value="${information.authors }" /> <br> Price:<input
										type="text" class="form-control" name="readerName"
										readonly="readonly" value="${information.price }" /> <br>
									Description:
									<textarea class="form-control" name="readerName"
										readonly="readonly">
										${information.description }</textarea>
									<br>
								</div>
							</div>
							<!-- END INPUTS -->
							<table class="table">
								<thead>
									<tr>
										<th>Number</th>
										<th>Book ID</th>
										<th>Book Location</th>
										<th>State</th>
										<th>Operate</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${library }" varStatus="i">
										<tr>
											<td>${i.count }</td>
											<td>${item.id }</td>
											<td>${item.location }</td>
											<td>${item.state }</td>
											<th><a href="LibrarianEditBook?book_id=${item.id}">
													<button type="button" class="btn btn-primary">
														<i class="fa fa-refresh"></i> Edit
													</button>
											</a></th>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<nav>

								<ul class="pager">
									<li><a href="?start=0&bookInfoISBN=${information.ISBN }">首
											页</a></li>
									<li><a
										href="?start=${pre}&bookInfoISBN=${information.ISBN }">上一页</a></li>
									<li><a
										href="?start=${next}&bookInfoISBN=${information.ISBN }">下一页</a></li>
									<li><a
										href="?start=${last}&bookInfoISBN=${information.ISBN }">末
											页</a></li>
								</ul>
							</nav>

						</div>
					</div>
					<!-- END INPUTS -->
					<!-- INPUT SIZING -->

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
	<script src="../assets/vendor/jquery/jquery.min.js"></script>
	<script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="../assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="../assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="../assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="../assets/scripts/klorofil-common.js"></script>

</body>

</html>
