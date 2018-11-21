<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<nav class="navbar navbar-default navbar-fixed-top">
<div class="brand">
	<a href="adminHomepage.jsp"><img
		src="assets/img/BiblioSoft Logo.png" alt="BiblioSoft Logo"
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
				data-toggle="dropdown"> <img src="assets/img/user.png"
					class="img-circle" alt="Avatar"> <span>${sessionScope.AdministratorEntity.name}</span></a></li>
		</ul>
	</div>
</div>
</nav>
</html>