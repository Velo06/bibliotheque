<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.entity.TypePret" %>
<%@ page import="com.example.demo.entity.Adherent" %>
<%@ page import="com.example.demo.entity.Livre" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire de pret</title>
    <style>
        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
        .card { border: 1px solid #ddd; border-radius: 5px; padding: 20px; }
        .mb-3 { margin-bottom: 1rem; }
        .btn { padding: 8px 16px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
        select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
    </style>
    <%-- <link rel="stylesheet" href="../css/style.css"> --%>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <form action="/pret/preter" method="post">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${message}
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <label>Type de pret :</label>
                        <select name="typePretId">
                            <%
                                List<TypePret> listeTypes = (List<TypePret>) request.getAttribute("listeTypes");
                                if (listeTypes != null) {
                                    for (TypePret t : listeTypes) {
                            %>
                                        <option value="<%= t.getId() %>"><%= t.getType() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label>Adherent :</label>
                        <select name="adherentId">
                            <%
                                List<Adherent> listeAdherents = (List<Adherent>) request.getAttribute("listeAdherents");
                                if (listeAdherents != null) {
                                    for (Adherent a : listeAdherents) {
                            %>
                                        <option value="<%= a.getId() %>"><%= a.getNom() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label>Livre :</label>
                        <select name="livreId">
                            <%
                                List<Livre> listeLivres = (List<Livre>) request.getAttribute("listeLivres");
                                if (listeLivres != null) {
                                    for (Livre l : listeLivres) {
                            %>
                                        <option value="<%= l.getId() %>"><%= l.getTitre() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label>Date d'emprunt :</label>
                        <input type="date" name="dateEmprunt">
                    </div>

                    <div class="mb-3">
                        <label>Date de retour prevu :</label>
                        <input type="date" name="datePrevu">
                    </div>

                    <div>
                        <button type="submit" class="btn">
                            Preter
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>