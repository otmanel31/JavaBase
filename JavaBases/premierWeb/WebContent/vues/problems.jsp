<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>un probleme est survenue ! nous n'avons malheursement pas pu enregistrer
		votre commentaire : cela ne marchera probablement pas si <br/> vous reessayer
		<%= request.getAttribute("nom")%> 
		clqiuer ici pour aller <a href="http://google.fr">ailleur</a>
	</h2>
</body>
</html>