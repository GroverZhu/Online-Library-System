		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
				<ul class="nav">
						<li><a href="librarianHomepage.jsp" class=""><i class="lnr lnr-home"></i> <span>Librarian Home</span></a></li>
						<li><a href="librarianModifyInfo.jsp" class=""><i class="lnr lnr-home"></i> <span>Change Password</span></a></li>
						<li>
							<a href="#subPages1" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>Books Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages1" class="collapse ">
								<ul class="nav">
									<li><a href="librarianAddBook.jsp" class="">Add Book</a></li>
								    <li><a href="librarianDeleteBook.jsp" class="">Delete Book</a></li>
								    <li><a href="librarianSearchBook.jsp" class="">Search Book</a></li>
								    <li><a href="librarianReturnBook.jsp" class="">Return Book</a></li>
									<li><a href="searchEditBook.jsp" class="">Search and Edit Book</a></li>
								</ul>
							</div>
						</li>						
						
						<li>
							<a href="#subPages2" data-toggle="collapse" class="active"><i class="lnr lnr-cog"></i> <span>Readers Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages2" class="collapse in">
								<ul class="nav">
									<li><a href="librarianAddReader.jsp" class="active">Add Reader</a></li>
									<li><a href="librarianDeleteReader.jsp" class="">Delete Reader</a></li>
									<li><a href="searchReaderBeforeEdit.jsp" class="">Edit Reader</a></li>
									<li><a href="librarianSearchReader.jsp" class="">Search Reader</a></li>
									<li><a href="librarianLendBook.jsp" class="">Lend Book To Reader</a></li>
									<li><a href="ShowCart?start=0" class="">Show Borrow Cart</a></li>
									<li><a href="borrowAndReturnRecord.jsp" class="">Borrow and Return Record</a></li>
									<li><a href="borrowAndReturn.jsp" class="">Book Borrow and Return</a></li>
									
								</ul>
							</div>
						</li>						
						
					<li><a href="#" onclick="logout()" class=""><i
							class="lnr lnr-linearicons"></i> <span>Logout</span></a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
	<!-- Javascript -->
	<script src="../assets/vendor/jquery/jquery.min.js"></script>
	<script src="../assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="../assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="../assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="../assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="../assets/scripts/klorofil-common.js"></script>
	<script>
	
		function logout() {
			var result = confirm("Please make sure.Logout?");
			if (result == true) {
				window.location.href="DestroySession"; 
			} else {
				
			}
		}
	</script>
