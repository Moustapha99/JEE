package forms;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class AddUserForm {
	
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_PASSWORD = "password";
	private static final String CHAMP_PASSWORD_BIS =  "passwordBis";
	private static final String ECHEC_AJOUT_MESSAGE =  "Echec de l'ajout";
	private static final String SUCCESS_AJOUT_MESSAGE =  "Ajout effectué avec succés";
	private static final String EMPTY_FIELD_ERROR_MESSAGE =  "Vous devez renseigner ce champ";
	private static final String DIFFERENT_PASSWORD_ERROR_MESSAGE =  "Les mots de passe ne sont pas conforme";
	
	
	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private Utilisateur utilisateur;
	private boolean status;
	private String statusMessage;
	
	
	public AddUserForm(HttpServletRequest request) throws SQLException {
		this.request = request;
		this.status = false;
		this.statusMessage = ECHEC_AJOUT_MESSAGE;
		this.erreurs = new HashMap<String, String>();
	}

	public boolean ajouter() throws SQLException 
	{
		String nom = this.getParameter(CHAMP_NOM);
		String prenom = this.getParameter(CHAMP_PRENOM);
		String login = this.getParameter(CHAMP_LOGIN);
		String password = this.getParameter(CHAMP_PASSWORD);
		
		this.utilisateur = new Utilisateur(nom,prenom,login,password);
		this.validerChamps(CHAMP_NOM, CHAMP_PRENOM, CHAMP_LOGIN, CHAMP_PASSWORD, CHAMP_PASSWORD_BIS);
		this.validerPassword();
		
		if(this.erreurs.isEmpty()) 
		{
			 UtilisateurDao.ajouter(this.utilisateur);
			 this.status = true;
			 this.statusMessage = SUCCESS_AJOUT_MESSAGE;
		}
		
		return this.status;
	}
	
	

	public String getParameter(String parametre) 
    {
		String valeur = this.request.getParameter(parametre);
		return (valeur == null || valeur.trim().isEmpty()) ? null : valeur.trim();
	}
	
	private void validerChamps(String...champs) 
    {
		for(String champ : champs)
		{
			if(this.getParameter(champ) == null) 
			{
				erreurs.put(champ, EMPTY_FIELD_ERROR_MESSAGE);
			}
		}
	}
	
	private void validerPassword() 
    {
		String password = this.getParameter(CHAMP_PASSWORD);
		String passwordBis = this.getParameter(CHAMP_PASSWORD_BIS);
		if(password != null && !password.equals(passwordBis)) 
        {
			erreurs.put(CHAMP_PASSWORD, DIFFERENT_PASSWORD_ERROR_MESSAGE);
			erreurs.put(CHAMP_PASSWORD_BIS, DIFFERENT_PASSWORD_ERROR_MESSAGE);
		}
		
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public boolean isStatus() {
		return status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
}
