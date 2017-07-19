<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
	merci de votre commentaire <%= request.getAttribute("nom") %>!<br/>
	Bous etes inscrit a notre mailing list a ladresse <%= request.getAttribute("email") %>!<br/>
	 nous avons bien enregistrer votre commentaire! <br/>
	Continueer la navigation sur notre site ou saisissez un nouveau commentaiire
	<a href="Aiguillage" >ici</a>
	</h2>
</body>
</html>