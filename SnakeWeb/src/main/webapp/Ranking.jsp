<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List"%>
<%@ page import="beans.User"%>
<%@ page import="dao.DaoFactory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Ranks</h1>
<table>
<c:forEach var="user" items="${requestScope['users']}">
<tr>
<td>
<c:out value="${user.getUsername()}"></c:out>
</td>
<td>
<c:out value="${user.getPersonnalBest()}"></c:out>
</td>
</tr>
</c:forEach>
</table>
</body>
</html>