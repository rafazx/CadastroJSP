package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import beans.BeanCurso;
import connection.SingleConnection;

public class DaoUsuario {
	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvarUsuario(BeanCurso usuario) {
				
		try {
			String sql = "INSERT into usuario (login , senha , nome , telefone)  VALUES (?,?,?,?) ";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getUser());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setLong(4, usuario.getTelefone());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
	}

	
	
	public List<BeanCurso> listar() throws Exception {
		List<BeanCurso> listar = new ArrayList<BeanCurso>();
		String sql = "select * from usuario";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultset = select.executeQuery();
		while (resultset.next()) {
			BeanCurso beancurso = new BeanCurso();
			beancurso.setSenha(resultset.getString("senha"));
			beancurso.setUser(resultset.getString("login"));
			beancurso.setId(resultset.getLong("id"));
			beancurso.setNome(resultset.getString("nome"));
			beancurso.setTelefone(resultset.getLong("telefone"));

			listar.add(beancurso);
		}
		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement statmant = connection.prepareStatement(sql);
			statmant.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	public BeanCurso consultar(String id) throws Exception{
		String sql = "select * from usuario where id ='"+id+"'";
		PreparedStatement editar = connection.prepareStatement(sql);
		ResultSet result = editar.executeQuery();
		if(result.next()){
			BeanCurso beancurso = new BeanCurso();
			beancurso.setSenha(result.getString("senha"));
			beancurso.setUser(result.getString("login"));
			beancurso.setId(result.getLong("id"));
			beancurso.setNome(result.getString("nome"));
			beancurso.setTelefone(result.getLong("telefone"));
			
			return beancurso;
		}
		
		return null;
	}
	
	public boolean validarLogin(String login) throws Exception{
		String sql = "select count(1) as qtd from usuario where login ='"+login+"'";
		PreparedStatement editar = connection.prepareStatement(sql);
		ResultSet result = editar.executeQuery();
		if(result.next()){
			
			return result.getInt("qtd") <= 0;
		}
		
		return false;
	}
	
	public boolean validarSenha(String senha) throws Exception{
		String sql = "select count(1) as qtd from usuario where senha ='"+senha+"'";
		PreparedStatement editar = connection.prepareStatement(sql);
		ResultSet result = editar.executeQuery();
		if(result.next()){
			
			return result.getInt("qtd") <= 0;
		}
		
		return false;
	}
	
	public boolean validarLoginUpdate(String login , String id) throws Exception{
		String sql = "select count(1) as qtd from usuario where login ='"+login+"' and id<> " + id;
		PreparedStatement editar = connection.prepareStatement(sql);
		ResultSet result = editar.executeQuery();
		if(result.next()){
			
			return result.getInt("qtd") <= 0;
		}
		
		return false;
	}
	public void autualizar(BeanCurso usuario) {
		try{
		String sql ="update usuario set login = ?, senha = ? , nome = ? , telefone = ? where id = "+ usuario.getId();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getUser());
		statement.setString(2, usuario.getSenha());
		statement.setString(3, usuario.getNome());
		statement.setLong(4, usuario.getTelefone());
		statement.executeUpdate();
		connection.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		}

}
