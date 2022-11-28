<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'un utilisateur</title>
</head>
<body>
	<div id="corps">
		<h1 id="titre-principal">Ajout d'un utilisateur</h1>
		<form method="post">
      		<h4 class="${ status ? 'success' : 'erreur'}">${ statusMessage }</h4>
			<div class="formItem">
				<label>Nom</label>
        		<input type="text" name="nom" value="${ utilisateur.nom }">
       			 <span class="erreur">${ erreurs.nom }</span>
			</div>
			<div class="formItem">
				<label>Pr�nom</label>
		        <input type="text" name="prenom" value="${ utilisateur.prenom }">
		        <span class="erreur">${ erreurs.prenom }</span>
			</div>
			<div class="formItem">
				<label>Login</label>
		        <input type="text" name="login" value="${ utilisateur.login }">
		        <span class="erreur">${ erreurs.login }</span>
			</div>
			<div class="formItem">
				<label>Password</label>
		        <input type="password" name="password" value="">
		        <span class="erreur">${ erreurs.password }</span>
			</div>
			<div class="formItem">
				<label>Password (Confirmation)</label>
		        <input type="password" name="passwordBis" value="">
		        <span class="erreur">${ erreurs.passwordBis }</span>
			</div>
			<div class="formItem">
				<label></label>
				<input type="submit" value="Ajouter">
			</div>
		</form>
	</div>
</div>
</body>
</html>