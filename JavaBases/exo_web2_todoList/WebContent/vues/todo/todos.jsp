<%@page import="ewo_web2_todoList.Metier.Todo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="vendors/bootstrap-3.3.7-dist/css/bootstrap.css" />
<title>My todos</title>
</head>
<body>
	<div class="container">
	<h2 class="display1">My todos</h2>  <a href="TodoCreation" class="btn btn-success">Creer une todo</a>
	<table class="table table-stripped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Description</th>
				<th>Priorité</th>
				<th>Contexte</th>
				<th>Fnished</th>
				<th>Date de création</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="t">
				<tr>
					<td><c:out value="${t.id}" /></td>
					<td><c:out value="${t.description}" /></td>
					<td><c:out value="${t.priorite}" /></td>
					<td><c:out value="${t.contexte}" /></td>
					<td><c:out value="${t.finished}" /></td>
					<td><c:out value="${t.dateCreation}" /></td>
					<td>
						<a href="TodoCreation?todoId=<c:out value="${t.id}"/>" class="btn btn-primary">Editer</a>
					
						<form method="post" action="TodoList" style="display: inline-block;">
							<input type="hidden" name="todoId" id="id" value="<c:out value="${t.id}" />"/>
							<button type="submit" class="btn btn-danger">Delete</button>
						</form>
					</td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	</div>
	

</body>
</html>