<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<jsp:include page="readerNavbar.jsp" />
<body>
	<c:if test="${empty sessionScope.ReaderEntity}">
		<jsp:forward page="homepage.jsp" />
	</c:if>
	<!-- WRAPPER -->
	<jsp:include page="readerLeftSlider.jsp" />
	<!-- END LEFT SIDEBAR -->
	<!-- MAIN -->
	<div class="main">
		<!-- MAIN CONTENT -->
		<div class="main-content">
			<div class="container-fluid">
				<h3 class="page-title">Borrow Cart</h3>
				<div class="row"></div>
			</div>

			<div class="row">

				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title">Your Borrow Cart</h3>
					</div>
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Reader ID</th>
									<th>Book ID</th>
									<th>Book Name</th>
									<th>Submit State</th>
								</tr>
								<c:forEach items="${borrowCart}" var="borrowcart"
									varStatus="num">
									<tr>
										<th>${num.count}</th>
										<th>${borrowcart.readerId}</th>
										<th>${borrowcart.bookId}</th>
										<th>${borrowcart.bookName}</th>
										<th>
											<button class="btn btn-primary"
												onclick="window.location.href='ReaderSubmitBorrowCart?bookid=${borrowcart.bookId}'">Submit</button>

										</th>
									</tr>
								</c:forEach>
							</thead>
						</table>
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
	<jsp:include page="Footer.jsp" />
	<!-- Javascript -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>

</html>