<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="fullscreen-bg">
<head>
<title>Reader Login</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
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
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center">
									<img src="assets/img/logo-big.png" alt="BiblioSoft Logo">
								</div>
								<p class="lead">Login to your account</p>
							</div>
							<form class="form-auth-small" method="post" action="Reader_index">
								<table align="center">
									<tr>
										<td width=150px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="userID">Reader
													Tele</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="text" name="AdminID" id="userid"
													onchange=" checkuse()" class="form-control"
													value="${admin_id}" placeholder="Reader Tele"
													onkeyup="this.value=this.value.replace(/\D/g,'')"
													onafterpaste="this.value=this.value.replace(/\D/g,'')">
											</div>
										</td>
									</tr>
									<tr>
										<td width=150px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" for="password">Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<input type="password" name="password" id="pw"
													class="form-control" value="${admin_password}"
													onchange="checkpwd()" placeholder="Password" maxlength="32">
											</div>
										</td>

									</tr>
									<tr>
										<td></td>
										<td>
											<div class="form-group clearfix" align="center">
												<label class="fancy-checkbox element-left"> <input
													type="checkbox" name="ck"> <span>Remember me</span>


												</label>
											</div>
										</td>
									</tr>
									<tr>

									</tr>
								</table>
								<button type="submit" class="btn btn-primary btn-lg btn-block">LOGIN</button>
							</form>
							<td>
								<div class="form-group clearfix" align="center">
									<input type="button" class="btn btn-link btn-block"
										value="Forget password"
										onclick="window.location.href = 'ReaderRetrievePassword.jsp'">
								</div>
							</td>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 align="center" class="heading">Welcome , Reader!</h1>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
	<jsp:include page="Footer.jsp" />
	<script>
		function checkuse() {

			var check;
			var username = document.getElementById("userid").value;
			if (username.length != 11) {
				alert("reader tele's length is 11");

				document.getElementById("username").focus();
				check = false;
			}
			return check;
		}
	</script>
</body>

</html>
