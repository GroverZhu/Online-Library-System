<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Librarian Modify Reader</title>
</head>
<body>

<h1>Librarian Modify Reader</h1>
<form method="post" action="LibrarianModifyReader">
	<table border="2">	
		<tr>
			<td>Reader ID:</td>
			<td>${readerEntity.id }</td>
			<input type="hidden" name="id" value="${readerEntity.id }" />
		</tr>
		<tr>
			<td>Reader Name:</td>
			<td><input type="text" name="name" value="${readerEntity.name }" /></td>
		</tr>
		<tr>
			<td>Reader password:</td>
			<td><input type="text" name="password" value="${readerEntity.password }" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" value="${readerEntity.email }" /></td>
			
		</tr>
		<tr>
			<td>State:</td>
			<td>
				当前状态：${readerEntity.state }"
				<c:if test="${readerEntity.state eq 'unlock'}">
					<input type="radio" name="state" value="blockade"  />锁定用户
					<input type="radio" name="state" value="unlock" checked />解锁用户
				</c:if>
				<c:if test="${readerEntity.state eq 'blockade'}">
					<input type="radio" name="state" value="blockade" checked />锁定用户
					<input type="radio" name="state" value="unlock" />解锁用户
				</c:if>
			</td>
		</tr>
	</table>
	<input type="submit" value="提交" />
	<input type="reset" value="重置" />
</form>



</body>
</html>