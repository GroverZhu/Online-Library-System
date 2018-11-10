<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
</head>
<body>

	<h1>Search Result</h1>
	<p>
		Search by : <b>${param.searchBy}</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Keyword : <b>${param.keyword }</b>
	</p>

	<c:if test="${empty sessionScope.searchResult}">
		<p>There is no Result!</p>
	</c:if>
	<c:if test="${not empty sessionScope.searchResult}">
		<table border="2">
			<tr>
				<td width="160">ISBN</td>
				<td width="200">Book Name</td>
				<td width="160">Author(s)</td>
				<td width="160">Publisher</td>
				<td width="160">Price</td>
			</tr>
			<c:forEach var="book" items="${sessionScope.searchResult}">
				<tr>
					<td width="160">${book.ISBN }</td>
					<td width="200"><a href="BookInformation?isbn=${book.ISBN}"><b>${book.name }</b></a></td>
					<td width="160"><c:forEach var="author"
							items="${book.authors }" varStatus="i">
							<c:if test="${i.count == 1}">${author.name}</c:if>
							<c:if test="${i.count != 1}">;${author.name}</c:if>
						</c:forEach></td>
					<td width="160">${book.publisher.name}</td>
					<td width="160">${book.price}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>