<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.entity.Reservation" %>
<%
    List<Reservation> resa = (List<Reservation>) request.getAttribute("listeResa");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reservation en attente</title>
</head>
<body>
<div class="container mt-5">
    <h2>Liste des reservations en attente de validation</h2>
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Adherent</th>
            <th>Livre</th>
            <th>Date de demande</th>
            <th>Date reservee</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (resa != null) {
                for (Reservation r : resa) {
        %>
        <tr>
            <td><%= r.getAdherent().getNom() %></td>
            <td><%= r.getLivre().getTitre() %></td>
            <td><%= r.getDateDemande() %></td>
            <td><%= r.getDateReservation() %></td>
            <td>
                <a href="/bibliothecaire/acceptResa?idResa=<%= r.getId() %>">Accepter</a>
                <a href="/bibliothecaire/refusResa?idResa=<%= r.getId() %>">Refuser</a>
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