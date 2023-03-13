package dao;

import java.util.List;

import beans.User;

public interface UserDao {
	void add(User user);
	User getUser(String username, String password) throws Exception;
	User getUser(String username) throws Exception;
	List<User> getAllUsers();
	void delete(User user);
	boolean isPasswordCorrect(User user);
}
