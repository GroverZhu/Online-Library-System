<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Reader Homepage</title>
<meta charset="utf-8">
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
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="brand">
			<img src="assets/img/BiblioSoft Logo.png"
				alt="BiblioSoft Logo" class="img-responsive logo">
		</div>
		<div class="container-fluid">
			<div id="navbar-menu">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a class="update-pro" href="UserLogin.jsp" title="Login" ><i class="fa fa-rocket"></i> <span>Login</span></a>
					</li>
				</ul>
			</div>
		</div>
		</nav>
		<!-- MAIN -->
		<div>
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
				<br/><br/><br/><br/>
					<div class="panel">
						<div class="panel-heading">
							<br />
							<div class="logo text-center">
									<img src="assets/img/logo-big.png" alt="BiblioSoft Logo">
							</div>
						</div>
						<div class="panel-body">
							<div class="overlay">
							<div class="content text">
								<h1 align="center" class="heading">
									<br />Newest Announcement
								</h1>
								<div class="panel-body">
									<table align="center">
										<tr>
											<td width="120px">
												<div align="center">
													<label style="font-size: 20px" class="control-label"
														for="Announcement">Search</label>
												</div>
											</td>
											<td width="550px">
												<!-- Search Announcement Here And List On This Page -->

												<form method="post" action="">
													<div class="input-group">
														<input class="form-control" type="text"
															name="Announcement" id="Announcement"
															placeholder="Search The Announcement"> <span
															class="input-group-btn">
															<button type="submit" class="btn btn-primary"
																type="button">Search</button>
														</span>
													</div>
												</form>
											</td>
										</tr>
									</table>

									<br/><br/>
									<table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th width="750px">Post Title</th>
                                        <th width="150px">Writer</th>
                                        <th width="350px">Post Time</th>
                                    </tr>
                                    </thead>
                                    
									<!-- Post信息 -->

									</table>
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
	<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->

	<script>
		function checkuse() {
			//å¨æ¯ä¸ªå½æ°ä¸­å®ä¹checkåéæ¯ä¸ºäºå¨è¡¨åæäº¤åï¼è½å¤éä¸ªéªè¯æ¯ä¸ªå½æ°æ¯å¦éè¿ï¼å¾å¥½å¾å¥½ãï¼ä»¥ä¸åçï¼
			var check;
			var username = document.getElementById("userid").value;
			if (username.length != 11) {
				alert("reader tele's length is 11");
				//æ­¤å¤çå¦ï¼æ¢ç¶ä½ å¨æ­¤å¤è¾å¥éè¯¯ï¼é£ä¹æçè¯´å½ç¶è¦å¨æ­¤å¤ç»§ç»­è¾å¥äºãï¼å¨æ­¤å¤ç»§ç»­è·åç¦ç¹ï¼ï¼
				document.getElementById("username").focus();
				check = false;
			}
			return check;
		}
	</script>
</body>

</html>
