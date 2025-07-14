<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire d'insertion</title>
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0"><i class="<%= icone %>"></i> <%= pageTitle %></h4>
            </div>
            <div class="card-body">
                <form action="/adherent/saveAdherent" method="post">

                    <div class="mb-3">
                        <label for="nom">Nom:</label>
                        <input type="text" name="nom" id="nom">
                    </div>

                    <div class="mb-3">
                        <label for="pnom">Pr&eacute;nom:</label>
                        <input type="text" name="pnom" id="pnom">
                    </div>

                    <div class="mb-3">
                        <label for="dtn">Date de naissance:</label>
                        <input type="date" name="dtn" id="dtn">
                    </div>

                    <div class="mb-3">
                        <label for="mdp">Mot de passe:</label>
                        <input type="password" name="mdp" id="mdp">
                    </div>

                    <div class="d-flex justify-content-md-end gap-2">
                        <button type="submit" class="btn btn-success">
                            <i class="bi bi-check-lg"></i> Enregistrer
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
