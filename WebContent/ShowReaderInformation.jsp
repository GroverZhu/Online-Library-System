<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="">
<table>	
	<tr>
		<td>Reader ID:</td>
		<td>${readerEntity.id }</td>
	</tr>
	<tr>
		<td>Reader Name:</td>
		<td>${readerEntity.name }</td>
		<td><input type="text" name="newName"></td>
	</tr>
	<tr>
		<td>Reader password:</td>
		<td>${readerEntity.password }</td>
		<td><input type="text" name="newPassword"></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td>${readerEntity.email }</td>
		<td><input type="text" name="newEmail"></td>
		
	</tr>
	<tr>
		<td>State:</td>
		<td>${readerEntity.state }</td>
	</tr>
</table>
</form>
</body>
</html>