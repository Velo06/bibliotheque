<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Dashboard - Bibliothèque</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f6f8;
    }

    header {
      background-color: #2e86de;
      color: white;
      padding: 20px;
      text-align: center;
    }

    .dashboard {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
      gap: 30px;
      padding: 40px;
      max-width: 1000px;
      margin: auto;
    }

    .card {
      background-color: white;
      border-radius: 10px;
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
      padding: 30px;
      text-align: center;
      transition: transform 0.2s ease-in-out;
      cursor: pointer;
    }

    .card:hover {
      transform: translateY(-5px);
    }

    .card h3 {
      color: #2e86de;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>

  <header>
    <h1>Bibliotheque</h1>
  </header>

  <section class="dashboard">
    <div class="card" onclick="location.href='/pret/formPreter'">
      <h3>Preter un livre</h3>
      <p>Enregistrer un pret d’exemplaire e un adherent.</p>
    </div>
    <div class="card" onclick="location.href='/pret/rendrePret'">
      <h3>Rendre un livre</h3>
      <p>Gerer les retours d’exemplaires empruntes.</p>
    </div>
    <div class="card" onclick="location.href='/bibliothecaire/validerResa'">
      <h3>Reservations</h3>
      <p>Valider ou refuser les reservations en attente.</p>
    </div>
    <div class="card" onclick="location.href='/bibliothecaire/prolongement'">
      <h3>Prolongements</h3>
      <p>Gerer les demandes de prolongement de pret.</p>
    </div>
  </section>

</body>
</html>
