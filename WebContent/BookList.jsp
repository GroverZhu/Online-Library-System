<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
</head>
<body>
	<h1>Book List</h1>

	<form method="post" action="controller/SearchBook">
		Search By: <select name="searchBy" size="1">
			<option>Book Name
			<option>Author
			<option>Publisher
			<option>ISBN
		</select> <input type="text" name="keyword"> <input type="submit"
			value="Search..">
	</form>

	<c:if test="${empty sessionScope.bookList}">
		<p>There is no book in the library!</p>
	</c:if>

	<table border="2">
		<tr>
			<td width="160">ISBN</td>
			<td width="200">Book Name</td>
			<td width="160">Publisher</td>
			<td width="160">Author(s)</td>
			<td width="160">Price</td>
		</tr>
		<c:forEach var="book" items="${sessionScope.bookList}">
			<tr>
				<td width="160">${book.ISBN}</td>
				<td width="200"><a
					href="controller/BookInformation?isbn=${book.ISBN}"><b>${book.name}</b></td>
				<td width="160">${book.publisher.name}</td>
				<td width="160"><c:forEach var="author" items="${book.authors}"
						varStatus="i">
						<c:if test="${i.count == 1}">${author.name}</c:if>
						<c:if test="${i.count != 1}">;${author.name}</c:if>
					</c:forEach></td>
				<td width="160">${book.price}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>