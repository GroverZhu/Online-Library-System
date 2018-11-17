<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Modify Password</title>
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
	<!-- WRAPPER -->
	<div id="wrapper">
		<%@ include file="navbar.jsp" %>
		<%@ include file="sidebar.jsp" %>
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="panel">
						<div class="panel-heading">
							<h1 align="center" class="page-title">Modify Password</h1>
						</div>
						<form method="post" action="ChangePassword">
							<div class="panel-body">
								<table align="center">
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="oldPassword">Old
													Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="password" name="old" class="form-control"
													placeholder="Old Password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
											</div>
										</td>
									</tr>
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="newPassword">New
													Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="password" name="new"
													class="form-control" placeholder="New Password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
											</div>
										</td>
									</tr>
									<tr>
										<td width=250px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="ConfirmPassword">Confirm
													New Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="password" name="confirm"
													class="form-control" placeholder="Confirm New Password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
											</div>
										</td>
									</tr>
								</table>
								<div>
									<div style="width: 300px; margin: auto">
										<button type="submit" class="btn btn-primary btn-block">Modify</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
	<!-- END MAIN -->
	<div class="clearfix"></div>
	<footer>
	<div class="container-fluid">
		<p class="copyright">Copyright &copy; 2018.Company name All rights
			reserved.BiblioSoft - Collect from Software</p>

	</div>
	</footer>
	<!-- END WRAPPER -->
</body>

</html>
