<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<jsp:include page="readerNavbar.jsp" />

<body>
	<!-- WRAPPER -->
	<form method="post" action="readerIndex.jsp">
		<jsp:include page="readerLeftSlider.jsp" />
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<h3 class="page-title">Modify Information</h3>
					<div class="row">
						<!-- BASIC TABLE -->
						<div class="panel">
							<div class="panel-body">
								<table class="table">
									<thead>
										<tr>
											<th>Reader_id</th>
											<th>Reader_name</th>
											<th>Password</th>
											<th>email</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<!-- Reader change information -->

											<td>${ReaderEntity.id }</td>
											<form method="post" action="ReaderModifyInformation">
												<td><input type="text" name="newName" maxlength="15"
													style="height: 23px; width: 100px"></td>
												<td><input type="password" name="newPassword"
													maxlength="15" style="height: 23px; width: 100px"></td>
												<td><input type="text" name="newEmail" maxlength="15"
													style="height: 23px; width: 100px"></td>
												<td><input type="SUBMIT" name="cis2" value="Modify"></td>
											</form>
										</tr>

									</tbody>
								</table>
								<div>
									<div style="width: 300px; margin: auto">
										<button type="submit" name="cis2"
											class="btn btn-primary btn-block">Modify</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END MAIN CONTENT -->
	<!-- END MAIN -->
	<div class="clearfix"></div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>
