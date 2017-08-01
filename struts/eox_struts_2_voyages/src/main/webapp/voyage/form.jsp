<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.css" />
<title>Editer</title>
<style type="text/css">
	.erreur_saisie {
		background-color: pink;
	}
</style>
</head>
<body>
	<div class="container">
		<h2>Formulaire d'edition</h2>
		<s:form action="save" method="post" theme="simple">
			<s:if test="hasFieldErrors() || hasActionErrors()">
				<div class="alert alert-danger" role="alert">
					<s:fielderror />
					<s:actionerror/>
				</div>
			</s:if>
			
		<div class="form-group">
			<label for="id">Id </label>
			<s:textfield name="id" id="id" cssClass="form-control" />
		</div>
		<div class="form-group">
			<label for="libelle">Libelle</label>
			<s:textfield name="libelle" id="libelle" cssClass="form-control" cssErrorClass="erreur_saisie"
			    aria-describedby="libelle_addon"	/>
		</div>
		
		<div class="form-group">
			<label for="destination">Destination</label>
			<s:textfield name="destination" id="destination" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="dateDept">Date de depart</label>
			<s:textfield name="dateDept" id="dateDept" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="dateRetour">Date de retour</label>
			<s:textfield name="dateRetour" id="dateRetour" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="prix">Prix</label>
			<s:textfield name="prix" id="prix" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="agence">Agence</label>
			<!---<s:textfield name="agence" id="agence" class="form-control"/>-->
			<s:select list="agences" listKey="id" listValue="nom" name="agence"
				 aria-describedby="agence_addon"  cssClass="form-control" />
		</div>
		<div class="form-group">
			<label for="passport">Passeport</label>
			<s:checkbox name="passport" id="passport" class="form-control"/>
		</div>
		<s:submit value="Editer" class="btn btn-success" />
	</s:form>
	</div>
	
</body>
</html>