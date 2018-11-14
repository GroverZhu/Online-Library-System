<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="2">	
	<tr>
		<td>Reader ID:</td>
		<td>${readerEntity.id }</td>
	</tr>
	<tr>
		<td>Reader Name:</td>
		<td>${readerEntity.name }</td>
	</tr>
	<tr>
		<td>Reader password:</td>
		<td>${readerEntity.password }</td>
	</tr>
	<tr>
		<td>Email:</td>
		<td>${readerEntity.email }</td>
		
	</tr>
	<tr>
		<td>State:</td>
		<td>${readerEntity.state }</td>
	</tr>
</table>
<a href="ChangeReaderInformation?reader_id=${readerEntity.id}">修改Reader信息</a>

</body>
</html>