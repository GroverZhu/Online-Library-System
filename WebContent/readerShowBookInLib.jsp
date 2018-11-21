<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Created by Hu Yuxi on 2018-11-18  -->
<!doctype html>
<html lang="en">

<head>
<title>Reader Search Book</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<link rel="stylesheet"
	href="assets/vendor/chartist/css/chartist-custom.css">
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
	<c:if test="${empty sessionScope.ReaderEntity}">
		<jsp:forward page="homepage.jsp" />
	</c:if>
	<!-- WRAPPER -->
	<jsp:include page="readerNavbar.jsp" />
	<!-- END NAVBAR -->
	<!-- LEFT SIDEBAR -->
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
									<!-- INPUTS -->
									<div class="panel">
										<div class="panel-heading"></div>

										<div class="panel-body">
											ISBN:<input type="text" class="form-control"
												name="bookDetailName" readonly="readonly"
												value="${BookDetail.ISBN }" /> <br> Book Name: <input
												type="text" class="form-control" name="bookDetailName"
												readonly="readonly" value="${BookDetail.name}" /> <br>
											Publisher:<input type="text" class="form-control"
												name="bookDetailName" readonly="readonly"
												value="${BookDetail.publisher.name }" /> <br> Authors:<input
												type="text" class="form-control" name="bookDetailName"
												readonly="readonly" value="${BookDetail.authors }" /> <br>
											Price:<input type="text" class="form-control"
												name="bookDetailName" readonly="readonly"
												value="${BookDetail.price }" /> <br> Description:
											<textarea class="form-control" name="bookDetailName"
												readonly="readonly">${BookDetail.description }</textarea>
											<br>
										</div>
									</div>
									<!-- END INPUTS -->
									<br /> <br /> <br />
									<table class="table">
										<thead>
											<tr>
												<th>ID</th>
												<th>ISBN</th>
												<th>Book Name</th>
												<th>Author</th>
												<th>Publisher</th>
												<th>Location</th>
												<th>Status</th>
												<th>Operation</th>
											</tr>

											<!-- 展示书籍信息 -->

											<c:set var="bookInfoList" scope="session"
												value="${bookInfoList}" />
											<c:set var="bookInfoISBN" scope="session"
												value="${bookInfoISBN}" />

											<c:forEach var="book" items="${bookInfoList }">
												<tr>
													<th>${book.id }</th>
													<th>${book.ISBN }</th>
													<th>${book.name }</th>
													<th>${book.authors }</th>
													<th>${book.publisher.name }</th>
													<th>${book.location }</th>
													<th>${book.state }</th>
													<c:if test="${book.state=='inlib'}" var="flag">
														<th><form method="post" action="AddBookToCart">
																<input type="hidden" name="id" value='${book.id}'></input>
																<button type="submit" name="addtocart"
																	class="btn btn-success">Add To Cart</button>
															</form></th>
													</c:if>
													<c:if test="${not flag}">
														<th></th>
													</c:if>
												</tr>
											</c:forEach>
										</thead>

									</table>
									<nav>

										<ul class="pager">
											<li><a
												href="?start=0&bookInfoISBN=${sessionScope.bookInfoISBN }">首
													页</a></li>
											<li><a
												href="?start=${pre}&bookInfoISBN=${sessionScope.bookInfoISBN }">上一页</a></li>
											<li><a
												href="?start=${next}&bookInfoISBN=${sessionScope.bookInfoISBN }">下一页</a></li>
											<li><a
												href="?start=${last}&bookInfoISBN=${sessionScope.bookInfoISBN }">末
													页</a></li>
										</ul>
									</nav>
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
