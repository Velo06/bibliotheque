<%@ page import="com.example.demo.entity.Adherent" %>
<%@ page import="com.example.demo.entity.Livre" %>
<%
    Long adherent = (Long) request.getAttribute("idAdherent");
    Long livre = (Long) request.getAttribute("idLivre");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire de reservation</title>
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
                <form action="/reservation/saveReserve" method="post">
                   <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${message}
                        </div>
                    </c:if>
                    <input type="hidden" value="<%= adherent %>" name="adh">
                    <input type="hidden" value="<%= livre %>" name="livre">

                    <div class="mb-3">
                        <label>Date a reserver :</label>
                        <input type="date" name="dateResa">
                    </div>

                    <div>
                        <button type="submit" class="btn">
                            Reserver
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>