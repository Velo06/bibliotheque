<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.entity.Pret" %>
<%
    List<Pret> pret = (List<Pret>) request.getAttribute("pret-en-cours");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pret</title>
</head>
<body>
<div class="container mt-5">
    <h2>Liste de vos prets en cours</h2>
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Livre</th>
            <th>Date retour prevu</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (pret != null) {
                for (Pret prets : pret) {
        %>
        <tr>
            <td><%= prets.getLivre().getTitre() %></td>
            <td><%= prets.getDateRetourPrevu() %></td>
            <td>
                <a href="/prolongement/formProlong?idPret=<%= prets.getId() %>&idAdherent=<%= prets.getAdherent().getId() %>">Prolonger</a>
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