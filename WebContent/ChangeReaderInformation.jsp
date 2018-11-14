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
<form method="post" action="ChangeReaderInformation">
	<table border="2">	
		<tr>
			<td>Reader ID:</td>
			<td>${readerEntity.id }</td>
			<input type="hidden" name="id" value="${readerEntity.id }">
		</tr>
		<tr>
			<td>Reader Name:</td>
			<td><input type="text" name="name" value="${readerEntity.name }"></td>
		</tr>
		<tr>
			<td>Reader password:</td>
			<td><input type="text" name="password" value="${readerEntity.password }"></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" value="${readerEntity.email }"></td>
			
		</tr>
		<tr>
			<td>State:</td>
			<td>
				当前状态：${readerEntity.state }"
				<c:if test="${readerEntity.state eq 'unlock'}">
					<input type="radio" name="state" value="blockade" >锁定用户
					<input type="radio" name="state" value="unlock" checked>解锁用户
				</c:if>
				<c:if test="${readerEntity.state eq 'blockade'}">
					<input type="radio" name="state" value="blockade" checked>锁定用户
					<input type="radio" name="state" value="unlock">解锁用户
				</c:if>
			</td>
		</tr>
	</table>
	<input type="submit" value="提交">
	<input type="reset" value="重置">
</form>
</body>
</html>