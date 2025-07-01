<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Entités</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .logout-btn {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <%
    Object bibliothecaire = session.getAttribute("bibliothecaire");
    if (bibliothecaire == null) {
        response.sendRedirect(request.getContextPath() + "/bibliothecaire/connexion");
        return;
    }
    %>
    
    <h1>Bienvenue <%= ((com.exemple.entity.Bibliothecaire) bibliothecaire).getPseudo() %></h1>
    <h2>Liste des entités</h2>
    
    <!-- Contenu de la liste à implémenter ici -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <!-- Ajoutez d'autres colonnes selon vos besoins -->
        </tr>
        <!-- Exemple de ligne statique -->
        <tr>
            <td>1</td>
            <td>Exemple Entité</td>
        </tr>
    </table>
    
    <div class="logout-btn">
        <a href="${pageContext.request.contextPath}/bibliothecaire/deconnexion">Déconnexion</a>
    </div>
</body>
</html>