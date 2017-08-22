<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Liste des produits</title>
</head>
	<body>
		<h1>Liste des produits</h1>
		<table border="1">
			<thead>
				<tr>
					<th>Id</th>
					<th>nom</th>
					<th>prix</th>
					<th>poid</th>
					<th>categories</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="produits">
					<tr>
						<td><s:property value="id"/> </td>
						<td><s:property value="nom"/> </td>
						<td><s:property value="prix"/> </td>
						<td><s:property value="poid"/> </td>
						<td>
							<s:iterator value="categories">
								<s:property value="libelle"/>
							</s:iterator>
						</td>
						<td>
							<s:a action="produit/edit/%{id}">Editer produit</s:a>
							<s:form theme="simple" action="produit/delete" methode="post">
								<s:hidden name="id"></s:hidden>
								<s:submit value="supprimer" />
							</s:form>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<s:a action="produit/create">Creer Produit</s:a>
	</body>
</html>