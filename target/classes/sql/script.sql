CREATE DATABASE IF NOT EXISTS gestion_bibliotheque;
USE gestion_bibliotheque;

CREATE TABLE bibliothecaire (
    id_bibliothecaire INT AUTO_INCREMENT PRIMARY KEY,
    pseudo VARCHAR(50) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL
);

CREATE TABLE Jour_ferie (
    id_jour_ferie INT AUTO_INCREMENT PRIMARY KEY,
    nom_fete VARCHAR(100) NOT NULL,
    date_jour_ferie DATE NOT NULL,
    est_exceptionnel BOOLEAN DEFAULT FALSE,
    UNIQUE KEY (date_jour_ferie)
);

CREATE TABLE Status_demande (
    id_status_demande INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Status_exemplaire (
    id_status_exemplaire INT AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Auteur (
    id_auteur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE Editeur (
    id_editeur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

CREATE TABLE Type_demande (
    id_type_demande INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Livre (
    id_livre INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    nombre_pages INT,
    id_auteur INT NOT NULL,
    id_editeur INT NOT NULL,
    description TEXT,
    annee_publication INT,
    restriction_age_min INT DEFAULT 0,
    FOREIGN KEY (id_auteur) REFERENCES Auteur(id_auteur),
    FOREIGN KEY (id_editeur) REFERENCES Editeur(id_editeur),
    CHECK (restriction_age_min >= 0)
);

CREATE TABLE Exemplaire (
    id_exemplaire INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT NOT NULL,
    id_status_exemplaire INT NOT NULL,
    date_ajout DATE NOT NULL,
    code_barre VARCHAR(20) UNIQUE,
    FOREIGN KEY (id_livre) REFERENCES Livre(id_livre),
    FOREIGN KEY (id_status_exemplaire) REFERENCES Status_exemplaire(id_status_exemplaire)
);

CREATE TABLE Type_pret (
    id_type_pret INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Type_adherent (
    id_type_adherent INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL UNIQUE,
    quota_livre INT NOT NULL DEFAULT 1,
    quota_prolongement INT NOT NULL DEFAULT 0,
    quota_reservation INT NOT NULL DEFAULT 1,
    duree_emprunt_max INT NOT NULL, -- en jours
    montant_abonnement DECIMAL(10,2) NOT NULL,
    duree_penalite INT NOT NULL DEFAULT 0, -- en jours
    CHECK (quota_livre > 0),
    CHECK (duree_emprunt_max > 0)
);

CREATE TABLE Adherent (
    id_adherent INT AUTO_INCREMENT PRIMARY KEY,
    id_type_adherent INT NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    date_de_naissance DATE NOT NULL,
    pseudo VARCHAR(50) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    date_d_inscription DATE NOT NULL,
    est_actif BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_type_adherent) REFERENCES Type_adherent(id_type_adherent),
    CHECK (date_d_inscription >= date_de_naissance)
);

CREATE TABLE Pret (
    id_pret INT AUTO_INCREMENT PRIMARY KEY,
    id_type_pret INT NOT NULL,
    id_adherent INT NOT NULL,
    id_exemplaire INT NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour_prevu DATE NOT NULL,
    date_retour_effective DATE,
    est_en_retard BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_type_pret) REFERENCES Type_pret(id_type_pret),
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id_adherent),
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id_exemplaire),
    CHECK (date_retour_prevu > date_emprunt),
    CHECK (date_retour_effective IS NULL OR date_retour_effective >= date_emprunt)
);

CREATE TABLE Prolongement (
    id_prolongement INT AUTO_INCREMENT PRIMARY KEY,
    id_status_demande INT NOT NULL,
    id_pret INT NOT NULL,
    date_fin_prolongement DATE NOT NULL,
    date_de_demande DATETIME NOT NULL,
    FOREIGN KEY (id_status_demande) REFERENCES Status_demande(id_status_demande),
    FOREIGN KEY (id_pret) REFERENCES Pret(id_pret),
    CHECK (date_fin_prolongement > date_de_demande)
);

CREATE TABLE Reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id_exemplaire INT NOT NULL,
    id_adherent INT NOT NULL,
    date_de_demande DATETIME NOT NULL,
    date_de_reservation DATE,
    id_status_demande INT NOT NULL,
    date_d_expiration DATE,
    priorite INT DEFAULT 1,
    FOREIGN KEY (id_exemplaire) REFERENCES Exemplaire(id_exemplaire),
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id_adherent),
    FOREIGN KEY (id_status_demande) REFERENCES Status_demande(id_status_demande),
    CHECK (date_d_expiration IS NULL OR date_d_expiration >= date_de_reservation)
);

