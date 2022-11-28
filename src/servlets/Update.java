package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.UtilisateurDao;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VUE_MODIFIER_UTILISATEUR = "/WEB-INF/modifierUtilisateur.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		if( id != null && id.matches("[0-9]+")) {
		ArrayList<Utilisateur> utilisateur = (ArrayList<Utilisateur>)UtilisateurDao.lister();
		for(Utilisateur user : utilisateur) {
			if(user.getId() == Integer.parseInt(id)) {
				request.setAttribute("nom", user.getNom());
				request.setAttribute("prenom", user.getPrenom());
				request.setAttribute("login", user.getLogin());
				request.setAttribute("password", user.getPassword());
				request.setAttribute("passwordBis", user.getPassword());
			}
				
			}
		}
		
		getServletContext().getRequestDispatcher(VUE_MODIFIER_UTILISATEUR).forward(request, response);;

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if( id != null && id.matches("[0-9]+")) {
		Utilisateur u = new Utilisateur(Integer.parseInt(id),nom,prenom,login,password);
		 UtilisateurDao.modifier(u);
		 response.sendRedirect("list");
		}
	}

}
