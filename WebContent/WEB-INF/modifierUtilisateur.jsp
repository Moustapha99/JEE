<%@include file="header.jsp" %>
<div id="container">
<fieldset>
<legend>Modifier un utilisateur</legend>
<form method="post">
<input type="text" name="nom" placeholder="Nom" value="<%=request.getAttribute("nom")%>"><br>
<input type="text" name="prenom" placeholder="Prenom" value="<%=request.getAttribute("prenom")%>"><br>
<input type="text" name="login" placeholder="Login" value="<%=request.getAttribute("login")%>"><br>
<input type="password" name="password" placeholder="Mot de passe" value="<%=request.getAttribute("password")%>"><br>
<input type="submit" value="Modifier">
</form>
</fieldset>
</div>
</body>
</html>