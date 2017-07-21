<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="vendors/bootstrap-3.3.7-dist/css/bootstrap.css" />
<title>Create product</title>
</head>
<body>
	<div class="container">
	<h2><c:out value="${titre}" /></h2>
	<form method="post" action="ProduitEdit" novalidate> 
		<div class="form-froup">
			<label for="nom">Nom produit</label>
			<input type="text" name="nom" id="nom" class="form-control" value="<c:out value="${produit.nom}"/>"/>
		</div>
		<div class="form-froup">
			<label for="prix">Prix produit</label>
			<input type="number" name="prix" id="prix" class="form-control" value="<c:out value="${produit.prix}"/>"/>
		</div>
		<div class="form-froup">
			<label for="poid">Poid produit</label>
			<input type="number" name="poid" id="poid" class="form-control" value="<c:out value="${produit.poid}"/>"/>
		</div>
		<input type="hidden" name="id" id="id" value="<c:out value="${produit.id}"/>"/>
		<button type="submit" class="btn btn-primary">Creer un produit</button>
	</form>
	</div>
</body>
</html>