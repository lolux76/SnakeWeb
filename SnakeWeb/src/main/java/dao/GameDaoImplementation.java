package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Game;
import beans.User;

public class GameDaoImplementation implements GameDao {

	private DaoFactory daoFactory;
	
	public GameDaoImplementation(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void createGame(Game game) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO db.games(uuid, player_one_uuid, player_two_uuid, player_one_score, player_two_score) VALUES(?, ?, ?, ?, ?);");
			preparedStatement.setString(1, game.getUuid());
			preparedStatement.setString(2, game.getPlayer_one_uuid());
			preparedStatement.setString(3, game.getPlayer_two_uuid());
			preparedStatement.setInt(4, game.getPlayer_one_score());
			preparedStatement.setInt(5, game.getPlayer_two_score());
			
			//Execute query
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateGame(Game game) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE db.games SET player_one_score = ? , player_two_score = ? WHERE uuid=?;");
			preparedStatement.setInt(1, game.getPlayer_one_score());
			preparedStatement.setInt(2, game.getPlayer_two_score());
			preparedStatement.setString(3, game.getUuid());
			
			//Execute query
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Game getGame(String uuid) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT uuid, player_one_uuid, player_two_uuid, player_one_score, player_two_score FROM db.games WHERE uuid=?;");
			preparedStatement.setString(1, uuid);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				return new Game(result.getString("uuid"), result.getString("player_one_uuid"), result.getString("player_two_uuid"), result.getInt("player_one_score"), result.getInt("player_two_uuid"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		throw new Exception("No game found with uuid : " + uuid);
	}

	@Override
	public List<Game> getAllGamesOfAPlayer(String uuid) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		List<Game> games = new ArrayList<Game>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT uuid, player_one_uuid, player_two_uuid, player_one_score, player_two_score FROM db.games WHERE player_one_uuid=? OR player_two_uuid=?;");
			preparedStatement.setString(1, uuid);
			preparedStatement.setString(2, uuid);
			result = preparedStatement.executeQuery();
			while(result.next()) {
				games.add(new Game(result.getString("uuid"), result.getString("player_one_uuid"), result.getString("player_two_uuid"), result.getInt("player_one_score"), result.getInt("player_two_uuid")));
			}
			return games;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		throw new Exception("No game found with user uuid : " + uuid);
	}

	@Override
	public List<Game> getAllGames() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		
		List<Game> games = new ArrayList<Game>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement("SELECT uuid, player_one_uuid, player_two_uuid, player_one_score, player_two_score FROM db.games;");
			result = preparedStatement.executeQuery();
			while(result.next()) {
				games.add(new Game(result.getString("uuid"), result.getString("player_one_uuid"), result.getString("player_two_uuid"), result.getInt("player_one_score"), result.getInt("player_two_uuid")));
			}
			return games;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return games;
	}

}
