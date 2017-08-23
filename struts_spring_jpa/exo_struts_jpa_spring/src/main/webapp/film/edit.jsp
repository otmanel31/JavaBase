<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edition d'un film</title>
	</head>
	<body>
		<h1>Editer un film</h1>
		<s:form method="post" action="film/save">
			<s:hidden  name="id"/>
			<s:textfield name="titre"/>
			<s:textfield name="synopsis"/>
			<s:textfield name="annee"/>
			<s:select list="realisateurs" listKey="id" listValue="nom" name="realisateurId"></s:select>
			<h5>ACTEURS DS FILM</h5>
			<s:set name="filmId" value="id" />
			<s:iterator value="acteurs">
				<s:a action="film/acteuremove/%{filmId}/%{id}">
					<s:property value="nom"/>
				</s:a>
			</s:iterator>
			<h5>ACTEURS not in FILM</h5>
			<s:iterator value="acteursNotInFilm">
				<s:a action="film/acteuradd/%{filmId}/%{id}">
					<s:property value="nom"/>
				</s:a>
			</s:iterator>
			<!-- <s:textfield name="realisateur.nom"/> -->
			<s:submit value="soummettre"/>
		</s:form>
	</body>
</html>