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
<title>Search Reader Before Edit</title>
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
	<!-- WRAPPER -->
	<div id="wrapper">
		<%@ include file="navbar.jsp"%>
		<%@ include file="sidebar.jsp"%>
		<!-- MAIN -->

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">Librarian Manage Reader</h3>
					<div class="row">
						<!-- BASIC TABLE -->
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Manage Reader</h3>
							</div>
							<div class="panel-body">
								<table align="center">
									<tr>
										<td width="120px">
											<div align="center">
												<label style="font-size: 20px" class="control-label"
													for="LibrarianID">Reader ID</label>
											</div>
										</td>
										<td width="550px">
											<form method="post" onsubmit="return inputCheck(this)"
												action="SearchReaderBeforeEdit">
												<div class="input-group">
													<input class="form-control" type="text" name="account"
														id="account" placeholder="Please Input ReaderID">
													<span class="input-group-btn">
														<button class="btn btn-primary" type="submit">Search</button>
													</span>
												</div>
											</form>
										</td>
									</tr>
								</table>
								<br />

								<!-- 如果readerEntity属性不为空，则展示Reader信息 -->
								<c:if test="${not empty readerEntity }">
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
											<td>Email:</td>
											<td>${readerEntity.email }</td>

										</tr>
										<tr>
											<td>State:</td>
											<td>${readerEntity.state }</td>
										</tr>
									</table>
									<a href="LibrarianModifyReader?reader_id=${readerEntity.id}">Modify
										Reader Information</a>
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
	<script>

	
	var isReaderId = /^\d{4,10}$/;
	
	function inputCheck(form) {
		if (!isReaderId.test(form.account.value)) {
			alert("Invalid Reader ID(Should Be 4-10 Numbers!), Please Input Again!");
			form.account.focus();
			return false;
		}
	}
	</script>
</body>
</html>
