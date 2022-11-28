package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtilisateurDao;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				
				String login = request.getParameter("login");
				String pass = request.getParameter("pass");
				HttpSession session = request.getSession();
				if(login != "" || pass != "") {
					if(login.equals("admin") && pass.equals("azerty")) {
						session.setAttribute("session", login);
						response.sendRedirect("list");
					} else {
						request.setAttribute("erreur", " Login ou mot de passe incorrect");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					request.setAttribute("erreur", "Veuillez remplir tous les champs");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/authentification.jsp");
					dispatcher.forward(request, response);
					
				}
	}

}
