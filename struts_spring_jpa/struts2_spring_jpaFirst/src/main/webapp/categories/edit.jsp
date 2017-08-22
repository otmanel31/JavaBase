<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>edition categorie</title>
</head>
	<body>
		<h1>edition categorie</h1>
		<s:form method="post" action="categorie/save">
			<s:hidden name="id" />
			<s:textfield name="libelle" label="libelle categorie"/>
			<s:submit value="Soummettre" />
		</s:form>
	</body>
</html>