<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="simpleTag" prefix="simple"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.AdministratorEntity}">
	<jsp:forward page="../homepage.jsp" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modify Librarian</title>
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
							<h1 align="center" class="page-title">Modify Librarian
								Information</h1>
						</div>
						<form onsubmit="return inputCheck(this)" method="post"
							action="AdminModify">
							<div class="panel-body">
								<table align="center">
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="librarianId">Librarian
													ID</label>
											</div>
										</td>

										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="text" id="LibrarianID" name="librarianId"
													class="form-control" readonly="readonly" value="${id}">
											</div>
										</td>
									</tr>
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="librarianname">Librarian
													New Name</label>
											</div>
										</td>

										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="text" id="LibrarianName" name="librarianName"
													class="form-control" value="${name}"
													placeholder="Please enter the Librarian name">
											</div>
										</td>
									</tr>

									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="Password"> New
													Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="password" id="Password" name="password"
													class="form-control"
													placeholder="please enter password or be empty">
											</div>
										</td>
									</tr>

									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="librarianname">Librarian
													Account State</label>
											</div>
										</td>

										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<simple:state />
											</div>
										</td>
									</tr>
								</table>
								<div>
									<div style="width: 300px; margin: auto">
										<button type="submit" class="btn btn-primary btn-block">Submit</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- END MAIN CONTENT -->
		<!-- END MAIN -->
		<div class="clearfix"></div>

		<!-- END WRAPPER -->
		<jsp:include page="Footer.jsp" />
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
			// librarian密码不可超过50个字符
			var isPassword = /^\w{1,50}$|^$/;

			function inputCheck(form) {
				if (!isName.test(form.LibrarianName.value)) {
					alert("Librarian name must use the English or Chinese character with less than 50 letters and cannot be empty, please enter again!");
					form.LibrarianName.focus();
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