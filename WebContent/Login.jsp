<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="fullscreen-bg">

<head>
<title>Librarian Login</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
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
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center">
									<img src="assets/img/logo-big.png" alt="BiblioSoft Logo">
								</div>
								<p class="lead">Login to your account</p>
							</div>
							<form class="form-auth-small" method="post" action="LoginHandler">
								<table align="center">
									<tr>
										<td width=150px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" name="userID">User ID</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<c:if test="${not empty userId }">
												<input type="text" name="userID" class="form-control"
													placeholder="User ID"
													onkeyup="this.value=this.value.replace(/\D/g,'')"
													onafterpaste="this.value=this.value.replace(/\D/g,'')"
													value="${userId}">
													</c:if>
													<c:if test="${empty userId }">
														<input type="text" name="userID" class="form-control"
													placeholder="User ID"
													onkeyup="this.value=this.value.replace(/\D/g,'')"
													onafterpaste="this.value=this.value.replace(/\D/g,'')">
													</c:if>
											</div>

										</td>
										<td>
										<div class="form-group">
											<select name="account" size="1" id="account" class="selectpicker" onchange="change(this.value)">
											<option value="please">please select </option> 
											<c:forEach var="item" items="${idList }" >
											<option value="${item }">${item }</option>
											</c:forEach>
											</select>
											
									</div>
										</td>
									</tr>
									<tr>
										<td width=150px height=64px>
											<div class="form-group has-success has-feedback">
												<label class="control-label" name="password">Password</label>
											</div>
										</td>
										<td width=350px height=64px>
											<div class="form-group has-success has-feedback">
												<c:if test="${not empty password }">
												<input type="password" name="password" id="pw"
													class="form-control" placeholder="Password" maxlength="32"
													value="${password }">
													</c:if>
													<c:if test="${empty password }">
													<input type="password" name="password" id="pw"
													class="form-control" placeholder="Password" maxlength="32">
													</c:if>
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
										<td>
											<div class="form-group clearfix" align="left">
												<c:if test="${empty authority }">
												<input type="radio" name="authority" value="reader" checked />I'm Reader <br /> 
												<input type="radio" name="authority" value="librarian" />I'm Librarian <br /> 
												<input type="radio" name="authority" value="administrator" />I'm Administrator <br />
												</c:if>
												<c:if test="${not empty authority }">
												<c:if test="${authority eq 'reader' }">
												<input type="radio" name="authority" value="reader" checked />I'm Reader <br /> 
												<input type="radio" name="authority" value="librarian" />I'm Librarian <br /> 
												<input type="radio" name="authority" value="administrator" />I'm Administrator <br />
												</c:if>
												<c:if test="${authority eq 'librarian' }">
												<input type="radio" name="authority" value="reader" />I'm Reader <br /> 
												<input type="radio" name="authority" value="librarian" checked/>I'm Librarian <br /> 
												<input type="radio" name="authority" value="administrator" />I'm Administrator <br />
												</c:if>
												<c:if test="${authority eq 'administrator' }">
												<input type="radio" name="authority" value="reader" />I'm Reader <br /> 
												<input type="radio" name="authority" value="librarian" />I'm Librarian <br /> 
												<input type="radio" name="authority" value="administrator" checked/>I'm Administrator <br />
												</c:if>
												</c:if>
												
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
										
										<td>
											<div class="form-group clearfix" align="left">
												<input type="checkbox" name="isRemember" id="isRemember"/>Remember Password
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
										
									</tr>
				
								</table>
								<button type="submit" class="btn btn-primary btn-lg btn-block">LOGIN</button>
							</form>
							<td></td>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 align="center" class="heading">Welcome To The Online
								Librarian System!</h1>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
	<jsp:include page="Footer.jsp" />
	<script>
    function checkuse() {
        //在每个函数中定义check变量是为了在表单提交后，能够逐个验证每个函数是否通过
        var check;
        var username = document.getElementById("userid").value;
        if (username.length != 6) {
            alert("admin id's length is 6");
            document.getElementById("username").focus();
            check = false;
        }
        return check;
    }
    function change(val){
        if(val!="please"){
    		window.location.href="AutoFilling?param="+val;
        }
    }
</script>

</body>

</html>
