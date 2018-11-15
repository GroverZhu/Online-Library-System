<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Show Reader Information</title>
</head>
<body>

<c:if test="${not empty currentList }">
<h1>Show Current Borrow</h1>
	<table border="2">
		<tr>
			<td>序号</td>
			<td>用户id</td>
			<td>书名</td>
			<td>book id</td>
			<td>借阅时间</td>
			<td>应还时间</td>
			<td>处理人</td>
			<td>状态</td>
		</tr>
		<c:forEach var="item" items="${currentList}" varStatus="i">
		<tr>
			<td>${i.count }</td>
			<td>${item.readerId}</td>
			<td>${item.bookName }</td>
			<td>${item.bookId }</td>
			<td>${item.borrowTime }</td>
			<td>${item.returnTime }</td>
			<td>${item.borrowLibrarianId }</td>
			<td>${item.state }</td>
		</tr>		
		</c:forEach>
	</table>
</c:if>
<c:if test="${not empty historyList }">
<h1>Show History Borrow</h1>
	<table border="2">
		<tr>
			<td>序号</td>
			<td>用户id</td>
			<td>书名</td>
			<td>book id</td>
			<td>借阅时间</td>
			<td>借阅处理人</td>
			<td>还书时间</td>
			<td>还书处理人</td>
			<td>状态</td>
		</tr>
		<c:forEach var="item" items="${historyList}" varStatus="i">
		<tr>
			<td>${i.count }</td>
			<td>${item.readerId}</td>
			<td>${item.bookName }</td>
			<td>${item.bookId }</td>
			<td>${item.borrowTime }</td>
			<td>${item.borrowLibrarianId }</td>
			<td>${item.returnTime }</td>
			<td>${item.returnLibrarianId }</td>
			<td>${item.state }</td>
		</tr>		
		</c:forEach>
	</table>
</c:if>

</body>
</html>