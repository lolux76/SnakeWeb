package dao;

import java.util.List;

import beans.Game;
import beans.User;

public interface GameDao {
	void createGame(Game game);
	void updateGame(Game game);
	Game getGame(String uuid) throws Exception;
	List<Game> getAllGamesOfAPlayer(String uuid) throws Exception;
	List<Game> getAllGames();
}
