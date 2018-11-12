<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="2">
<c:forEach var="" items="${sessionScope.reader.borrowCart}">
	<tr>
		<td width="160">${book.ISBN}</td>
		<td width="200"><a href="controller/BookInformation?isbn=${book.ISBN}"><b>${book.name}</b></td>
		<td width="160">${book.publisher.name}</td>
		<td width="160">
			<c:forEach var="author" items="${book.authors}" varStatus="i">
				<c:if test="${i.count == 1}">${author.name}</c:if>
				<c:if test="${i.count != 1}">;${author.name}</c:if>
			</c:forEach>
		</td>
		<td width="160">${book.price}</td>
	</tr>
	</c:forEach>

</table>


</body>
</html>