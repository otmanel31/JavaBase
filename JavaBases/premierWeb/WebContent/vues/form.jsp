<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Commentaire page</title>
</head>
<body>
	<h2>Votre commentaire</h2>
	<form action="Aiguillage" method="post">
		<table>
			<tr>
				<td><label for="nom"> vortre nom</label>
					<input name="nom" id="nom" placeholder="Saisissez votre nom" />
				</td>
			</tr>
			<tr>
				<td><label for="email"> vortre email</label>
					<input name="email" id="email" placeholder="Saisissez votre email" />
				</td>
			</tr>
			<tr>
				<td><label for="commentaire"> vortre commentaire</label>
					<textarea name="commentaire" id="commentaire" rows="5" cols="50" placeholder="Saisissez votre commentaire ..."></textarea>
				</td>
			</tr>
			<tr>
				<td><label for="note"> vortre note</label>
					<select name="note" id="note" >
						<option value="0">pas content</option>
						<option value="1">content</option>
						<option value="2">tres content</option>
						<option value="4" selected>waw</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="envoyer" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>