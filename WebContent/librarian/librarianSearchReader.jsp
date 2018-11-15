<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Librarian Search Reader</title>
</head>
<body>
<h1>Librarian Search Reader</h1>
<form method="post" action="LibrarianSearchReader">
<table>
	<tr>
		<td>Reader ID:</td>
		<td><input type="text" name="id" /></td>
		<td>
	</tr>
	<tr>
		<td>Reader Name:</td>
		<td><input type="text" name="name" /></td>
	</tr>
	<tr>
		<td>Reader State:</td>
		<td>
			<input type="radio" name="state" value="unlock" />未锁定
			<input type="radio" name="state" value="blockade" /> 锁定
			<input type="radio" name="state" value="unknown" checked/>未指定
		</td> 
	</tr>
	<tr>
		<td><input type="submit" value="查询"></td>
	</tr>
</table>

</form>
<c:if test="${not empty readerEntity }">
	<table border="2">
		<tr>
			<td>ID</td>
			<td>用户名</td>
			<td>email</td>
			<td>state</td>
			<td>借阅车</td>
			<td>借阅历史</td>
			<td>当前借阅</td>
		</tr>
		<tr>
			<td>${readerEntity.id }</td>
			<td>${readerEntity.name }</td>
			<td>${readerEntity.email }</td>
			<td>${readerEntity.state }</td>
			<td><a href="ShowReaderInfo?reader_id=${readerEntity.id }&param=cart">查看</a></td>
			<td><a href="ShowReaderInfo?reader_id=${readerEntity.id }&param=history">查看</a></td>
			<td><a href="ShowReaderInfo?reader_id=${readerEntity.id }&param=current">查看</a></td>
		</tr>
	</table>
</c:if>
<c:if test="${not empty readerList }">
	<table border="2">
		<tr>
			<td>序号</td>
			<td>ID</td>
			<td>用户名</td>
			<td>email</td>
			<td>state</td>
			<td>借阅车</td>
			<td>借阅历史</td>
			<td>当前借阅</td>
		</tr>
		<c:forEach var="reader" items="${readerList }" varStatus="i">
		<tr>
			<td>${i.count }</td>
			<td>${reader.id }</td>
			<td>${reader.name }</td>
			<td>${reader.email }</td>
			<td>${reader.state }</td>
			<td><a href="ShowReaderInfo?reader_id=${reader.id }&param=cart">查看</a></td>
			<td><a href="ShowReaderInfo?reader_id=${reader.id }&param=history">查看</a></td>
			<td><a href="ShowReaderInfo?reader_id=${reader.id }&param=current">查看</a></td>
		</tr>
		</c:forEach>
	</table>
	
</c:if>

</body>
</html>