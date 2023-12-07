<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Error</title>
</head>
<body>
	<h1>Error</h1>
	<h2><%=exception.getMessage() %>/></h2>
	<button id="btn-back"><a href="<%request.getContextPath();%>list">Back</a></button>
</body>
</html>