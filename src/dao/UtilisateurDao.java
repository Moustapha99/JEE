package dao;

import java.sql.*;
import java.util.ArrayList;
import beans.Utilisateur;

public class UtilisateurDao {
	
	private final static String DB_URL= "jdbc:mysql://localhost:3306/tp_users_k";
	private final static String DB_LOGIN= "admin";
	private final static String DB_PASSWORD= "azerty";

	
	
	
	private static ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	public static int lastId = 1;
	
	static {
		Connection  conn = openConnexion();
		if( conn != null) {
			try {
				Statement statement = conn.createStatement();
				String query = "SELECT * FROM user";
				ResultSet result = statement.executeQuery(query);
				while(result.next()) {
					Utilisateur utilisateur = new Utilisateur(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("login"), result.getString("password"));
					utilisateurs.add(utilisateur);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Connection openConnexion() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private static boolean closeConnexion(Connection conn) {
		try {
			conn.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Utilisateur> lister() {
		return utilisateurs;
 	}
	
	public static boolean ajouter(Utilisateur utilisateur) throws SQLException  {
		Connection conn = openConnexion();
		String request = "INSERT INTO user (nom, prenom, login, password) values (?, ?, ?, ?)";
		// try{
			PreparedStatement pStatement = conn.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, utilisateur.getNom());
			pStatement.setString(2, utilisateur.getPrenom());
			pStatement.setString(3, utilisateur.getLogin());
			pStatement.setString(4, utilisateur.getPassword());
			if(pStatement.executeUpdate()!=0) {
				ResultSet keys = pStatement.getGeneratedKeys();
				if(keys.next()) {
					utilisateur.setId(keys.getInt(1));
					utilisateurs.add(utilisateur);
				}
				System.out.println("Utilisateur ajouté");
				return true;
			}
		// }catch(SQLException e) {
		// 	e.printStackTrace();
		// }finally {
		// 	closeConnexion(conn);
		// }
		return false;
	}
	
	public static boolean supprimer(int id) {	
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getId() == id)
			{
				Connection conn = openConnexion();
				String req = "DELETE FROM user where ( id = ? )";
				try{
					PreparedStatement pStatement = conn.prepareStatement(req);
					pStatement.setInt(1, id);
					if(pStatement.executeUpdate()!=0) {
						utilisateurs.remove(utilisateur);
						return true;
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					closeConnexion(conn);
				}
			}
		}
		
		return false;
	}
	
	public static boolean modifier(Utilisateur utilisateur) {	
		for (Utilisateur user : utilisateurs)
		{
			if (user.getId() == utilisateur.getId())
			{
				user.setNom(utilisateur.getNom());
				user.setPrenom(utilisateur.getPrenom());
				user.setLogin(utilisateur.getLogin());
				user.setPassword(utilisateur.getPassword());
				// update on the DB
				Connection conn = openConnexion();
				String request = "UPDATE user set nom=?, prenom=?, login=?, password=? where (id = ?)";
				try{
					PreparedStatement pStatement = conn.prepareStatement(request);
					pStatement.setString(1, utilisateur.getNom());
					pStatement.setString(2, utilisateur.getPrenom());
					pStatement.setString(3, utilisateur.getLogin());
					pStatement.setString(4, utilisateur.getPassword());
					if(pStatement.executeUpdate()!=0) {
						return true;
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					closeConnexion(conn);
				}
				return true;
			}
		}

		return false;
	}

	public static Utilisateur get(int id)
	{
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getId() == id)
			{
				return utilisateur;
			}
		}

		return null;
	}
	
	public static Utilisateur getByUsername(String login) {
		for(Utilisateur user : utilisateurs) {
			if(login.equals(user.getLogin())) {
				return user;
			}
		}
		return null;
	}

	public static boolean login(String login, String password) {
		int isRegister = 0;
		Connection  conn = openConnexion();
		if( conn != null) {
			String request = "SELECT * FROM users WHERE login = '"+login+"' AND password ='"+password+"'";
			try {
				Statement pStatement = conn.createStatement();
				
				ResultSet res = pStatement.executeQuery(request);
				while(res.next()) {
					isRegister++;
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	   return isRegister == 1 ? true : false;
	}
}


