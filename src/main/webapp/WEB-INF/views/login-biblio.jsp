<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion - Bibliothécaire</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Optional: Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .login-container {
            max-width: 400px;
            margin: 80px auto;
        }

        .card {
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        .form-label {
            font-weight: 500;
        }
    </style>
</head>
<body>

<div class="container login-container">
    <div class="card">
        <h3 class="text-center mb-4">Connexion Bibliothecaire</h3>

        <!-- Message d'erreur -->
        <c:if test="${not empty error}">
            <div role="alert">
                ${error}
            </div>
        </c:if>

        <!-- Formulaire -->
        <form action="/bibliothecaire/checkLogin" method="post">
            <div class="mb-3">
                <label for="nom" class="form-label">Nom d'utilisateur</label>
                <input type="text" class="form-control" name="nom" id="nom" required>
            </div>

            <div class="mb-3">
                <label for="mdp" class="form-label">Mot de passe</label>
                <input type="password" class="form-control" name="mdp" id="mdp" required>
            </div>

            <div class="d-grid mt-4">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-box-arrow-in-right"></i> Se connecter
                </button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
