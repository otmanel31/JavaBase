<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Liste des categories</title>
</head>
	<body>
		<h1>Liste des categories</h1>
		<table border="1">
			<thead>
				<tr>
					<th>Id</th>
					<th>libelle</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="categories">
					<tr>
						<td><s:property value="id"/> </td>
						<td><s:property value="libelle"/> </td>
						<td>
							<s:a action="categorie/edit/%{id}">Editer produit</s:a>
							<s:form theme="simple" action="categorie/delete" methode="post">
								<s:hidden name="id"></s:hidden>
								<s:submit value="supprimer" />
							</s:form>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<s:a action="categorie/create">Creer categorie</s:a>
	</body>
</html>