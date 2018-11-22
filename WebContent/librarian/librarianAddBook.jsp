<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="BookLocationTag" prefix="tag"%>
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
					<h3 class="page-title">Add a new book</h3>
					<div class="row">
						<div class="col-md-12">

							<!-- INPUTS -->


							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Input ISBN</h3>
								</div>
								<div class="panel-body">
									<div class="col-md-12">
										<form method="post" onsubmit="return inputCheck2(this)"
											action="ValidateISBN">
											<div class="input-group">
												<input class="form-control" name="ISBN" id="ISBNSearch"
													type="text" placeholder="ISBN" v-model="pfincome"
													onkeyup="value=value.replace(/[^\d]/g,'') "
													ng-pattern="/[^a-zA-Z]/" maxlength=13 /> <span
													class="input-group-btn">
													<button class="btn btn-primary" type="submit">
														Validate and GO!</button>
												</span>
											</div>
										</form>
									</div>
								</div>
							</div>

							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Book Information</h3>
								</div>


								<div class="panel-body">
									<form method="post" onsubmit="return inputCheck(this)"
										action="LibrarianAddBook">
										ISBN: <input type="text" class="form-control" name="ISBN"
											id="ISBN" placeholder="ISBN" value="${sessionScope.ISBN__}" />
										<br /> Book Name: <input type="text" class="form-control"
											name="Book Name" id="BookName" placeholder="Book Name"
											value="${sessionScope.BookName__}" /> <br /> Book
										Description:
										<textarea class="form-control" name="Book Description"
											id="Description" placeholder="Book Description">${sessionScope.BookDes__}</textarea>
										<br /> Publish Time: <input type="text" class="form-control"
											name="Publish Time" id="PublishTime"
											placeholder="Publish Time ( Example:2010-10-20 )"
											value="${sessionScope.PubTime__}" /> <br /> Price: <input
											type="text" class="form-control" name="Price" id="Price"
											placeholder="Price" value="${sessionScope.Price__}" /> <br />
										Publisher Name: <input type="text" class="form-control"
											name="Publisher Name" id="PublisherName"
											placeholder="Publisher Name"
											value="${sessionScope.PubName__}" /> <br /> Author: <input
											type="text" class="form-control" name="Author" id="Author"
											placeholder="Author" value="${sessionScope.Author__}" /> <br />
										Location:
										<tag:location></tag:location>
										<br /> Number: <input type="text" class="form-control"
											name="Number" id="Number" placeholder="Book Number" /> <br />
										Status: <input type="text" class="form-control" name="State"
											readonly="readonly" value="inlib" /> <br />

										<p class="demo-button">
											<button type="submit" class="btn btn-success">Add</button>
										</p>
									</form>
								</div>
							</div>



							<!-- END INPUTS -->
							<!-- INPUT SIZING -->

							<!-- END INPUT SIZING -->
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
	</div>
	<!-- Javascript -->
	<script src="../assets/vendor/jquery/jquery.min.js"></script>
	<script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="../assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="../assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="../assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="../assets/scripts/klorofil-common.js"></script>

	<script>
		var isISBN = /^\d{10}$|^\d{13}$/;
		var isPublishTime = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
		var isBookName = /^[&a-zA-Z0-9\u4e00-\u9fa5()（）：:\s\S ]{1,}$/;
		var isPrice = /^[0-9]+(.[0-9]{2})?$/;
		var isPublisherName = /^[&a-zA-Z0-9\u4e00-\u9fa5 ]{1,}$/;
		var isAuthor = /^[&a-zA-Z0-9\u4e00-\u9fa5 ]{1,}$/;
		var isLocation = /^[\w\s\S]{1,}$/;
		var isNumber = /^\d{1,10}$/;

		function inputCheck(form) {
			if (!isISBN.test(form.ISBN.value)) {
				alert("Invalid ISBN, Please Input Again!");
				form.ISBN.focus();
				return false;
			}

			if (!isPublishTime.test(form.PublishTime.value)) {
				alert("Invalid Publish Time, Please Input Again!");
				form.PublishTime.focus();
				return false;
			}

			if (!isBookName.test(form.BookName.value)) {
				alert("Invalid Book Name, Please Input Again!");
				form.BookName.focus();
				return false;
			}

			if (!isPrice.test(form.Price.value)) {
				alert("Invalid Price, Please Input Again!");
				form.Price.focus();
				return false;
			}

			if (!isPublisherName.test(form.PublisherName.value)) {
				alert("Invalid Publisher Name, Please Input Again!");
				form.PublisherName.focus();
				return false;
			}

			/*if (!isAuthor.test(form.Author.value)) {
				alert("Invalid Author Name, Please Input Again!");
				form.Author.focus();
				return false;
			}*/

			if (!isLocation.test(form.Location.value)) {
				alert("Invalid Location, Please Input Again!");
				form.Location.focus();
				return false;
			}

			if (!isNumber.test(form.Number.value)) {
				alert("Invalid Number, Please Input Again!");
				form.Number.focus();
				return false;
			}

		}

		function inputCheck2(form) {
			if (!isISBN.test(form.ISBNSearch.value)) {
				alert("Invalid ISBN, Please Input Again!");
				form.ISBNSearch.focus();
				return false;
			}
		}
	</script>
</body>

</html>
