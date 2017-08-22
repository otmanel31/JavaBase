<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>edition produit</title>
</head>
	<body>
		<h1>edition produit</h1>
		<s:form method="post" action="produit/save">
			<s:hidden name="id" />
			<s:textfield name="nom" label="nom produit"/>
			<s:textfield name="prix" label="prix" />
			<s:textfield  name="poid" label="poid"/>
			<s:submit value="Soummettre" />
		</s:form>
		<!--  ajouter un bloc s:if pour le cas ou on est en creation 
			si vrai alors inutile de rajouter ce qui est ci dessous
		 -->
		<!-- affichage la liste de categories des produits -->
			<h3>Listes des categories deja associés au produits</h3>
			<p>
				<s:set name="prodid" value="id"></s:set>
				cliquer sur une categories pour la retirer
				<s:iterator value="categories">
					<s:a action="produit/catRemove/%{prodid}/%{id}">
						<s:property value="libelle"/>
					</s:a>
				</s:iterator>
			</p>
			<h3>Listes des categories non associés au produits</h3>
			<p>
				
				cliquer sur une categories pour l ajouter
				<s:iterator value="allCategories">
					<s:a action="produit/catAdd/%{prodid}/%{id}">
						<s:property value="libelle"/>
					</s:a>
				</s:iterator>
			</p>
	</body>
</html>