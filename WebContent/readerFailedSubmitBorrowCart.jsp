<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.ReaderEntity}">
	<jsp:forward page="homepage.jsp" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
</head>
<body>
	<script language='javascript'>alert('Submit Borrow Cart Failed!It has been reserved!');window.location.href='readerBorrowCart.jsp';</script>
	");
	<jsp:include page="Footer.jsp" />
</body>
</html>