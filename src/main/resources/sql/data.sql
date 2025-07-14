-- typeadherent
INSERT INTO typeadherent (type, dureePret, dureePenalite, quotaLivre, quotaReservation, quotaProlongement, montantCotisation) VALUES
('Etudiant', 14, 7, 3, 2, 1, 0.00),
('Professionnel', 21, 10, 5, 3, 2, 50.00),
('Professeur', 28, 0, 10, 5, 3, 0.00);

-- theme
INSERT INTO theme (theme) VALUES
('Science'),
('Littérature'),
('Histoire'),
('Informatique');

-- auteur
INSERT INTO auteur (nomAuteur) VALUES
('Victor Hugo'),
('Albert Einstein'),
('J.K. Rowling'),
('Isaac Asimov');

-- etatlivre
INSERT INTO etatlivre (etat) VALUES
('disponible'),
('reserve'),
('en pret');

-- typepret
INSERT INTO typepret (type) VALUES
('Sur place'),
('A la maison');

-- adherent
INSERT INTO adherent (nom, prenom, dateNaissance, idTypeAdherent, mdp) VALUES
('Rakoto', 'Jean', '1995-05-20', 1, 'mdp123'),
('Rasoa', 'Marie', '1980-10-11', 2, 'mdp456'),
('Rabe', 'Ando', '1975-03-15', 3, 'mdp789');

-- bibliothecaire
INSERT INTO bibliothecaire (nom, prenom, dateNaissance, mdp) VALUES
('Andrian', 'Lala', '1985-08-22', 'admin123');

-- livre
INSERT INTO livre (titre, idAuteur, idTheme, age, nbrExemplaire) VALUES
('Les Misérables', 1, 2, 16, 4),
('Relativité Générale', 2, 1, 18, 2),
('Harry Potter à l école des sorciers', 3, 2, 10, 5),
('Fondation', 4, 4, 14, 3);

-- exemplaire (avec idEtat = 1 => disponible)
INSERT INTO exemplaire (idLivre, idEtat) VALUES
(1, 1), (1, 1), (1, 3), (1, 2), -- 4 exemplaires du livre 1
(2, 1), (2, 3),                -- 2 exemplaires du livre 2
(3, 1), (3, 1), (3, 1), (3, 2), (3, 3), -- 5 exemplaires livre 3
(4, 1), (4, 2), (4, 3);       -- 3 exemplaires livre 4

-- abonnement
INSERT INTO abonnement (idAdherent, montant, dateDebut, dateFin) VALUES
(1, 0.00, '2025-01-01', '2025-12-31'),
(2, 50.00, '2025-01-01', '2025-12-31'),
(3, 0.00, '2025-01-01', '2025-12-31');

-- penalisation (par exemple adhérent 2 pénalisé)
INSERT INTO penalisation (idAdherent) VALUES
(2);

INSERT INTO etat(etat) VALUES
('Accepté'),
('Refusé'),
('En attente');

-- reservation
INSERT INTO reservation (idAdherent, idLivre, idEtat, dateDemande, dateReservation) VALUES
(1, 4, 1, '2025-07-01', '2025-07-05'),
(3, 1, 3, '2025-07-10', NULL);

-- pret
INSERT INTO pret (idTypePret, idAdherent, idLivre, dateEmprunt, dateRetourPrevu) VALUES
(2, 1, 3, '2025-07-01', '2025-07-15'),
(1, 3, 1, '2025-06-15', '2025-06-15');

-- rendrepret
INSERT INTO rendrepret (idPret, dateRetourReel) VALUES
(2, '2025-06-15');

-- prolongement
INSERT INTO prolongement (idPret, dateFin) VALUES
(1, '2025-07-22');

-- jourferie
INSERT INTO jourferie (dateFerie) VALUES
('2025-01-01'),
('2025-05-01'),
('2025-12-25');
