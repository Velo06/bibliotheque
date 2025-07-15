<%@ page import="com.example.demo.entity.Pret" %>
<%
    Long pret = (Long) request.getAttribute("idPret");
    Long adh = (Long) request.getAttribute("idAdherent");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire rendre pret</title>
    <style>
        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
        .card { border: 1px solid #ddd; border-radius: 5px; padding: 20px; }
        .mb-3 { margin-bottom: 1rem; }
        .btn { padding: 8px 16px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
        select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <form action="/pret/saveRendrePret" method="post">
                    <input type="hidden" value="<%= pret %>" name="pret">
                    <input type="hidden" value="<%= adh %>" name="adh">

                    <div class="mb-3">
                        <label>Date de retour :</label>
                        <input type="date" name="dateRetourReel">
                    </div>

                    <div>
                        <button type="submit" class="btn">
                            Rendre
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>