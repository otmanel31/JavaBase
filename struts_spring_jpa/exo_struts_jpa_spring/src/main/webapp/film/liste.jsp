<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Liste des films</title>
	</head>
	<body>
		<h3>Liste des films</h3>
		<table border="1">
			<thead>
				<tr>
					<th>id</th>
					<th>titre</th>
					<th>annee</th>
					<th>realisateur</th>
					<th>Acteurs</th>
					<th>actions</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="films">
					<tr>
						<td><s:property value="id"/> </td>
						<td><s:property value="titre"/> </td>
						<td><s:property value="annee"/> </td>
						<td><s:property value="realisateur.nom"/> </td>
						<td>
							<s:iterator value="acteurs" status="stats">
								<s:property value="nom"/>
								<s:if test="#stats.end == false">, </s:if> 
							</s:iterator>
						</td>
						<td>
							<s:a action="film/edit/%{id}">editer</s:a> 
							<s:form theme="simple" action="film/delete" methode="post">
								<s:hidden name="id"></s:hidden>
								<s:submit value="supprimer" />
							</s:form>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<s:a action="film/create">creer un film</s:a>
	</body>
</html>