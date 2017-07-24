<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="vendors/bootstrap-3.3.7-dist/css/bootstrap.css" />
<title>Creation</title>
</head>
<body>
	<h2><c:out value="${titre}" /></h2>
	
	<form action="TodoCreation" method="post" novalidate> 
		<label for="description"> Description</label>
		<input type="text" name="description" id="description" placeholder="Entrer une description" 
			value="${todo.description}"/>
		<label for="priorite">Priorité</label>
		<input type="number" name="priorite" id="priorite" value="${todo.priorite}"/> 
		<label for="contexte">Contexte</label>
		<input type="text" name="contexte" id="contexte" placeholder="Entrer le contexte" value="${todo.contexte}"/>
		<label for="finished">Is finished</label>
		<input type="checkbox" name="finished" id="finished" value="${todo.finished}"/>
		<label for="datetime">La date</label>
		<input type="datetime" name="datetime" class="datetime" id="datetime" value="${todo.dateToStr}" />
		
		<input type="hidden" name="id" id="id" value="${todo.id}" />
		<button type="submit" class="btn btn-primary"><c:out value="${btnForm}" /></button>
	</form>
	
</body>
</html>