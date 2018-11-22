<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.librarianEntity}">
	<jsp:forward page="../homepage.jsp" />
</c:if>
<%@ taglib uri="BookLocationTag" prefix="tag"%>
<html lang="en">
<head>
<title>Librarian Search Book</title>
<meta charset="utf-8">
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
	<div id="wrapper">
		<%@ include file="navbar.jsp"%>
		<%@ include file="sidebar.jsp"%>
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">Search a book</h3>
					<div class="row">
						<div class="col-md-12">

							<!-- INPUTS -->
							<form method="post" action="LibrarianSearchBook">
								<div class="panel">
									<div class="panel-heading">
										<table>
											<tr>
												<td width=150px height=64px>
													<div class="form-group has-success has-feedback">
														<label class="control-label" for="searchBy">
															Search By</label>
													</div>
												</td>
												<td width=350px height=64px><tag:searchBy></tag:searchBy>
												</td>
											</tr>
										</table>

									</div>
									<div class="panel-body">
										<div class="col-md-12">
											<div class="input-group">
												<input class="form-control" type="text" name="keyword"
													placeholder="Input Keywords:Chinese Character, Book Name, Author or ISBN">
												<span class="input-group-btn">
													<button class="btn btn-primary" type="submit">Search</button>
												</span>
											</div>
										</div>
									</div>
								</div>
							</form>

							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Result Of Search</h3>
								</div>
								<div class="panel-body no-padding">
									<table class="table">
										<thead>
											<tr>
												<th>ISBN</th>
												<th>Book Name</th>
												<th>Publisher</th>
												<th>Author(s)</th>
												<th>Price</th>
												<th>Operate</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty sessionScope.bookList }">
												<c:forEach var="item" items="${sessionScope.bookList }"
													varStatus="i">
													<tr>
														<td>${item.ISBN }</td>
														<td>${item.name }</td>
														<td>${item.publisher.name }</td>
														<td>${item.authors }</td>
														<td>${item.price }</td>
														<th><a href="LibrarianSearchBook?isbn=${item.ISBN}">
																<button type="button" class="btn btn-primary">
																	<i class="fa fa-refresh"></i> Details
																</button>
														</a></th>
													</tr>
												</c:forEach>

											</c:if>
											<c:if test="${not empty sessionScope.bookEntity }">
												<tr>
													<td>${sessionScope.bookEntity.ISBN }</td>
													<td>${sessionScope.bookEntity.name }</td>
													<td>${sessionScope.bookEntity.publisher.name }</td>
													<td>${sessionScope.bookEntity.authors }</td>
													<td>${sessionScope.bookEntity.price }</td>
													<th><a
														href="LibrarianSearchBook?isbn=${sessionScope.bookEntity.ISBN}">
															<button type="button" class="btn btn-primary">
																<i class="fa fa-refresh"></i> Details
															</button>
													</a></th>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END MAIN CONTENT -->

		<!-- END MAIN -->
		<div class="clearfix"></div>
		<jsp:include page="../Footer.jsp" />
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