<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welome</title>
</head>
<body>
	<h2>Nous somme le <%=LocalDateTime.now() %></h2>
	<p>Fantastique ... enfin je pense ... </p>
	<%-- ceci est un commentaire qui n'apparait pas coté client --%>
	<%-- les balise <% %> contienne un ensemble d'instruction et si dans ce code java on ecrit
		dans la page jsp cela remplacera la balise dans le html généré --%>
	<% 
		// dans ce code on a acces a des objet implicites entre autre but ->
		// un printwirter dans la page jsp
		out.println("code gene via out.println");
		out.println("<ul>");
		for (int i=1; i<5 ; i++){
			out.println("<li>"+ i + "</li>");
		}
		out.println("</ul>");

	%>
</body>
</html>