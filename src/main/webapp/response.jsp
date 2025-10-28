<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello <%=request.getAttribute("user")%></title>
</head>
<body>
Hello <%=request.getAttribute("user")%>
</body>
</html>