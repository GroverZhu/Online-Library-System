<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	function check(){
		var checkbox = document.getElementByID("paid");   //checkbox's id
		if(checkbox.checked == true ){  //selected
			document.getElementByID("submit").style.backgroundColor="red";
			document.getElementByID("submit").removeAttribute="disabled";  //remove disabled
		}
		else
		{
			document.getElementByID("submit").disabled="disabled";
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
										<form method="post" action="ValidateISBN">
											<div class="input-group">
												<input class="form-control" name="ISBN" type="text"
													placeholder="ISBN" v-model="pfincome"
													onkeyup="value=value.replace(/[^\d]/g,'') "
													ng-pattern="/[^a-zA-Z]/" maxlength=13 /> <span
													class="input-group-btn"><button
														class="btn btn-primary" type="submit">Validate
														and GO!</button></span>
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
									<form method="post" action="LibrarianAddBook">
										ISBN: <input type="text" class="form-control" name="ISBN"
											placeholder="ISBN" value="${sessionScope.ISBN__}" /> <br />
										Book Name: <input type="text" class="form-control"
											name="Book Name" placeholder="Book Name"
											value="${sessionScope.BookName__}" /> <br /> Book
										Description: <input type="text" class="form-control"
											name="Book Description" placeholder="Book Description"
											value="${sessionScope.BookDes__}" /> <br /> Publish Time: <input
											type="text" class="form-control" name="Publish Time"
											placeholder="Publish Time ( Example:2010-10-20 )"
											value="${sessionScope.PubTime__}" /> <br /> Price: <input
											type="text" class="form-control" name="Price"
											placeholder="Price" value="${sessionScope.Price__}" /> <br />
										Publisher Name: <input type="text" class="form-control"
											name="Publisher Name" placeholder="Publisher Name"
											value="${sessionScope.PubName__}" /> <br /> Author: <input
											type="text" class="form-control" name="Author"
											placeholder="Author" value="${sessionScope.Author__}" /> <br />
										Location: <input type="text" class="form-control"
											name="Location" placeholder="Location" /> <br /> Number: <input
											type="text" class="form-control" name="Number"
											placeholder="Book Number" /> <br /> Status: <input
											type="text" class="form-control" name="State"
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
		<footer>
			<div class="container-fluid">
				<p class="copyright">
					Copyright &copy; 2018.Company name All rights reserved. <a
						target="_blank" title="BiblioSoft">BiblioSoft</a> - Collect from <a
						title="BiblioSoft" target="_blank">Software</a>
				</p>

			</div>
		</footer>
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

		<script>


	function logout(){
		var result = confirm("Please make sure.Logout?");  
	    if (result == true) {  
	    	window.location.href="DestroyLibSession"; 
	    } else {  
	        
	    }
	}
	
	</script>
</body>

</html>
