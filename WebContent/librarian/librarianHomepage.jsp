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
		<td width="200">${sessionScope.librarianEntity.name}</td>
	</tr>
	<tr>
		<td width="160">Librarian ID</td>
		<td width="160">${sessionScope.librarianEntity.id}</td>
	</tr>
	<tr>
		<td width="160">State</td>
		<td width="160">${sessionScope.librarianEntity.state}</td>
	</tr>
</table>


<a href="librarianModifyInfo.jsp"><b>修改密码</b></a><br/>
<a href="searchReaderBeforeEdit.jsp">查询读者</a>

<a href="librarianAddReader.jsp">增加读者</a>
<a href="librarianDeleteReader.jsp">删除读者</a>
<a href="librarianSearchReader.jsp">查看读者详细信息</a>
</body>
</html>