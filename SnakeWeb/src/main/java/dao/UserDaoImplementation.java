package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserDaoImplementation implements UserDao{

	private DaoFactory daoFactory;
	
	public UserDaoImplementation(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void add(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO db.user(uuid, username, password, personnalBest) VALUES(?, ?, SHA1(?), ?);");
			preparedStatement.setString(1, user.getUuid());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.getPersonnalBest());
			
			//Execute query
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUser(String username, String password) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT uuid, personnalBest FROM db.user WHERE username=? AND password=SHA1(?);");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				return new User(result.getString("uuid"), username, password, result.getInt("personnalBest"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		throw new Exception("Unknown user : " + username);
		
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT uuid, username, personnalBest FROM db.user;");
			
			while(result.next()) {
				String uuid = result.getString("uuid");
				String username = result.getString("username");
				int personnalBest = result.getInt("personnalBest");
				
				User user = new User(uuid, username, "", personnalBest);
				users.add(user);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public void delete(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE db.user SET username = NULL, password = NULL, personnalBest = NULL WHERE uuid=?;");
			preparedStatement.setString(1, user.getUuid());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isPasswordCorrect(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT password FROM db.user WHERE username=? AND password=SHA1(?);");
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			result = preparedStatement.executeQuery();
			
			while(result.next()) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	
}
