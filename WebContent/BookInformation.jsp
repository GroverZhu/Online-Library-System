<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Book Information</h1>
	<table border="1">
		<c:forEach var="bookInfo" items="${bookInformation}" varStatus="i">
			<c:if test="${i.count == 1}">
				<tr>
					<td width="160"><b>ISBN:</b></td>
					<td width="400">${bookInfo.ISBN }</td>
				</tr>
				<tr>
					<td width="160"><b>Book Name:</b></td>
					<td width="400">${bookInfo.name }</td>
				</tr>
				<tr>
					<td width="160"><b>Publisher:</b></td>
					<td width="400">${bookInfo.publisher.name }</td>
				</tr>
				<tr>
					<td width="160"><b>Author(s):</b></td>
					<td width="400"><c:forEach var="author"
							items="${bookInfo.authors}" varStatus="i">
							<c:if test="${i.count == 1}">${author.name}</c:if>
							<c:if test="${i.count != 1}">;${author.name}</c:if>
						</c:forEach></td>
				</tr>
				<tr>
					<td width="160"><b>Price:</b></td>
					<td width="400">${bookInfo.price}</td>
				</tr>
				<tr>
					<td width="160"><b>Description:</b></td>
					<td width="400">${bookInfo.description}</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<br />
	<br />
	<form method="post" action="">
		<table border="1">
			<tr>
				<td width="160">Book ID</td>
				<td width="450">Location</td>
				<td width="160">Status</td>
				<td width="160">Select</td>
			</tr>
			<c:forEach var="bookInfo" items="${bookInformation}" varStatus="i">
				<tr>
					<td width="160">${bookInfo.bookId}</td>
					<td width="450">${bookInfo.location}</td>
					<td width="160">${bookInfo.status}</td>
					<td width="160"><a
						href="AddToCart?book_id=${bookInfo['bookId']}">Add To Borrow
							Cart</a></td>

				</tr>
			</c:forEach>
		</table>
	</form>

</body>
</html>