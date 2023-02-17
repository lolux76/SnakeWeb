

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.DaoFactory;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Index() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/SignIn.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom,mdp,command;
		nom=request.getParameter("nom");
		mdp=request.getParameter("mdp");
		command=request.getParameter("command");
		DaoFactory daoFactory = DaoFactory.getInstance();
		daoFactory.DataTest().generateData();
		User user;
		switch(command) {
		case "Register":
			try {
				user=new User(UUID.randomUUID().toString(),nom,mdp, 150);
				daoFactory.getUserDao().add(user);
			} catch (Exception e) {
				response.getWriter().append(e.getMessage()).append("\n");
			}
			break;
		case "Login":
			try {
				user = daoFactory.getUserDao().getUser(nom, mdp);
				if(daoFactory.getUserDao().isPasswordCorrect(user)) {
					response.getWriter().append("MDP correct" ).append("\n");
				}
				daoFactory.getUserDao().delete(user);
			} catch (Exception e) {
				response.getWriter().append(e.getMessage()).append("\n");
			}
			break;
		}
	}

}
