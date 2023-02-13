package dao.dbconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import beans.User;
import dao.DaoFactory;

public class TestDataDaoImplementation implements TestDataDao{

private DaoFactory daoFactory;
	
	public TestDataDaoImplementation(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void generateData() {
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			statement.execute("DELETE FROM db.user");
			
			users.add(new User(UUID.randomUUID().toString(), "Mateo", "LeMotDePasseSecurise", 150));
			users.add(new User(UUID.randomUUID().toString(), "Anthony", "lerootdu49", 25));
			users.add(new User(UUID.randomUUID().toString(), "leSnakeurFou", "mdp", 25));
			users.add(new User(UUID.randomUUID().toString(), "xXxDarkSnakeDu49xXx", "lemdp", 34));
			users.add(new User(UUID.randomUUID().toString(), "H4ck3rman", "lejoker", 47));
			
			for(User user : users) {
				daoFactory.getUserDao().add(user);
			}
		}
		catch (Exception e) {
			e.printStackTrace();

		}
	}
}
