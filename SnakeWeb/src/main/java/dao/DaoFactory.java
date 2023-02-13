package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.dbconfig.TestDataDao;
import dao.dbconfig.TestDataDaoImplementation;

public class DaoFactory {
	private String url;
	private String username;
	private String password;
	
	public DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DaoFactory getInstance() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306", "root", "password");
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	public TestDataDao DataTest() {
		return new TestDataDaoImplementation(this);
	}
	
	//get User Dao
	public UserDao getUserDao() {
		return new UserDaoImplementation(this);
	}

}
