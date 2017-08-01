<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video tech</title>
</head>
<body>
	<h2>Films</h2>
	<!-- <p><s:property value="title"/></p>-->
	<p>Value s:property a la poubelle</p>
	
	<table>
		<thead>
			<tr>
				<th>Titre</th>
				<th>annee</th>
				<th>Realisateur</th>
				<th>Descriptuion</th>
				<th>Votes</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="films">
				<tr>
					<td><s:property value="title"/> </td>
					<td><s:property value="annee"/></td>
					<td><s:property value="realisateur"/></td>
					<td><s:property value="description"/> </td>
					<td><s:property value="rating"/></td>
					<td>
						<s:form action="editMd" method="post" theme="simple">
							<s:hidden name="id" />
							<s:submit value="Edit"/>
						</s:form>
						<s:form action="deleteMd" method="post"  theme="simple">
							<s:hidden name="id" />
						<s:submit value="Delete"/>	
						</s:form>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<s:form action="createMd" method="post" theme="simple">
			<s:submit value="Create"/>
	</s:form>
</body>
</html>