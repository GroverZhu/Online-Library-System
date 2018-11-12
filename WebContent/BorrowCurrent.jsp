<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Current Borrow List</title>
</head>
<body>
<h1>Current Borrow List</h1>

<c:if test="${empty borrowCurrent}">
	<p>You don't have any book on list!</p>
</c:if>
<table border="2">
	<tr>
		<td width="160">ISBN</td>
		<td width="200">Book Name</td>
		<td width="160">Publisher</td>
		<td width="160">Author(s)</td>
		<td width="160">Borrow Time</td>
		<td width="160">Should Return Time</td>
	</tr>
	<c:forEach var="item" items="${borrowCurrent}" varStatus="i">
	<tr>
		<td width="160">${item.book.ISBN}</td>
		<td width="200"><a href="BookInformation?isbn=${item.book.ISBN}"><b>${item.book.name}</b></a></td>
		<td width="160">${item.book.publisher.name}</td>
		<td width="160">
			<c:forEach var="author" items="${item.book.authors}" varStatus="i">
				<c:if test="${i.count == 1}">${author.name}</c:if>
				<c:if test="${i.count != 1}">;${author.name}</c:if>
			</c:forEach>
		</td>
		<td width="160">${item.borrowTime}</td>
		<td width="160">${item.returnTime}</td>
	</tr>
	</c:forEach>
</table>



</body>
</html>