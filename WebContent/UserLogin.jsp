<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
</head>
<body>

	<h1>User Login</h1>
	<form method="post" action="LoginHandler">
		User Name<input type="text" name="userName" /><br /> Password <input
			type="password" name="password" /><br /> <input type="radio"
			name="authority" value="reader" checked />I'm Reader <input
			type="radio" name="authority" value="librarian" />I'm Librarian <input
			type="radio" name="authority" value="administrator" />I'm
		Administrator <br /> <input type="submit" value="Login" />
	</form>
</body>
</html>