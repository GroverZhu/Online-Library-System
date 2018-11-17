<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Show Cart</title>
</head>
<body>
<table border="2">
	<tr>
		<td>Reader ID</td>
		<td>Reader Name</td>
		<td>Book ID</td>
		<td>Book Name</td>
		<td>Submit Time</td>
		<td>Operate</td>
	</tr>
	<c:forEach var="item" items="${cartList }" varStatus="i">
		<tr>
			<td>${item.readerId }</td>
			<td>${item.readerName }</td>
			<td>${item.bookId }</td>
			<td>${item.bookName }</td>
			<td>${item.submitTime }</td>
			<td>
				<a href="LibrarianLendBook?operate=agree&readerId=${item.readerId}&bookId=${item.bookId}">Agree</a> or 
				<a href="LibrarianLendBook?operate=disagree&readerId=${item.readerId}&bookId=${item.bookId}">Disagree</a>
			</td>
		</tr>
	</c:forEach>
</table>
	<ul >
		<li><a href="?start=0">首 页</a></li>
		<li><a href="?start=${pre}">上一页</a></li>
		<li><a href="?start=${next}">下一页</a></li>
		<li><a href="?start=${last}">末 页</a></li>
	</ul>


</body>
</html>