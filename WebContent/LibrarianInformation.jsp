<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Librarian Information</title>
</head>
<body>

<h1>Librarian Information</h1>
<!-- 展示librarian的信息 -->
<table border="2">
	<tr>
		<td width="160">Librarian Name</td>
		<td width="200">${librarianEntity.name}</td>
	</tr>
	<tr>
		<td width="160">Librarian ID</td>
		<td width="160">${librarianEntity.id}</td>
	</tr>
	<tr>
		<td width="160">State</td>
		<td width="160">${librarianEntity.state}</td>
	</tr>
</table>


</body>
</html>