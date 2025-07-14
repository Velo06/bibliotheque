<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
             <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error}
                </div>
            </c:if>
            <div class="card-body">
                <form action="/bibliothecaire/checkLogin" method="post">

                    <div class="mb-3">
                        <label for="nom">Nom:</label>
                        <input type="text" name="nom" id="nom">
                    </div>

                    <div class="mb-3">
                        <label for="mdp">Mot de passe:</label>
                        <input type="password" name="mdp" id="mdp">
                    </div>

                    <div class="d-flex justify-content-md-end gap-2">
                        <button type="submit" class="btn btn-success">
                            <i class="bi bi-check-lg"></i> Se connecter
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
