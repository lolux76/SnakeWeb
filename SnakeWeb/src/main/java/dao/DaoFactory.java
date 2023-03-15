package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import dao.dbconfig.DBManager;
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
		
		DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306", "javaEE", "password");
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		Connection db = DriverManager.getConnection(url, username, password);
		if(db != null) {
			if(!DBManager.doesDBExist(db)) { //Database does not exist
				DBManager.createDB(url, username, password, db);
			}
			return db;
		}
		throw new Exception("Cannot open connection do Mysql Server");
		
	}

	public TestDataDao DataTest() {
		return new TestDataDaoImplementation(this);
	}
	
	//get User Dao
	public UserDao getUserDao() {
		return new UserDaoImplementation(this);
	}
	
	public GameDao getGameDao() {
		return new GameDaoImplementation(this);
	}

}
