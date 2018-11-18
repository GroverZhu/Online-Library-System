<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Created by Hu Yuxi on 2018-11-14  -->
<!doctype html>
<html lang="en">
<jsp:include page="readerNavbar.jsp"/>
<body>
	<!-- WRAPPER -->
	<jsp:include page="readerLeftSlider.jsp"/>
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
											&nbsp;&nbsp;&nbsp; <select id="input" name="style">
												<option selected value="Please select info type">Please
													select</option>
												<option value="bookName" selected="selected">Book
													name</option>
												<option value="author">author</option>
												<option value="publisher">publisher</option>
											</select>
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
													<th>Book_name</th>
													<th>Author</th>
													<th>Publisher</th>
													<th>Location</th>
													<th>Status</th>

												</tr>

												<!-- 展示书籍信息 -->

                                              <c:set var="bookList" scope="session" value="${bookList}" />
												<c:forEach var="book" items="${bookList }">
													<tr>
													<th>${book.ISBN }</th>
													<th>${book.name }</th>
													<th>${book.authors }</th>
													<th>${book.publisher.name }</th>
													<th>${book.location }</th>
													<th>${book.state }</th>													
													</tr>
												</c:forEach>
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
		<!-- Javascript -->
		<script src="assets/vendor/jquery/jquery.min.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/vendor/chartist/js/chartist.min.js"></script>
		<script src="assets/scripts/klorofil-common.js"></script>
</body>

</html>
