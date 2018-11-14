<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Search Reader Before Edit</title>
</head>
<body>

<form method="post" action="SearchReaderBeforeEdit">
<h1>读者管理 : </h1> <br/>
Reader 查询<input type="text" name="account" value="请输入Reader账号" />
<input type="submit" value="查找" />
</form>
<!-- 如果readerEntity属性不为空，则展示Reader信息 -->
<c:if test="${not empty readerEntity }">
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
<a href="LibrarianModifyReader?reader_id=${readerEntity.id}">修改Reader信息</a>
</c:if>
<a href="librarianHomepage.jsp">返回主页</a>
</body>
</html>