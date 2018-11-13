<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Operate Reader</title>
</head>
<body>
<h1>Operate Reader</h1>
<form method="post" action="controller/librarian/OperateReader">
Reader 账号：<input type="text" name="account">

<input type="submit" value="查找">
</form>


</body>
</html>