package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import beans.Produto;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProduto daoProduto = new DaoProduto();

	public ProdutoServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String prod = request.getParameter("prod");
			String acao = request.getParameter("acao");
			if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("excluir")) {
				daoProduto.deletarProduto(prod);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {
				Produto produto = daoProduto.consultarProduto(prod);			
				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("prod", produto);
				view.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {


			try {
				
				boolean podeInserir = true;
				String msg = null;
				
				String nome = request.getParameter("nome-produto");
				String valor = request.getParameter("valor-produto");
				String quantidade = request.getParameter("quantidade-produto");
				String id = request.getParameter("id");

				Produto produto = new Produto();
				produto.setNome(nome);
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
				
				if(valor != null && !valor.isEmpty()) {
				produto.setValor(Double.parseDouble(valor));
				msg = "Inserir o valor";
				podeInserir = false;
				}
				
				else if(quantidade != null && quantidade.isEmpty()) {
				produto.setQuantidade(Double.parseDouble(quantidade));
				msg = "inserir a quantidade";
				podeInserir = false;
				}
				
				
				else if(nome == null || nome.isEmpty()) {
					msg = "Informe o nome do Produto";
					podeInserir = false;
				}
										
				else if (id != null && !id.isEmpty() ) {
					daoProduto.atualizarProduto(produto);

				} else {

					daoProduto.inserirProduto(produto);
			
				}
				
				if(msg != null) {
					request.setAttribute("msg", msg);
					
				}
				if(!podeInserir) {
					request.setAttribute("prod", produto);
				}
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
