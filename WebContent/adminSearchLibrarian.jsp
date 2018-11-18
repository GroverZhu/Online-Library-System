<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search Librarian</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
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

		<!-- LEFT SIDEBAR -->
		<jsp:include page="adminLeftSlider.jsp" />

		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">Search Librarian</h3>
						</div>
						<div class="panel-body">
							<form class="navbar-form navbar-left" role="search"
								align="center" method="post" action="SearchLibrarian">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp; <select id="input" name="style">

									<option value="librarianId" selected="selected">Librarian
										ID</option>
									<option value="librarianName">Librarian Name</option>
								</select>
								<div class="form-group" align="center">
									<input type="text" name="name" placeholder="begin to search..."
										class="form-control" style="width: 400px;" />
								</div>
								<button type="submit" name="submit" class="btn btn-success">Search</button>

							</form>

							<br /> <br /> <br />
							<table class="table">
								<thead>
									<tr>
										<th>Librarian ID</th>
										<th>Librarian Name</th>
										<th>Librarian State</th>
										<th>Operation</th>
									</tr>

									<!-- 展示书籍信息 -->


									<c:forEach var="book" items="">
										<tr>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th>${book.location }</th>
											<th>${book.state }</th>
										</tr>
									</c:forEach>
								</thead>

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END MAIN CONTENT -->
		<!-- END MAIN -->
		<div class="clearfix"></div>

		<!-- END WRAPPER -->
		<footer>
		<div class="container-fluid">
			<p class="copyright">Copyright &copy; 2018.Company name All
				rights reserved.BiblioSoft - Collect from Software</p>

		</div>
		</footer>
		<!-- Javascript -->
		<script src="assets/vendor/jquery/jquery.min.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<script
			src="assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
		<script src="assets/vendor/chartist/js/chartist.min.js"></script>
		<script src="assets/scripts/klorofil-common.js"></script>
		<!-- 判断输入的正确性 -->
		<script type="text/javascript">
			// librarian的姓名不可超过45个字符
			var isName = /^[a-zA-Z0-9\u4e00-\u9fa5 ]{1,50}$/;
			// 读者的密码不可超过45个字符
			var isPassword = /^\w{1,50}$/;

			function inputCheck(form) {
				if (!isName.test(form.Name.value)) {
					alert("Librarian name must use the English or Chinese character with less than 50 letters and cannot be empty, please enter again!");
					form.Name.focus();
					return false;
				}

				if (!isPassword.test(form.Password.value)) {
					alert("Password must less than 50 letters and cannot be empty, please enter again!");
					form.Password.focus();
					return false;
				}

			}
		</script>
</body>

</html>