<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link rel="stylesheet" href="Styles/Accueil.css"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Rubik:400,700">
</head>
<body>
<h1> 
<%=request.getSession().getAttribute("username")%>
</h1>
<div>
<form method="POST">
	<input name="command" value="Rank" type="submit">
	<input name="command" value="Shop" type="submit">
</form>
</div>
</body>
</html>