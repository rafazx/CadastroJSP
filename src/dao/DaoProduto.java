package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;
import connection.SingleConnection;

public class DaoProduto {
	private Connection connection;
	
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	
	}
	
	public void inserirProduto(Produto produto){
		try {
			String sql ="INSERT into produto (nome , valor , quantidade) VALUES (?,?,?) ";
			PreparedStatement insert = connection.prepareStatement(sql); 
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getValor());
			insert.setDouble(3, produto.getQuantidade());
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
	public List<Produto> listar() throws Exception{
		List<Produto> listar = new ArrayList<Produto>();
		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		while(set.next()){
		Produto produto = new Produto();
		produto.setNome(set.getString("nome"));
		produto.setValor(set.getDouble("valor"));
		produto.setQuantidade(set.getDouble("quantidade"));
		produto.setId(set.getLong("id"));
		
		listar.add(produto);
		}
		return listar;
		
	}
	
	public void deletarProduto(String id){
		try{
		String sql ="DELETE from produto where id = " +id;
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		connection.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		}
	
	public Produto consultarProduto(String id) throws Exception{
		
			String sql ="select * from produto where id = " +id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			while(set.next()){
			Produto produto = new Produto();
			produto.setId(set.getLong("id"));
			produto.setNome(set.getString("nome"));
			produto.setValor(set.getDouble("valor"));
			produto.setQuantidade(set.getDouble("quantidade"));
			return produto;
			}
			return null;
		
		
	}
	
	public void atualizarProduto(Produto produto){
		try {
			String sql = "update produto set nome = ? , valor = ? , quantidade = ? , where id = "+produto.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getValor());
			statement.setDouble(3, produto.getQuantidade());
			statement.executeUpdate();
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
	
	
	
}
