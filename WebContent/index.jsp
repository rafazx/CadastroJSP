<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<h1>Login</h1>
			<form action="LoginServlet" method="post" class="login-form">
				Login: <input type="text" id="user" name="user"><br />
				Senha: <input type="text" id="senha" name="senha"><br />
				<button type="submit" name="enviar" value="Logar">Logar</button>

			</form>
		</div>
	</div>
</body>
</html>