<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard Bibliothécaire</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <div class="dashboard-card">
            <h1>Bienvenue ${bibliothecaire.pseudo}</h1>
            <p>Vous êtes connecté en tant que bibliothécaire.</p>
            
            <div class="dashboard-actions">
                <a href="/logout" class="logout-btn">Se déconnecter</a>
            </div>
        </div>
    </div>
</body>
</html>