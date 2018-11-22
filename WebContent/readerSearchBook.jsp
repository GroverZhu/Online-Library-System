<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="BookLocationTag"%>
<!--Created by Hu Yuxi on 2018-11-14  -->
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
	<div class="tab-content">
		<div class="tab-pane active" id="panel-1">
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content">
					<div class="container-fluid">
						<div class="row">
							<!-- BASIC TABLE -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Search</h3>
								</div>
								<div class="panel-body">
									<form class="navbar-form navbar-left" role="search"
										align="center" method="post" action="SearchBookForReader">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										<tag:searchBy></tag:searchBy>
										<div class="form-group" align="center">
											<input type="text" name="name"
												placeholder="begin to search..." class="form-control"
												style="width: 400px;" />
										</div>
										<button type="submit" name="submit" class="btn btn-success">Search</button>

									</form>

									<br /> <br /> <br />
									<table class="table">
										<thead>
											<tr>
												<th>ISBN</th>
												<th>Book Name</th>
												<th>Author</th>
												<th>Publisher</th>
												<th>Price</th>

											</tr>

											<!-- 展示书籍信息 -->
											<c:if test="${not empty sessionScope.bookList }">
											<c:forEach var="book" items="${sessionScope.bookList }">
												<tr>
													<th>${book.ISBN }</th>
													<th>${book.name }</th>
													<th>${book.authors }</th>
													<th>${book.publisher.name }</th>
													<th>${book.price }</th>
													<th><form align="center" method="post"
															action="ShowBookInLib">
															<input type="hidden" name="isbn" value='${book.ISBN}'></input>
															<button type="submit" name="bookInfo"
																class="btn btn-success">More Information</button>
														</form></th>
												</tr>
											</c:forEach>
											</c:if>
											<c:if test="${not empty sessionScope.bookEntity }">
											<tr>
													<th>${sessionScope.bookEntity.ISBN }</th>
													<th>${sessionScope.bookEntity.name }</th>
													<th>${sessionScope.bookEntity.authors }</th>
													<th>${sessionScope.bookEntity.publisher.name }</th>
													<th>${sessionScope.bookEntity.price }</th>
													<th><form align="center" method="post"
															action="ShowBookInLib">
															<input type="hidden" name="isbn" value='${sessionScope.bookEntity.ISBN}'></input>
															<button type="submit" name="bookInfo"
																class="btn btn-success">More Information</button>
														</form></th>
												</tr>
											</c:if>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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
	<script src="assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>

