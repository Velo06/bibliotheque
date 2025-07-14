<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.entity.Livre" %>
<%
    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
    Long currentUserId = (Long) request.getAttribute("currentUserId");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Livres</title>
</head>
<body>
<div class="container mt-5">
    <h2>Liste des livres</h2>
    <a href="/pret/enCours?idAdherent=<%= currentUserId %>">Pret en cours</a>
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Theme</th>
            <th>Age min</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (livres != null) {
                for (Livre livre : livres) {
        %>
        <tr>
            <td><%= livre.getTitre() %></td>
            <td><%= livre.getAuteur().getNomAuteur() %></td>
            <td><%= livre.getTheme().getTheme() %></td>
            <td><%= livre.getAgeRestriction() %>+</td>
            <td>
                <a href="/reservation/formReserve?idAdherent=<%= currentUserId %>&idLivre=<%= livre.getId() %>">Reserver</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>