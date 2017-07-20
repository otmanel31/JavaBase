<%@page import="webBoutique.Metier.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Listes des produits</h2>
	<table border="1">
		<thead>
			<tr>Id</tr>
			<tr>Nom</tr>
			<tr>Prix</tr>
			<tr>Poid</tr>
		</thead>
		<tbody>
			<%
				List<Produit> produits = (List<Produit>)request.getAttribute("produits");
				for(Produit p : produits){
					%>
					<tr>
						<td><%= p.getId()%></td>
						<td><%= p.getNom()%></td>
						<td><%= p.getPrix()%></td>
						<td><%= p.getPoid() %></td>
					</tr>
				<%}
				%>
		</tbody>
	</table>
</body>
</html>