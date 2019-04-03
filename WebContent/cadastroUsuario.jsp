<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<h1>Cadastro de Usuario</h1>
	<h3>${msg}</h3>
	<form action="salvarUsuario" method="post" class="form-style-1" id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>id:</td>
						<td><input type="text" name="id" readonly="readonly" id="id"
							value="${user.id}" title="Id Do Usuario"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" name="login" id="login"
							value="${user.user}" title="Informe o login"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" name="senha" id="senha"
							value="${user.senha}"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" name="nome" id="nome"
							value="${user.nome}"></td>
					</tr>
					<tr>
						<td>Telefone:</td>
						<td><input type="text" name="telefone" id="telefone" value="${user.telefone}"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Cadastrar">
						<input type="submit" value="Cancelar" onclick="document.getElementById(formUser).action= 'salvarUsuario?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>
	
	<div class="contanier">
		<table class="responsive-table">
		<caption>Usuários Cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Senha</th>
				<th>Delete</th>
				<th>Editar</th>

			</tr>

			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}" ></c:out></td>
					<td><c:out value="${user.user}"></c:out></td>
					<td><c:out value="${user.nome}"></c:out></td>
					<td><c:out value="${user.telefone}"></c:out></td>
					<td><c:out value="${user.senha}"></c:out></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							src="resources/img/excluir.png" width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							src="resources/img/editar.jpg" width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>