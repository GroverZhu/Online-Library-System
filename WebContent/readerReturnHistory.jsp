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
				<h3 class="page-title">Your Return History</h3>
				<div class="row"></div>
			</div>

			<div class="row">

				<div class="panel">
					<div class="panel-heading">
						<h3 class="panel-title">Return History</h3>
					</div>
					<div class="panel-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Book ID</th>
									<th>Book Name</th>
									<th>Librarian ID</th>
									<th>Return Time</th>
								</tr>
								<c:forEach items="${borrowitems}" var="returnhistory"
									varStatus="num">
									<tr>
										<th>${num.count+nums}</th>
										<th>${returnhistory.bookId}</th>
										<th>${returnhistory.bookName}</th>
										<th>${returnhistory.borrowLibrarianId}</th>
										<th>${returnhistory.returnTime}</th>
									</tr>
								</c:forEach>
							</thead>
						</table>
						<nav>
							<ul class="pager">
								<li><a href="?start=0">First Page</a></li>
								<li><a href="?start=${pre}">Preview</a></li>
								<li><a href="?start=${next}">Next</a></li>
								<li><a href="?start=${last}">Last Page</a></li>
							</ul>
						</nav>
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