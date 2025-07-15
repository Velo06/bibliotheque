<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.entity.Pret" %>
<%
    List<Pret> prets = (List<Pret>) request.getAttribute("listeNonRendu");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Livres</title>
</head>
<body>
<div class="container mt-5">
    <h2>Liste des prets non rendus</h2>
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Type pret</th>
            <th>Adherent</th>
            <th>Livre</th>
            <th>Date retour prevu</th>
            <th>Date d'emprunt</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (prets != null) {
                for (Pret pret : prets) {
        %>
        <tr>
            <td><%= pret.getTypePret().getType() %></td>
            <td><%= pret.getAdherent().getNom() %></td>
            <td><%= pret.getLivre().getTitre() %></td>
            <td><%= pret.getDateRetourPrevu() %></td>
            <td><%= pret.getDateEmprunt() %></td>
            <td>
                <a href="/pret/formSaveRendre?idPret=<%= pret.getId() %>&idAdherent=<%= pret.getAdherent().getId() %>">Rendre</a>
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