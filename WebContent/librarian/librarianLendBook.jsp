<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Librarian Lend Book</title>
</head>
<body>
<form method="post" action="LibrarianLendBook">
	Reader ID:
	<c:if test="${not empty readerEntity}">
		<input type="text" name="readerId" value="${readerEntity.id }"/>
	</c:if>
	<c:if test="${empty readerEntity}">
		<input type="text" name="readerId" />
	</c:if>
	<input type="submit" value="Show Reader Information" />
</form>
<c:if test="${not empty readerEntity}">
	<table>
		<tr>
			<td>Reader ID:</td>
			<td>${readerEntity.id }</td>
		</tr>
		<tr>
			<td>Reader Name:</td>
			<td>${readerEntity.name }</td>
		</tr>
		<tr>
			<td>Reader Email:</td>
			<td>${readerEntity.email }</td>
		</tr>
		<tr>
			<td>Reader State:</td>
			<td>${readerEntity.state }</td>
		</tr>
		<tr>
			<td>Current Borrow Count:</td>
			<td>${total}</td>
		</tr>
	</table>
	<form method="post" action="LibrarianLendBook">
		Book ID:
		<input type="text" name="bookId"/>
		<input type="hidden" name="readerId" value="${readerEntity.id }"/> 
		<input type="submit" value="Lend Book"/>
	</form>
	

</c:if>


</body>
</html>