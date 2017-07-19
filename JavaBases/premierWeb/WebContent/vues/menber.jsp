<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
		merci de votre commentaire <%= request.getAttribute("nom") %>!<br/>
		Toi on va te faire ta fete!
	</h2>
	<p style="border: 2px solid dashed blue;">
		commentaire: <%= request.getAttribute("commentaire") %>
	</p>
</body>
</html>