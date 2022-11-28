<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div id="container">
<%
ArrayList<Utilisateur> utilisateur = (ArrayList<Utilisateur>)request.getAttribute("utilisateur");
if(utilisateur.isEmpty()){
%>
<p>La liste d'utilisateur est vide</p>
<% } else { %>
	<table border>
	<th>ID</th>
	<th>Nom</th>
	<th>Prenom</th>
	<th>Login</th>
	<th>Password</th>
	<th>Action</th>
	<% for(Utilisateur user : utilisateur){ %>
	<tr>
	<td><%=user.getId()%></td>
	<td><%=user.getNom()%></td>
	<td><%=user.getPrenom()%></td>
	<td><%=user.getLogin()%></td>
	<td><%=user.getPassword()%></td>
	<td><a href="update?id=<%=user.getId()%>">Modifier</a> &nbsp; <a href="delete?id=<%=user.getId()%>">Supprimer</a></td>
	</tr>
	<%} %>
	</table>
<% } %>
</div>
</body>
</html>