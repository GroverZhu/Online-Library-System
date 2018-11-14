<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Librarian Add Reader</title>
</head>
<body>
<h1>Librarian Add Reader</h1>
<form method="post" action="LibrarianAddReader">
<table>
	<tr>
		<td><b>Reader 用户名：</b><</td>
		<td><input type="text" name="readerName" width="300"/>由字符、空格、中文组成</td>
	</tr>
	<tr>
		<td><b>初始密码</b></td>
		<td><input type="text" name="password" width="300" value="12345678"/></td>
	</tr>
	<tr>
		<td><b>初始状态</b></td>
		<td>
			<input type="radio" name="state" value="unlock" checked/>解锁状态		
			<input type="radio" name="state" value="blockade" />锁定状态
		</td>
	</tr>
</table>
<input type="submit" value="添加"/>
</form>
<a href="librarianHomepage.jsp">返回主页</a>
</body>
</html>