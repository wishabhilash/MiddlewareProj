<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete</title>
</head>
	<body>
		<div>Delete successful!!!</div>
		<%= session.getAttribute("result")	%>
		<div>
			<form method="get" action="Controller" name = "delete_success">
				<input type = "submit" value = "back">
			</form>
		</div>
	</body>
</html>