CREATE TABLE Historique_status_demande (
    id_historique INT AUTO_INCREMENT PRIMARY KEY,
    id_demande INT NOT NULL,
    id_status_demande INT NOT NULL,
    date_changement DATETIME NOT NULL,
    id_bibliothecaire INT,
    commentaire TEXT,
    FOREIGN KEY (id_status_demande) REFERENCES Status_demande(id_status_demande),
    FOREIGN KEY (id_bibliothecaire) REFERENCES Bibliothecaire(id_bibliothecaire)
);

CREATE TABLE Paiement_abonnement (
    id_paiement_abonnement INT AUTO_INCREMENT PRIMARY KEY,
    id_adherent INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    date_de_paiement DATE NOT NULL,
    FOREIGN KEY (id_adherent) REFERENCES Adherent(id_adherent),
    CHECK (montant > 0)
);

CREATE TABLE Abonnement (
    id_abonnement INT AUTO_INCREMENT PRIMARY KEY,
    id_paiement_abonnement INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    FOREIGN KEY (id_paiement_abonnement) REFERENCES Paiement_abonnement(id_paiement_abonnement),
    CHECK (date_fin > date_debut)
);

CREATE TABLE Penalite (
    id_penalite INT AUTO_INCREMENT PRIMARY KEY,
    id_pret INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    montant DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (id_pret) REFERENCES Pret(id_pret),
    CHECK (date_fin >= date_debut)
);

DELIMITER //
CREATE TRIGGER update_retard BEFORE UPDATE ON Pret
FOR EACH ROW
BEGIN
    DECLARE today DATE;
    SET today = CURDATE();
    
    IF NEW.date_retour_effective IS NOT NULL THEN
        SET NEW.est_en_retard = (NEW.date_retour_effective > NEW.date_retour_prevu);
    ELSE
        SET NEW.est_en_retard = (today > NEW.date_retour_prevu);
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER check_reservation_expiration BEFORE UPDATE ON Reservation
FOR EACH ROW
BEGIN
    DECLARE today DATE;
    DECLARE status_expiree_id INT;
    SET today = CURDATE();

    SELECT id_status_demande INTO status_expiree_id 
    FROM Status_demande 
    WHERE status = 'Expirée';

    IF NEW.date_d_expiration IS NOT NULL AND today > NEW.date_d_expiration 
       AND NEW.id_status_demande != status_expiree_id THEN

        SET NEW.id_status_demande = status_expiree_id;

        INSERT INTO Historique_status_demande (
            id_demande, 
            id_status_demande, 
            date_changement, 
            id_bibliothecaire,
            commentaire
        ) VALUES (
            NEW.id_reservation, 
            status_expiree_id, 
            NOW(), 
            NULL,
            'Expiration automatique'
        );
    END IF;
END//
DELIMITER ;

CREATE VIEW vue_pret_en_retard AS
SELECT p.*, a.nom, a.prenom, e.code_barre
FROM Pret p
JOIN Adherent a ON p.id_adherent = a.id_adherent
JOIN Exemplaire e ON p.id_exemplaire = e.id_exemplaire
WHERE p.est_en_retard = TRUE;

CREATE VIEW vue_reservations_expirees AS
SELECT r.*, a.nom, a.prenom, e.code_barre
FROM Reservation r
JOIN Adherent a ON r.id_adherent = a.id_adherent
JOIN Exemplaire e ON r.id_exemplaire = e.id_exemplaire
WHERE r.id_status_demande = (SELECT id_status_demande FROM Status_demande WHERE status = 'Expirée');