<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessopermitido.jsp">Voltar</a>
	<h1>Cadastro de Usuario</h1>
	<h3>${msg}</h3>
	<form action="salvarUsuario" method="post" class="form-style-1"
		id="formUser" onsubmit="return validarCampos() ? true : false">
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
						<td><input type="text" name="telefone" id="telefone"
							value="${user.telefone}"></td>
					</tr>
					<tr>
						<td>CEP:</td>
						<td><input type="text" name="cep" id="cep"
							onblur="consultaCEP()" value="${user.cep}"></td>
					</tr>
					<tr>
						<td>Rua:</td>
						<td><input type="text" name="rua" id="rua" value="${user.rua}"></td>
					</tr>
					<tr>
						<td>Bairro:</td>
						<td><input type="text" name="bairro" id="bairro" value="${user.bairro}"></td>
					</tr>
					<tr>
						<td>Cidade:</td>
						<td><input type="text" name="cidade" id="cidade" value="${user.cidade}"></td>
					</tr>
					<tr>
						<td>Estado:</td>
						<td><input type="text" name="estado" id="estado" value="${user.estado}"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Cadastrar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById(formUser).action= 'salvarUsuario?acao=reset'"></td>
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
					<td><c:out value="${user.id}"></c:out></td>
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
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert("Informe o Login");
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert("Informe o Senha");
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert("Informe o Nome");
				return false;
			} else if (document.getElementById("telefone").value == '') {
				alert("Informe o telefone");
				return false;
			}
			
			return true;

		}
		
		function consultaCEP(){
			var cep = $('#cep').val();			
			  //Consulta o webservice viacep.com.br/
            $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
                if (!("erro" in dados)) {
                	$("#rua").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#cidade").val(dados.localidade);
            		$('#estado').val(dados.uf);
                	
                } 
                else {
       
                    alert("CEP não encontrado.");
                }
            });   
			
		}
	</script>

</body>
</html>