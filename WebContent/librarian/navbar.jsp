
<!-- NAVBAR -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="brand">
		<a href="librarianHomepage.jsp"><img
			src="../assets/img/BiblioSoft Logo.png" alt="BiblioSoft Logo"
			class="img-responsive logo"></a>
	</div>
	<div class="container-fluid">
		<div class="navbar-btn">
			<button type="button" class="btn-toggle-fullwidth">
				<i class="lnr lnr-arrow-left-circle"></i>
			</button>
		</div>
		<form class="navbar-form navbar-left"></form>
		<div id="navbar-menu">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <img src="../assets/img/user.png"
						class="img-circle" alt="Avatar"> <span>${sessionScope.librarianEntity.name}</span></a></li>
			</ul>
		</div>
	</div>
</nav>
<!-- END NAVBAR -->