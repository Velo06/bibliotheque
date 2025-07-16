-- 1. Insertion des types d'adhérents (avec quotas et pénalités)
INSERT INTO typeadherent (type, dureePret, dureePenalite, quotaLivre, quotaReservation, quotaProlongement, montantCotisation) VALUES
('Etudiant', 7, 10, 2, 1, 3, NULL),
('Enseignant', 9, 9, 3, 2, 5, NULL),
('Professionnel', 12, 8, 4, 3, 7, NULL);

-- 2. Insertion des adhérents
INSERT INTO adherent (nom, prenom, idTypeAdherent) VALUES
('Bensaid', 'Amine', 1),
('El Khattabi', 'Sarah', 1),
('Moujahid', 'Youssef', 1),
('Benali', 'Nadia', 2),
('Haddadi', 'Karim', 2),
('Touhami', 'Salima', 2),
('El Mansouri', 'Rachid', 3),
('Zerouali', 'Amina', 3);

-- 3. Insertion des thèmes
INSERT INTO theme (theme) VALUES
('Litterature classique'),
('Philosophie'),
('Jeunesse / Fantastique');

-- 4. Insertion des auteurs
INSERT INTO auteur (nomAuteur) VALUES
('Victor Hugo'),
('Albert Camus'),
('J.K. Rowling');

-- 5. Insertion des livres
INSERT INTO livre (titre, idAuteur, idTheme, nbrExemplaire) VALUES
('Les Miserables', 1, 1, 3),
('L''Etranger', 2, 2, 2),
('Harry Potter a l''ecole des sorciers', 3, 3, 1);

-- 6. Insertion des états de livre
INSERT INTO etatlivre (etat) VALUES
('Disponible'),
('Reserve'),
('En pret');

-- 7. Insertion des exemplaires
INSERT INTO exemplaire (idLivre, idEtat) VALUES
(1, 1), (1, 1), (1, 1),  -- 3 exemplaires pour Les Misérables
(2, 1), (2, 1),          -- 2 exemplaires pour L'Étranger
(3, 1);                  -- 1 exemplaire pour Harry Potter

-- 8. Insertion des abonnements
INSERT INTO abonnement (idAdherent, dateDebut, dateFin, validite) VALUES
(1, '2025-02-01', '2025-07-24',0),
(2, '2025-02-01', '2025-07-01',1),
(3, '2025-04-01', '2025-12-01',0),
(4, '2025-07-01', '2026-07-01',0),
(5, '2025-08-01', '2026-05-01',1),
(6, '2025-07-01', '2026-06-01',0),
(7, '2025-06-01', '2025-12-01',0),
(8, '2024-10-01', '2025-06-01',1);

-- 9. Insertion des jours fériés
INSERT INTO jourferie (dateFerie) VALUES
('2025-07-19'),  -- Jour férié
('2025-07-26'),  -- Jour férié
('2025-07-13'),  -- Dimanche
('2025-07-20'),  -- Dimanche
('2025-07-27'),  -- Dimanche
('2025-08-03'),  -- Dimanche
('2025-08-10'),  -- Dimanche
('2025-08-17');  -- Dimanche

-- 10. Insertion des états (pour réservations/prolongements)
INSERT INTO etat (etat) VALUES
('Accepte'),
('Refuse'),
('En attente');

-- 11. Insertion des types de prêt
INSERT INTO typepret (type) VALUES
('Sur place'),
('A la maison');