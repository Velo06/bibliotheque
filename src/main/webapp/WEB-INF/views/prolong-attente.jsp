<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.entity.Prolongement" %>
<%
    List<Prolongement> pro = (List<Prolongement>) request.getAttribute("liste-prolongement");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prolongement en attente</title>
    <%-- <link rel="stylesheet" href="../css/style.css"> --%>
</head>
<body>
<div class="container mt-5">
    <h2>Liste des prolongements en attente de validation</h2>
    <table class="table" border="1">
        <thead>
        <tr>
            <th>Pret</th>
            <th>Date de demande</th>
            <th>Date fin prolongement</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (pro != null) {
                for (Prolongement p : pro) {
        %>
        <tr>
            <td><%= p.getPret() %></td>
            <td><%= p.getDateDemande() %></td>
            <td><%= p.getDateFin() %></td>
            <td>
                <a href="/bibliothecaire/acceptProlong?idProlong=<%= p.getId() %>">Accepter</a>
                <a href="/bibliothecaire/refusProlong?idProlong=<%= p.getId() %>">Refuser</a>
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