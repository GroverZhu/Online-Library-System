<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
</head>
<body>

<h1>Change Password</h1>


<form method="post" action="ChangePassword">

	<table>
		<tr>
		 	<td>旧密码：</td>
		 	<td><input type="text" name="old"></td>
		</tr>
		<tr>
			<td>新密码：</td>
			<td><input type="text" name="new"></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input type="text" name="confirm"></td>
		</tr>
	</table>
	<input type="submit" value="修改密码">
</form>

</body>
</html>