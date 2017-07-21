<%@page import="webBoutique.Metier.Produit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap-3.3.7-dist/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h2>Listes des produits</h2>
	<table class="table table-stripped">
		<thead>
		<tr>
			<th>Id</th>
			<th>Nom</th>
			<th>Prix</th>
			<th>Poid</th>
			</tr>
		</thead>
		<tbody>
		<% /*<!-- version jstl
		<c:forEach  items="${produits}" var="p">
			<tr>
						<td><c:out value="${p.id}" /></td>
						<td></td>
						<td></td>
						<td><></td>
						<td>
	
							<a href="ProduitEdit?produitId=" class="btn btn-primary">
							Edition</a> 
							<form method="post" action="Produit" style="display: inline-block;">
								
								<input type="hidden" name="produitId" id="produitId" value=""/>
								<button type="submit" class="btn btn-danger">Suppresion</button>
							</form> 
						</td>
					</tr>
		</c:forEach>
			
		 --> */%>
			<%
				List<Produit> produits = (List<Produit>)request.getAttribute("produits");
				for(Produit p : produits){
					%>
					<tr>
						<td><%= p.getId()%></td>
						<td><%= p.getNom()%></td>
						<td><%= p.getPrix()%></td>
						<td><%= p.getPoid() %></td>
						<td>
	
							<a href="ProduitEdit?produitId=<%= p.getId() %>" class="btn btn-primary">
							Edition</a> 
							<form method="post" action="Produit" style="display: inline-block;">
								
								<input type="hidden" name="produitId" id="produitId" value="<%= p.getId() %>"/>
								<button type="submit" class="btn btn-danger">Suppresion</button>
							</form> 
						</td>
					</tr>
				<%}
				%>
		</tbody>
	</table>
	<a href="ProduitEdit" class="btn btn-success">Creer un produit</a>
	
</div>
</body>
</html>