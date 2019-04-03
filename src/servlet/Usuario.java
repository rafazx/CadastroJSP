package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCurso;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daousuario = new DaoUsuario();

	public Usuario() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("editar")) {
				BeanCurso curso = daousuario.consultar(user);
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", curso);
				view.forward(request, response);

			}

			else if (acao.equalsIgnoreCase("delete")) {
				daousuario.delete(user);
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				view.forward(request, response);
			
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				view.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String id = request.getParameter("id");
			String telefone = request.getParameter("telefone");

			BeanCurso usuario = new BeanCurso();
			usuario.setUser(login);	
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(Long.parseLong(telefone));
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);

			try {
				String msg = null;
				boolean podeInserir = true;
				
				if(!daousuario.validarSenha(senha)){
				msg = "Senha já existente";
				podeInserir = false;	
				}
				
				if(id == null || id.isEmpty() && !daousuario.validarLogin(login)){
					msg = "Usuario já Existe com o mesmo Login";
					podeInserir = false;
				}
				if(msg != null){
					request.setAttribute("msg", msg);
				}
				
				if (id == null || id.isEmpty()
						&& podeInserir) {
					daousuario.salvarUsuario(usuario);

				} else if (id != null && !id.isEmpty()) {
					if(!daousuario.validarLoginUpdate(login, id)){
						request.setAttribute("msg", "Login já existente");
					}
					else{	
						daousuario.autualizar(usuario);
					} 
				}
				if(!podeInserir){
					
					request.setAttribute("user", usuario );
				}

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daousuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
