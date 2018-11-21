<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
</head>
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
<link rel="stylesheet"
	href="assets/vendor/chartist/css/chartist-custom.css">

<!-- LEFT SIDEBAR -->
<div id="sidebar-nav" class="sidebar">
	<div class="sidebar-scroll">
		<nav>
		<ul class="nav">
			<li><a href="librarianHomepage.jsp" class=""> <i
					class="lnr lnr-home"></i> <span>Librarian Homepage</span>
			</a></li>
			<li><a href="librarianModifyInfo.jsp" class=""> <i
					class="lnr lnr-home"></i> <span>Change Password</span>
			</a></li>

			<li><a href="#subPages1" data-toggle="collapse"
				class="collapsed"><i class="lnr lnr-cog"></i> <span>Manage
						Books</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
				<div id="subPages1" class="collapse">
					<ul class="nav">
						<li><a href="librarianAddBook.jsp" class="">Add Book</a></li>
						<li><a href="librarianDeleteBook.jsp" class="">Delete
								Book</a></li>
						<li><a href="librarianSearchBook.jsp" class="">Search
								Book</a></li>
						<li><a href="librarianReturnBook.jsp" class="">Return
								Book</a></li>
					</ul>
				</div></li>

			<li><a href="#subPages2" data-toggle="collapse"
				class="collapsed"><i class="lnr lnr-cog"></i> <span>Manage
						Readers</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
				<div id="subPages2" class="collapse">
					<ul class="nav">
						<li><a href="librarianAddReader.jsp" class="active">Add
								Reader</a></li>
						<li><a href="librarianDeleteReader.jsp" class="">Delete
								Reader</a></li>
						<li><a href="searchReaderBeforeEdit.jsp" class="">Edit
								Reader</a></li>
						<li><a href="librarianSearchReader.jsp" class="">Search
								Reader</a></li>
						<li><a href="librarianLendBook.jsp" class="">Lend Book To
								Reader</a></li>
						<li><a href="ShowCart?start=0" class="">Show Borrow Cart</a></li>
					</ul>
				</div></li>

			<li><a href="#" onclick="logout()" class=""> <i
					class="lnr lnr-linearicons"></i> <span>Logout</span>
			</a></li>
		</ul>
		</nav>
	</div>
</div>
<!-- END LEFT SIDEBAR -->
<script>
	function logout() {
		var result = confirm("Please make sure to Logout?");
		if (result == true) {
			window.location.href = "../DestorySession";
		} else {

		}
	}
</script>