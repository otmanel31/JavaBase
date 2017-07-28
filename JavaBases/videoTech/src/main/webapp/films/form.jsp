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
		
		<s:form action="save" method="post">
			<s:hidden name="editId"></s:hidden>
			<s:textfield name="editTitre" label="Titre du film"/>
			<s:textfield name="editAnnee" label="Année du film"/>
			<s:textfield name="editRealisateur" label="Realisateur du film"/>
			<s:textarea name="editDescription" label="description du film" rows="5" cols="50"/>
			<s:textfield name="editRating" label="note du film"/>
			<s:submit value="Editer" />
		</s:form>
	</body>
</html>