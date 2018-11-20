<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<title>Add Reader</title>
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
					<h3 class="page-title">Add a New Reader</h3>
					<div class="row">
						<div class="col-md-12">

							<!-- INPUTS -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">
										<b>Please Input Here to Add a new Reader</b>
									</h3>
								</div>

								<form method="post" action="LibrarianAddReader">
									<div class="panel-body">
										<input type="text" class="form-control"
											placeholder="Reader Name 由字符、空格、中文组成" name="readerName">
										<br> <input type="text" class="form-control"
											placeholder="Password" name="password"> <br> <label
											class="fancy-radio"> <input name="state"
											value="unlock" type="radio" checked> <span><i></i>Unlock</span>
										</label> <label class="fancy-radio"> <input name="state"
											value="blockade" type="radio"> <span><i></i>Blockade</span>
										</label>
										<p class="demo-button">
											<button id="submit" type="submit" class="btn btn-success ">Add</button>
										</p>
									</div>
								</form>
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
