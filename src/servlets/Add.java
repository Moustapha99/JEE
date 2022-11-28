package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import dao.UtilisateurDao;
import forms.AddUserForm;

/**
 * Servlet implementation class Add
 */
@WebServlet("/add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String VUE_AJOUTER_UTILISATEUR = "/WEB-INF/ajouterUtilisateur.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(VUE_AJOUTER_UTILISATEUR).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddUserForm form;
		try {
			form = new AddUserForm(request);
			try {
				if(form.ajouter()) 
				{
					response.sendRedirect("list");
				} 
				else 
				{
					request.setAttribute("status", form.isStatus());
					request.setAttribute("statusMessage", form.getStatusMessage());
					request.setAttribute("utilisateur", form.getUtilisateur());
					request.setAttribute("errors", form.getErreurs());
					getServletContext().getRequestDispatcher(VUE_AJOUTER_UTILISATEUR).forward(request, response);
				}
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// } catch (SQLException | IOException | ServletException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
	}

}
