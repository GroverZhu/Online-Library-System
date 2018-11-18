<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<jsp:include page="readerNavbar.jsp" />
<body>
	<!-- WRAPPER -->
	<form method="post" action="readerChangeInformation.jsp">
	<jsp:include page="readerLeftSlider.jsp"/>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->

		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">Reader Index</h3>
					<div class="row">
							<!-- BASIC TABLE -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Reader Index
									<input type="SUBMIT" value="Change Information" style="color:green">
                                    </h3>

								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<th>Reader_id</th>
												<th>Reader_name</th>
												<th>sex</th>
												<th>Tele</th>
												<th>MaxBorrow</th>

											</tr>
										</thead>
										<tbody>
											<tr>
											</tr>

										</tbody>
									</table>
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

	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	</form>
</body>

</html>
