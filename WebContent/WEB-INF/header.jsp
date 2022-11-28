<%@page import="beans.Utilisateur"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String BaseURL = request.getContextPath();%>

<%@ page language="java" pageEncoding="ISO-8859-1" %>
    <html>
      <head>
      <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
      </head>
      <body>
      <div id="entete">Gestion des utilisateurs</div>
        <div id="menu">
          <ul>
            <c:choose>
              <c:when test="${ sessionScope.session != null }">
                <li><a href="<c:url value='/list'/>">Lister</a></li>
                <li><a href="<c:url value='/add'/>">Ajouter</a></li>
                <li><a href="<c:url value='/logout'/>">Deconnexion</a></li>
              </c:when>
              <c:otherwise>
                <li><a href="<c:url value='/auth'/>">Connexion</a></li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div>
      


