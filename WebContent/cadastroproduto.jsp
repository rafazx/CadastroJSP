<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Produto</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
<a href="acessopermitido.jsp">Voltar</a>
	<h1>Cadastro de Produto</h1>
	<h3>${msg}</h3>
	<form action="salvarProduto" method="post" class="form-style-1"
		id="formUser" onsubmit="return valirdarProdutos() ? true : false">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>C�digo:
						<td>
						<td><input type="text" name="id" id="id" readonly="readonly"
							value="${prod.id}"></td>
					</tr>
					<tr>
						<td>Nome:
						<td>
						<td><input type="text" name="nome-produto" id="nome-produto"
							value="${prod.nome}"></td>
					</tr>
					<tr>
						<td>Valor R$:
						<td>
						<td><input type="text" name="valor-produto"
							id="valor-produto" value="${prod.valor}"></td>
					</tr>
					<tr>
						<td>Quantidade:
						<td>
						<td><input type="text" name="quantidade-produto"
							id="quantidade-produto" value="${prod.quantidade}"></td>
					</tr>
					<tr>
						<td><input type="submit" name="cadastrarProduto"
							id="cadastrarProduto" value="Cadastrar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById(formUser).action= 'salvarProduto?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>


	<div class="contanier">
		<table class="responsive-table">
			<caption>Produtos Cadastrados</caption>
			<tr>
				<th>C�digo</th>
				<th>Nome Prouto</th>
				<th>Valor R$:</th>
				<th>Quantidade</th>

			</tr>
			<c:forEach items="${produtos}" var="prod">
				<tr>
					<td><c:out value="${prod.id}"></c:out></td>
					<td><c:out value="${prod.nome}"></c:out></td>
					<td><c:out value="${prod.valor}"></c:out></td>
					<td><c:out value="${prod.quantidade}"></c:out></td>
					<td><a href="salvarProduto?acao=excluir&prod=${prod.id}"><img
							src="resources/img/excluir.png" width="20px" height="20px"></a></td>
					<td><a href="salvarProduto?acao=editar&prod=${prod.id}"><img
							src="resources/img/editar.jpg" width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<script type="text/javascript">
		function valirdarProdutos(){
	if (document.getElementById("nome-produto").value == '') {
			alert("Informe o Nome do Produto");
			return false;
		} else if (document.getElementById("valor-produto").value == '') {
			alert("Informe o valor do produto");
			return false;
		} else if (document.getElementById("quantidade-produto").value == '') {
			alert("Informe a Quantidade");
			return false;
		}
		
		return true;
		}
	</script>


</body>
</html>