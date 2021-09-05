package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private final static String URL = "jdbc:mysql://localhost:3306/sportsteams";
	private final static String USERNAME = System.getenv("USERNAME");
	private final static String PASSWORD = System.getenv("PASSWORD");
	private static Connection connection;
	private static DBConnection instance;
	
	@SuppressWarnings("static-access")
	private DBConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static Connection getConnection() {
		
		if (instance == null) {
			
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DBConnection(connection);
				System.out.println("We're In!!\n");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return DBConnection.connection;
	}

}
