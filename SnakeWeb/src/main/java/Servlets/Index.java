package Servlets;


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
import passwordCheck.PasswordChecking;

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
		request.getSession().setAttribute("holder1", " ");
		request.getSession().setAttribute("holder2", " ");
		DaoFactory daoFactory = DaoFactory.getInstance();
		daoFactory.DataTest().generateData();
		request.getRequestDispatcher("/SignIn.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom,mdp,command;
		/*---Recupération paramètre du Form---*/
		nom=request.getParameter("nom");
		mdp=request.getParameter("mdp");
		command=request.getParameter("command");
		
		/*---Recuperation de la db avec DAO---*/
		DaoFactory daoFactory = DaoFactory.getInstance();
		daoFactory.DataTest().generateData();
		User user;
		
		
		switch(command) {
		case "Register":
			try {
				PasswordChecking checkMdp= new PasswordChecking();
				if(checkMdp.check(mdp)) {
					user=new User(UUID.randomUUID().toString(),nom,mdp, 150);
					daoFactory.getUserDao().add(user);
					request.getSession().setAttribute("username",user.getUsername());
					request.getSession().setAttribute("uuid",user.getUuid());
					request.getSession().setAttribute("personnalBest",user.getPersonnalBest());
					response.sendRedirect("Accueil");
				}
				else {
					request.getSession().setAttribute("holder2", "Le mot de passe doit contenir 8 caracteres dont 2 majuscules et 2 minuscules");
					request.getRequestDispatcher("/SignIn.jsp").forward(request,response);
				}
			} catch (Exception e) {
				response.getWriter().append(e.getMessage()).append("\n");
			}
			break;
		case "Login":
			try {
				/*---recuperation user dans db---*/
				user = daoFactory.getUserDao().getUser(nom, mdp);
				
				/*---Verif mdp user---*/
				if(daoFactory.getUserDao().isPasswordCorrect(user)) {
					/*--- redirection après verif mot de passe---*/
					request.getSession().setAttribute("username",user.getUsername());
					request.getSession().setAttribute("uuid",user.getUuid());
					request.getSession().setAttribute("personnalBest",user.getPersonnalBest());
					response.sendRedirect("Accueil");
				}
				/*daoFactory.getUserDao().delete(user);*/
			} catch (Exception e) {
				request.getSession().setAttribute("holder1", "MDP Incorrect");
				request.getRequestDispatcher("/SignIn.jsp").forward(request,response);
				/*response.getWriter().append(e.getMessage()).append("\n");*/
			}
			break;
		}
	}

}
