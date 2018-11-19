<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Librarian Search Reader</title>
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
					<h3 class="page-title">Search Reader</h3>
					<div class="row">
						<!-- BASIC TABLE -->
						<div class="panel">
							<div class="panel-heading">
								<h3 class="panel-title">Librarian Search Reader</h3>
							</div>
							<div class="panel-body">
								<form method="post" action="LibrarianSearchReader">
									<table align="center">
										<tr>
											<td width=250px height=64px>
												<div class="form-group has-success has-feedback">
													<label class="control-label" for="readerName">Reader
														ID</label>
												</div>
											</td>
											<td width=350px height=64px>
												<div class="form-group has-success has-feedback">
													<input type="text" id="readerID" name="id"
														class="form-control"
														placeholder="Please enter the Reader ID">
												</div>
											</td>
										</tr>
										<tr>
											<td width=250px height=64px>
												<div class="form-group has-success has-feedback">
													<label class="control-label" for="readerName">
														Reader Name</label>
												</div>
											</td>
											<td width=350px height=64px>
												<div class="form-group has-success has-feedback">
													<input type="text" id="LibrarianName" name="name"
														class="form-control"
														placeholder="Please enter the Reader Name">
												</div>
											</td>
										</tr>
										<tr>
											<td width=150px height=64px>
												<div class="form-group has-success has-feedback">
													<label class="control-label" for="state">State</label>
												</div>
											</td>
											<td width=350px height=64px><input type="radio"
												name="state" value="unlock" />未锁定 <input type="radio"
												name="state" value="blockade" /> 锁定 <input type="radio"
												name="state" value="unknown" checked />未指定</td>
										</tr>
									</table>
									<div style="width: 300px; margin: auto">
										<button type="submit" class="btn btn-primary btn-block">Search</button>
									</div>
								</form>
								<c:if test="${not empty readerEntity }">
									<table align="center">
										<tr>
											<td>ID</td>
											<td>用户名</td>
											<td>email</td>
											<td>state</td>
											<td>借阅车</td>
											<td>借阅历史</td>
											<td>当前借阅</td>
										</tr>
										<tr>
											<td>${readerEntity.id }</td>
											<td>${readerEntity.name }</td>
											<td>${readerEntity.email }</td>
											<td>${readerEntity.state }</td>
											<td><a
												href="ShowReaderInfo?reader_id=${readerEntity.id }&param=cart">查看</a></td>
											<td><a
												href="ShowReaderInfo?reader_id=${readerEntity.id }&param=history">查看</a></td>
											<td><a
												href="ShowReaderInfo?reader_id=${readerEntity.id }&param=current">查看</a></td>
										</tr>
									</table>
								</c:if>
								<c:if test="${not empty readerList }">
									<table border="2">
										<tr>
											<td>序号</td>
											<td>ID</td>
											<td>用户名</td>
											<td>email</td>
											<td>state</td>
											<td>借阅车</td>
											<td>借阅历史</td>
											<td>当前借阅</td>
										</tr>
										<c:forEach var="reader" items="${readerList }" varStatus="i">
											<tr>
												<td>${i.count }</td>
												<td>${reader.id }</td>
												<td>${reader.name }</td>
												<td>${reader.email }</td>
												<td>${reader.state }</td>
												<td><a
													href="ShowReaderInfo?reader_id=${reader.id }&param=cart">查看</a></td>
												<td><a
													href="ShowReaderInfo?reader_id=${reader.id }&param=history">查看</a></td>
												<td><a
													href="ShowReaderInfo?reader_id=${reader.id }&param=current">查看</a></td>
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
