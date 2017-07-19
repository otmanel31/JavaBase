<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carree</title>
</head>
<body>
	<h1>Generation carree</h1>
	<%  int t = (int)request.getAttribute("taille"); %>
	<% for (int i = 0; i < t; i++) {
			out.print("*");
			for (int j =0; j< t; j++) {
				if (j == (t-1))
					out.print("");
				else
					out.print("*");
			} 
		}
	%>
</body>
</html>