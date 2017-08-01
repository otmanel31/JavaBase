<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit form</title>
	</head>
	<body>
		<h2>Formulaire d'edition de film</h2>
		
		<s:form action="saveMd" method="post">
			<s:hidden name="id"></s:hidden>
			<s:textfield name="title" label="Titre du film"/>
			<s:textfield name="annee" label="Année du film"/>
			<s:textfield name="realisateur" label="Realisateur du film"/>
			<s:textarea name="description" label="description du film" rows="5" cols="50"/>
			<s:textfield name="rating" label="note du film"/>
			<s:submit value="Editer" />
		</s:form>
	</body>
</html>