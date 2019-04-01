package connection;

import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

import java.sql.Connection;


/**
 * Respons�vel por fazer liga��o com banco de dados
 * 
 * @author rafael
 *
 */
public class SingleConnection {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection(){
		conectar();
	}
	
	public static void conectar(){
		try {
			
			if(connection == null){
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco,user,password);
				connection.setAutoCommit(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
		
	}
	public static Connection getConnection(){
		return connection;
		
	}
	
}
