<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.css" />
	<title>Voyages</title>
</head>
<body>
	<div class="container">
		<h2>Liste des voyages</h2>
	<table class="table table-stripped" border="1">
		<thead>
			<th>Id</th>
			<th>Libellé</th>
			<th>destination</th>
			<th>date de depart</th>
			<th>date de retour</th>
			<th>prix</th>
			<th>agence</th>
			<th>passeport</th>
			<th>ACTIONS</th>
		</thead>
		<tbody>
			<s:iterator value="voyages">
				<tr>
					<td><s:property value="id"/> </td>
					<td><s:property value="libelle"/> </td>
					<td><s:property value="destination"/> </td>
					<td><s:property value="dateDept"/> </td>
					<td><s:property value="dateRetour"/> </td>
					<td><s:property value="prix"/> </td>
					<td><s:property value="agencee.nom"/> </td>
					<td><s:property value="passport"/> </td>
					<td>
						<s:form action="editer" method="post" theme="simple" style="display: inline-block;">
							<s:hidden name="id" />
							<s:submit value="Editer" class="btn btn-warning"/>
						</s:form>
						<s:form action="delete" method="post" theme="simple" style="display: inline-block;">
							<s:hidden name="id" />
							<s:submit value="Supprimer" class="btn btn-danger"/>
						</s:form>
					</td>
				</tr>
			</s:iterator>
			
		</tbody>
	</table>
	<s:form action="create" methode="post" theme="simple">
		<s:submit value="Creer" class="btn btn-success"/>
	</s:form>
	</div>
	
</body>
</html>