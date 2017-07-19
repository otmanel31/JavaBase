<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exo1b</title>
</head>
<body>
	<h1>Generation figure</h1>
	<form action="Generator" method="post">
		<label for="type">Selectionnez unu type de figure</label> 
		<select name="type" id="type">
			<option value="0">triangle</option>
			<option value="1">carre</option>
			<option value="0">rectangle</option>
		</select> 
		<input type="text" name="taille" placeholder="taille figure ... " />
		
		<input type="submit" value="Creer"/>
	</form>
</body>
</html>