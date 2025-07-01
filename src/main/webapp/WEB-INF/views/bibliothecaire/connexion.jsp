<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion Bibliothécaire</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .error { 
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Connexion Bibliothécaire</h1>
    
    <% 
    String errorType = request.getParameter("errorType");
    if (errorType != null) {
        String errorMessage = "";
        switch (errorType) {
            case "pseudo":
                errorMessage = "Pseudo incorrect";
                break;
            case "password":
                errorMessage = "Mot de passe incorrect";
                break;
            default:
                errorMessage = "Identifiants incorrects";
        }
    %>
        <p class="error"><%= errorMessage %></p>
    <% } %>
    
    <form action="${pageContext.request.contextPath}/bibliothecaire/connexion" method="post">
        <div>
            <label for="pseudo">Pseudo :</label>
            <input type="text" id="pseudo" name="pseudo" required>
        </div>
        <div>
            <label for="motDePasse">Mot de passe :</label>
            <input type="password" id="motDePasse" name="motDePasse" required>
        </div>
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>