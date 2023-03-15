package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customServlet.CustomServlet;
import dao.DaoFactory;

/**
 * Servlet implementation class Game
 */
@WebServlet("/Game")
public class Game extends CustomServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Game() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("uuid") != null) {
			
			try {
				beans.Game game = DaoFactory.getInstance().getGameDao().getGame(request.getParameter("uuid"));
				response.setStatus(202);
				response.getWriter().append(game.toJson());
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(404);
			}
			
		}
		else {
			response.setStatus(404);
			try {
				response.getWriter().append(request.getParameter("username").toString());
			}
			catch(Exception e) {
				response.getWriter().append(e.getMessage());
			}
		}
	}
	
	/**
	 * @see CustomServlet#doPatch(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("uuid") != null && request.getParameter("player_one_uuid") != null && request.getParameter("player_two_uuid") != null && request.getParameter("player_one_score") != null && request.getParameter("player_two_score") != null) {
			String uuid = request.getParameter("uuid");
			String player_one_uuid = request.getParameter("player_one_uuid");
			String player_two_uuid = request.getParameter("player_two_uuid");
			int player_one_score = Integer.parseInt(request.getParameter("player_one_score"));
			int player_two_score = Integer.parseInt(request.getParameter("player_two_score"));
			
			beans.Game game = new beans.Game(uuid, player_one_uuid, player_two_uuid, player_one_score, player_two_score);
			
			DaoFactory.getInstance().getGameDao().updateGame(game);
			
			response.setStatus(200);
		}
		response.setStatus(400);
		response.getWriter().append("Bad request.").append("\n").append("Correct Format : ").append("\n").append("{uuid, player_one_uuid, player_two_uuid, player_one_score, player_two_score");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
